package com.learning.sergio.urlshortener.controller;

import com.learning.sergio.urlshortener.domain.request.ShortenUrlRequest;
import com.learning.sergio.urlshortener.domain.response.FindFullUrlResponse;
import com.learning.sergio.urlshortener.domain.response.ShortenUrlResponse;
import com.learning.sergio.urlshortener.domain.response.UrlDetailResponse;
import com.learning.sergio.urlshortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ShortenUrlResponse shortenUrl(@RequestBody ShortenUrlRequest shortenUrlRequest) {
        return urlService.shortenUrl(shortenUrlRequest);
    }

    @GetMapping("/{shortenedUrl}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FindFullUrlResponse findFullUrl(@PathVariable("shortenedUrl") String shortenedUrl) {
        return urlService.findFullUrl(shortenedUrl);
    }

    @GetMapping("/detail/{shortenedUrl}")
    public UrlDetailResponse getUrlDetail(@PathVariable("shortenedUrl") String shortenedUrl) {
        return urlService.getUrlDetail(shortenedUrl);
    }
}
