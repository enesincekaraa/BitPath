package com.enesincekara.bitpath.urlshortener.exception;

public class RateLimitException extends RuntimeException {
    public RateLimitException() {
        super("Too many requests");
    }
}
