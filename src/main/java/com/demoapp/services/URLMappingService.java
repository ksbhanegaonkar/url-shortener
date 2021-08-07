package com.demoapp.services;

import com.demoapp.entity.URLMappingEntity;
import com.demoapp.repositories.URLMappingRepository;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

@Singleton
public class URLMappingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(URLMappingService.class);

    @Value("${short-url-prefix}")
    private String shortUrlPrefix;

    @Inject
    URLMappingRepository urlMappingRepository;

    public String registerURL(String url){
        LOGGER.info("Registering short url {}", url);
        Optional<URLMappingEntity> existingMapping = urlMappingRepository.findByLongUrl(url);
        if(existingMapping.isPresent()){//check if mapping already available and if yes then send the existing short url
            return existingMapping.get().getShortUrl();
        }else{//Create new mapping as mapping not available
            String uniqueShortUrlPath = generateUniqueShortUrl();
            URLMappingEntity entity = new URLMappingEntity(uniqueShortUrlPath, url);
            urlMappingRepository.save(entity);
            return uniqueShortUrlPath;
        }
    }

    public String decodeShortUrl(String shortUrl){
        LOGGER.info("Registering short url {}", shortUrl);
        Optional<URLMappingEntity> existingMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if(existingMapping.isPresent()){
            return existingMapping.get().getLongUrl();
        }else{
            LOGGER.error("No short url {} registered",shortUrl);
            return "URL not registered !!";
        }
    }

    private String generateUniqueShortUrl() {
        LOGGER.info("Decoding short url");
        String shortUrl;
        Optional<URLMappingEntity> existingEntity;
        do {
            //Generate a UUID and take first 8 characters and check if it exists in DB, if yes then create new else use that.
            shortUrl = UUID.randomUUID().toString().substring(0,8);
            existingEntity = urlMappingRepository.findByShortUrl(shortUrl);
        }while (existingEntity.isPresent());
        return shortUrlPrefix+shortUrl;
    }
}
