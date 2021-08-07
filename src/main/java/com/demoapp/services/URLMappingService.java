package com.demoapp.services;

import com.demoapp.entity.URLMappingEntity;
import com.demoapp.repositories.URLMappingRepository;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;
import java.util.UUID;

@Singleton
public class URLMappingService {

    @Value("${short-url-prefix}")
    private String shortUrlPrefix;

    @Inject
    URLMappingRepository urlMappingRepository;

    public String registerURL(String url){
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
        String shortUrl;
        Optional<URLMappingEntity> existingEntity;
        do {
            shortUrl = UUID.randomUUID().toString().substring(0,8);
            existingEntity = urlMappingRepository.findByShortUrl(shortUrl);
        }while (existingEntity.isPresent());
        return shortUrlPrefix+shortUrl;
    }
}
