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
        if(existingMapping.isPresent()){
            return existingMapping.get().getShortUrl();
        }else{
            String uniqueShortUrlPath = generateUniqueShortUrl();
            URLMappingEntity entity = new URLMappingEntity(uniqueShortUrlPath, url);
            urlMappingRepository.save(entity);
            return uniqueShortUrlPath;
        }
    }

    private String generateUniqueShortUrl() {
        LOGGER.info("Generating short url");
        String shortUrl;
        Optional<URLMappingEntity> existingEntity;
        do {
            shortUrl = UUID.randomUUID().toString().substring(0,8);
            existingEntity = urlMappingRepository.findByShortUrl(shortUrl);
        }while (existingEntity.isPresent());
        return shortUrlPrefix+shortUrl;
    }
}
