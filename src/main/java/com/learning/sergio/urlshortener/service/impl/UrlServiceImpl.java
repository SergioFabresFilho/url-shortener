package com.learning.sergio.urlshortener.service.impl;

import com.learning.sergio.urlshortener.domain.entity.Url;
import com.learning.sergio.urlshortener.domain.request.ShortenUrlRequest;
import com.learning.sergio.urlshortener.domain.response.FindFullUrlResponse;
import com.learning.sergio.urlshortener.domain.response.ShortenUrlResponse;
import com.learning.sergio.urlshortener.domain.response.UrlDetailResponse;
import com.learning.sergio.urlshortener.exception.NotFoundException;
import com.learning.sergio.urlshortener.exception.message.ExceptionMessage;
import com.learning.sergio.urlshortener.repository.UrlRepository;
import com.learning.sergio.urlshortener.service.UrlService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public ShortenUrlResponse shortenUrl(ShortenUrlRequest shortenUrlRequest) {
        Url url = new Url(null, shortenUrlRequest.shortenedUrl(), shortenUrlRequest.fullUrl(),
                shortenUrlRequest.clickCountLimit(), shortenUrlRequest.dateTimeLimit());
        Url persistedUrl = urlRepository.save(url);

        return new ShortenUrlResponse(persistedUrl.getShortenedUrl());
    }

    @Override
    public FindFullUrlResponse findFullUrl(String shortenedUrl) {
        Url url = urlRepository.findByShortenedUrl(shortenedUrl)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.URL_NOT_FOUND));

        checkUrlIsStillValid(url);

        urlRepository.incrementClickCount(url.getId());

        return new FindFullUrlResponse(url.getFullUrl());
    }

    @Override
    public UrlDetailResponse getUrlDetail(String shortenedUrl) {
        Url url = urlRepository.findByShortenedUrl(shortenedUrl)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.URL_NOT_FOUND));

        return new UrlDetailResponse(url.getId(), url.getShortenedUrl(), url.getFullUrl(), url.getClickCount(),
                url.getClickCountLimit(), url.getDateTimeLimit());
    }

    private void checkUrlIsStillValid(Url url) {
        validateUrlClickCountLimit(url);
        validateUrlDateTimeLimit(url);
    }

    private void validateUrlClickCountLimit(Url url) {
        if (url.getClickCountLimit() != null && url.getClickCount() >= url.getClickCountLimit()) {
            urlRepository.deleteById(url.getId());
            throw new RuntimeException("Click count exceeded");
        }
    }

    private void validateUrlDateTimeLimit(Url url) {
        if (url.getDateTimeLimit() != null && url.getDateTimeLimit().isBefore(LocalDateTime.now())) {
            urlRepository.deleteById(url.getId());
            throw new RuntimeException("Date time exceeded");
        }
    }
}
