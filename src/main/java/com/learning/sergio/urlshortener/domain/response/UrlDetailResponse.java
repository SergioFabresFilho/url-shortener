package com.learning.sergio.urlshortener.domain.response;

import java.time.LocalDateTime;

public record UrlDetailResponse(
		Long id,
		String shortenedUrl,
		String fullUrl,
		Long clickCount,
		Long clickCountLimit,
		LocalDateTime dateTimeLimit
) {
}
