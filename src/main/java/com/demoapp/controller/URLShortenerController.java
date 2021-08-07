package com.demoapp.controller;

import com.demoapp.services.URLMappingService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/service/v1")
public class URLShortenerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLShortenerController.class);


    @Inject
    URLMappingService urlMappingService;

    @Get("/getShortUrl")
    public String registerUrl(String longUrl){
        LOGGER.info("Request received for registering url {}", longUrl);
        return urlMappingService.registerURL(longUrl);
    }
}
