package com.enesincekara.bitpath.urlshortener.service;

import com.enesincekara.bitpath.urlshortener.dto.CreateShortUrlResponse;
import com.enesincekara.bitpath.urlshortener.entity.ShortUrlEntity;
import com.enesincekara.bitpath.urlshortener.exception.UrlNotFoundException;
import com.enesincekara.bitpath.urlshortener.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final ShortUrlRepository repository;

    public CreateShortUrlResponse shortenUrl(String originalUrl) {
        String shortCode = generateShortCode();
        ShortUrlEntity entity = new ShortUrlEntity(
                shortCode,
                originalUrl
        );
        ShortUrlEntity saved = repository.save(entity);
        return new CreateShortUrlResponse(
                saved.getShortCode()
        );
    }
    public String getOriginalUrl(String shortCode) {
        ShortUrlEntity entity = repository.findById(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Short code not found"));
        return entity.getOriginalUrl();
    }


    private String generateShortCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
