package com.learning.sergio.urlshortener.service;

import com.learning.sergio.urlshortener.domain.request.ShortenUrlRequest;
import com.learning.sergio.urlshortener.domain.response.FindFullUrlResponse;
import com.learning.sergio.urlshortener.domain.response.ShortenUrlResponse;
import com.learning.sergio.urlshortener.domain.response.UrlDetailResponse;

public interface UrlService {
    ShortenUrlResponse shortenUrl(ShortenUrlRequest shortenUrlRequest);
    FindFullUrlResponse findFullUrl(String shortenedUrl);
    UrlDetailResponse getUrlDetail(String shortenedUrl);
}
