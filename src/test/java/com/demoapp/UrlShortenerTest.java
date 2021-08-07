package com.demoapp;

import com.demoapp.client.GetShortUrlClient;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class UrlShortenerTest {

    @Inject
    GetShortUrlClient client;

    @Test
    @DisplayName("It registers long url and return short url")
    void itRegistersLongUrl() {
        String shortUrl = client.getShortUrl("https://longurl.com");

        //Short url lenght will always be same.
        Assertions.assertEquals(23, shortUrl.length());
    }

    @Test
    @DisplayName("It return same short url when sent multiple times")
    void itReturnsSaveShortUrlForSameLongUrl() {
        String shortUrl1 = client.getShortUrl("https://longurl.com");
        String shortUrl2 = client.getShortUrl("https://longurl.com");
        Assertions.assertEquals(23, shortUrl1.length());
        Assertions.assertEquals(23, shortUrl2.length());

        //Here two short urls are same because it got triggered with same url
        Assertions.assertEquals(shortUrl1, shortUrl2);
    }

    @Test
    @DisplayName("It return different short url for different long url")
    void itReturnsDifferentShortUrlForDifferentLongUrl() {
        String shortUrl1 = client.getShortUrl("https://longurl1.com");
        String shortUrl2 = client.getShortUrl("https://longurl2.com");
        Assertions.assertEquals(23, shortUrl1.length());
        Assertions.assertEquals(23, shortUrl2.length());

        //Here two short urls are different because long urls are different
        Assertions.assertNotEquals(shortUrl1, shortUrl2);
    }

    @Test
    @DisplayName("It decodes registered url")
    void itDecodesRegisteredUrl() {
        String shortUrl = client.getShortUrl("https://longurl.com");
        Assertions.assertEquals(23, shortUrl.length());

        String originalUrl = client.decodeShortUrl(shortUrl);
        Assertions.assertEquals("https://longurl.com", originalUrl);
    }

    @Test
    @DisplayName("It will not decode unknown short url")
    void itWillNotDecodeUnknownUrl() {
        String originalUrl = client.decodeShortUrl("unregisteredShortUrl");
        //Here two short urls are different because long urls are different
        Assertions.assertEquals("URL not registered !!", originalUrl);
    }
}
