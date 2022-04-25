package com.learning.sergio.urlshortener.domain.response;

public class ShortenUrlResponseMother {

    public static ShortenUrlResponse createShortenUrlResponse() {
        return new ShortenUrlResponse("abc");
    }

    private ShortenUrlResponseMother() {}
}
