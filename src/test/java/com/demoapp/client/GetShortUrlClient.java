package com.demoapp.client;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("/service/v1")
public interface GetShortUrlClient {
    @Get("/getShortUrl?longUrl={longUrl}")
    String getShortUrl(String longUrl);
}
