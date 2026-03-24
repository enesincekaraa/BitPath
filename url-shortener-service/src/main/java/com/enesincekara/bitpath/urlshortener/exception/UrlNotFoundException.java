package com.enesincekara.bitpath.urlshortener.exception;

public class UrlNotFoundException extends RuntimeException{
    public UrlNotFoundException(String shortCode) {
        super("Short url not found: " + shortCode);
    }
}
