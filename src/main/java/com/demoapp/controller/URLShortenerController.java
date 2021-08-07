package com.demoapp.controller;

import com.demoapp.services.URLMappingService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("/service/v1")
public class URLShortenerController {

    @Inject
    URLMappingService urlMappingService;

    @Get("/getShortUrl")
    public String registerUrl(String longUrl){
        return urlMappingService.registerURL(longUrl);
    }
}
