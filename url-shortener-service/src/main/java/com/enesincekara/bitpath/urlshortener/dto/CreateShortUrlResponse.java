package com.enesincekara.bitpath.urlshortener.dto;

public class CreateShortUrlResponse {

    private String shortCode;

    public CreateShortUrlResponse(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getShortCode() {
        return shortCode;
    }
}