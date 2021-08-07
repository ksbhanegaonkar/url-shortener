package com.demoapp.services;

import com.demoapp.repositories.URLMappingRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class URLMappingService {

    @Inject
    URLMappingRepository urlMappingRepository;

    public String registerURL(String url){
        return "test url shorting"+url;
    }
}
