package com.enesincekara.bitpath.urlshortener.service;

import com.enesincekara.bitpath.urlshortener.dto.CreateShortUrlResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlService {

    public CreateShortUrlResponse shortenUrl(String originalUrl) {
        String shortenCode = UUID.randomUUID().toString().substring(0, 8);
        return new CreateShortUrlResponse(shortenCode);
    }
}
