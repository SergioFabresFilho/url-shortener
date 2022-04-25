package com.learning.sergio.urlshortener.service.impl;

import com.learning.sergio.urlshortener.domain.entity.Url;
import com.learning.sergio.urlshortener.domain.entity.UrlMother;
import com.learning.sergio.urlshortener.domain.request.ShortenUrlRequest;
import com.learning.sergio.urlshortener.domain.request.ShortenUrlRequestMother;
import com.learning.sergio.urlshortener.domain.response.FindFullUrlResponse;
import com.learning.sergio.urlshortener.domain.response.ShortenUrlResponse;
import com.learning.sergio.urlshortener.domain.response.UrlDetailResponse;
import com.learning.sergio.urlshortener.exception.NotFoundException;
import com.learning.sergio.urlshortener.exception.message.ExceptionMessage;
import com.learning.sergio.urlshortener.repository.UrlRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class UrlServiceImplUnitTest {

    private final UrlRepository urlRepository = mock(UrlRepository.class);
    private final UrlServiceImpl urlService = new UrlServiceImpl(urlRepository);

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(urlRepository);
    }

    @Test
    void givenValidRequestWhenShortenUrlThenGenerateShortenedUrlAndPersist() {
        ShortenUrlRequest shortenUrlRequest = ShortenUrlRequestMother.createShortenUrlRequest();
        Url url = UrlMother.createUrl();

        given(urlRepository.save(any(Url.class)))
                .willReturn(url);

        ShortenUrlResponse response = urlService.shortenUrl(shortenUrlRequest);

        ArgumentCaptor<Url> argumentCaptor = ArgumentCaptor.forClass(Url.class);

        verify(urlRepository).save(argumentCaptor.capture());

        Url capturedUrl = argumentCaptor.getValue();
        then(capturedUrl.getFullUrl()).isEqualTo(shortenUrlRequest.fullUrl());
        then(capturedUrl.getShortenedUrl()).isEqualTo(shortenUrlRequest.shortenedUrl());
        then(response.shortenedUrl()).isEqualTo(url.getShortenedUrl());
    }

    @Test
    void givenValidShortenedUrlWhenFindFullUrlThenReturnFullUrlAndIncrementClickCount() {
        String shortenedUrl = "shortened fullUrl";
        Url url = UrlMother.createUrl();

        given(urlRepository.findByShortenedUrl(shortenedUrl))
                .willReturn(Optional.of(url));

        FindFullUrlResponse result = urlService.findFullUrl(shortenedUrl);

        verify(urlRepository).findByShortenedUrl(shortenedUrl);
        verify(urlRepository).incrementClickCount(url.getId());

        then(result.fullUrl()).isEqualTo(url.getFullUrl());
    }

    @Test
    void givenInvalidUrlWhenFindFullUrlThenThrowNotFoundException() {
        String shortenedUrl = "shortened fullUrl";

        given(urlRepository.findByShortenedUrl(shortenedUrl))
                .willReturn(Optional.empty());

        thenExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> urlService.findFullUrl(shortenedUrl))
                .extracting("exceptionMessage")
                .isEqualTo(ExceptionMessage.URL_NOT_FOUND);

        verify(urlRepository).findByShortenedUrl(shortenedUrl);
    }

    @Test
    void givenValidShortenedUrlWhenGetUrlDetailThenReturnUrlDetail() {
        String shortenedUrl = "shortened fullUrl";
        Url url = UrlMother.createUrl();

        given(urlRepository.findByShortenedUrl(shortenedUrl)).willReturn(Optional.of(url));

        UrlDetailResponse result = urlService.getUrlDetail(shortenedUrl);

        verify(urlRepository).findByShortenedUrl(shortenedUrl);

        then(result).usingRecursiveComparison().isEqualTo(url);
    }

    @Test
    void givenInvalidShortenedUrlWhenGetUrlDetailThenThrowNotFoundException() {
        String shortenedUrl = "shortened fullUrl";

        given(urlRepository.findByShortenedUrl(shortenedUrl))
                .willReturn(Optional.empty());

        thenExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> urlService.getUrlDetail(shortenedUrl))
                .extracting("exceptionMessage")
                .isEqualTo(ExceptionMessage.URL_NOT_FOUND);

        verify(urlRepository).findByShortenedUrl(shortenedUrl);
    }
}
