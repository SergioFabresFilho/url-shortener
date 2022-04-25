package com.learning.sergio.urlshortener.exception.handler;

import com.learning.sergio.urlshortener.exception.NotFoundException;
import com.learning.sergio.urlshortener.exception.builder.ExceptionResponseBuilder;
import com.learning.sergio.urlshortener.exception.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException(NotFoundException exception) {
        ExceptionResponse exceptionResponse = ExceptionResponseBuilder.buildExceptionResponse(exception);
        log.error(exceptionResponse.toString());

        return exceptionResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleException(Exception exception) {
        ExceptionResponse exceptionResponse = ExceptionResponseBuilder.buildExceptionResponse(exception);
        log.error(exceptionResponse.toString());

        return exceptionResponse;
    }
}
