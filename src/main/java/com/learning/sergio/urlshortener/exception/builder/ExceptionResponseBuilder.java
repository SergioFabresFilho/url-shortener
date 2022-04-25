package com.learning.sergio.urlshortener.exception.builder;

import com.learning.sergio.urlshortener.exception.BusinessException;
import com.learning.sergio.urlshortener.exception.response.ExceptionResponse;

public final class ExceptionResponseBuilder {

//    public static <T extends BusinessException> ExceptionResponse buildExceptionResponse(T businessException) {
//        return new ExceptionResponse(businessException.getExceptionMessage().toString());
//    }

    public static ExceptionResponse buildExceptionResponse(BusinessException businessException) {
        return new ExceptionResponse(businessException.getExceptionMessage().toString());
    }

    public static ExceptionResponse buildExceptionResponse(Exception exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    private ExceptionResponseBuilder() {
    }
}
