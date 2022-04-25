package com.learning.sergio.urlshortener.service;

import com.learning.sergio.urlshortener.domain.entity.Url;
import org.springframework.scheduling.annotation.Async;

public interface AsyncUrlService {
	void incrementClickCount(Long id);

	void deleteUrl(Url url);
}
