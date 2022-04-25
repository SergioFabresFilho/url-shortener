package com.learning.sergio.urlshortener.exception;

import com.learning.sergio.urlshortener.exception.message.ExceptionMessage;

public class NotFoundException extends BusinessException {

    private final ExceptionMessage exceptionMessage;

    public NotFoundException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}
