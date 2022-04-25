package com.learning.sergio.urlshortener.domain.response;

import java.time.LocalDateTime;

public final class UrlDetailResponseMother {

	public static UrlDetailResponse createUrlDetailResponse() {
		return new UrlDetailResponse(1L, "shortenedUrl", "fullUrl", 0L, 10L, LocalDateTime.now());
	}

	private UrlDetailResponseMother() {
	}
}
