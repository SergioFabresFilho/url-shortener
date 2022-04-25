package com.learning.sergio.urlshortener.exception.message;

public enum ExceptionMessage {
    URL_NOT_FOUND("Url not fount");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
