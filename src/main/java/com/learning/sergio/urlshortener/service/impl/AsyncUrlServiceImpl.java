package com.learning.sergio.urlshortener.service.impl;

import com.learning.sergio.urlshortener.domain.entity.Url;
import com.learning.sergio.urlshortener.repository.UrlRepository;
import com.learning.sergio.urlshortener.service.AsyncUrlService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncUrlServiceImpl implements AsyncUrlService {

	private final UrlRepository urlRepository;

	public AsyncUrlServiceImpl(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}

	@Async
	@Override
	public void incrementClickCount(Long id) {
		try {
			Thread.sleep(5000);
			urlRepository.incrementClickCount(id);
		} catch (Exception ignored) {
		}
	}

	@Async
	@Override
	public void deleteUrl(Url url) {
		try {
			Thread.sleep(5000);
			urlRepository.delete(url);
		} catch (Exception ignored) {
		}
	}
}
