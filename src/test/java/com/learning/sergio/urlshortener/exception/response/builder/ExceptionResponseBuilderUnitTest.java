package com.learning.sergio.urlshortener.exception.response.builder;

import com.learning.sergio.urlshortener.exception.BusinessException;
import com.learning.sergio.urlshortener.exception.NotFoundException;
import com.learning.sergio.urlshortener.exception.builder.ExceptionResponseBuilder;
import com.learning.sergio.urlshortener.exception.message.ExceptionMessage;
import com.learning.sergio.urlshortener.exception.response.ExceptionResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class ExceptionResponseBuilderUnitTest {

    @Test
    void givenValidBusinessExceptionWhenBuildExceptionResponseThenReturnExceptionResponse() {
        ExceptionMessage exceptionMessage = ExceptionMessage.URL_NOT_FOUND;
        BusinessException businessException = new NotFoundException(exceptionMessage);

        ExceptionResponse exceptionResponse =
                ExceptionResponseBuilder.buildExceptionResponse(businessException);

        then(exceptionResponse.getMessage()).isEqualTo(exceptionMessage.toString());
        then(exceptionResponse.getTimestamp()).isNotNull();
    }

    @Test
    void givenExceptionWhenBuildExceptionResponseThenReturnExceptionResponse() {
        String exceptionMessage = "exception message";
        Exception exception = new Exception(exceptionMessage);

        ExceptionResponse exceptionResponse =
                ExceptionResponseBuilder.buildExceptionResponse(exception);

        then(exceptionResponse.getMessage()).isEqualTo(exceptionMessage);
        then(exceptionResponse.getTimestamp()).isNotNull();
    }
}
