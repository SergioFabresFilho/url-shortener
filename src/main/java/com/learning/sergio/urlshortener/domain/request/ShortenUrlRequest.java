package com.learning.sergio.urlshortener.domain.request;

import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

public record ShortenUrlRequest(
		String fullUrl,
		String shortenedUrl,
		@Nullable Long clickCountLimit,
		@Nullable LocalDateTime dateTimeLimit
) {
}
