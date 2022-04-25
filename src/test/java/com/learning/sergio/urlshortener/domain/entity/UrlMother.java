package com.learning.sergio.urlshortener.domain.entity;

import java.time.LocalDateTime;

public class UrlMother {

    public static Url createUrl() {
        return new Url(1L, "abc", "http://localhost", null, null);
    }

    public static Url createUrlWithClickCountLimitExceeded() {
        Url url = new Url(1L, "abc", "http://localhost", 100L, LocalDateTime.MAX);
        url.setClickCount(url.getClickCountLimit() + 1);

        return url;
    }

    public static Url createUrlWithDateTimeLimitExceeded() {
        return new Url(1L, "abc", "http://localhost", 100L, LocalDateTime.MIN);
    }

    private UrlMother() {}
}
