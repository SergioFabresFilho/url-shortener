package com.learning.sergio.urlshortener.exception;

import com.learning.sergio.urlshortener.exception.message.ExceptionMessage;

public abstract class BusinessException extends RuntimeException {
    public abstract ExceptionMessage getExceptionMessage();
}
