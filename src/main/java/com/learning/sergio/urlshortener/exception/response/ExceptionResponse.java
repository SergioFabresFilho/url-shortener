package com.learning.sergio.urlshortener.exception.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExceptionResponse {

    private final String message;
    private final LocalDateTime timestamp;

    public ExceptionResponse(String message) {
        this.message = message;
        timestamp = LocalDateTime.now();
    }
}
