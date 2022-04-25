package com.learning.sergio.urlshortener.domain.request;

import java.time.LocalDateTime;

public class ShortenUrlRequestMother {

    public static ShortenUrlRequest createShortenUrlRequest() {
        return new ShortenUrlRequest("http://localhost", "shortened fullUrl", null, null);
    }

    public static ShortenUrlRequest createShortenUrlRequestWithInvalidClickCountLimit() {
        return new ShortenUrlRequest("http://localhost", "shortened fullUrl", -1L, null);
    }

    public static ShortenUrlRequest createShortenUrlRequestWithInvalidDateTimeLimit() {
        return new ShortenUrlRequest("http://localhost", "shortened fullUrl", null, LocalDateTime.MIN);
    }

    private ShortenUrlRequestMother() {}
}
