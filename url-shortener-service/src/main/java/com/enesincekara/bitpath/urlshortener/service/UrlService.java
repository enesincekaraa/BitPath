package com.enesincekara.bitpath.urlshortener.service;

import com.enesincekara.bitpath.urlshortener.dto.CreateShortUrlResponse;
import com.enesincekara.bitpath.urlshortener.entity.ShortUrlEntity;
import com.enesincekara.bitpath.urlshortener.exception.UrlNotFoundException;
import com.enesincekara.bitpath.urlshortener.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlService {

    private final ShortUrlRepository repository;
    private final StringRedisTemplate redisTemplate;

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
        String cachedUrl = redisTemplate.opsForValue().get(shortCode);
        if (cachedUrl != null) {
            log.info("Cache hit for short code: {}", shortCode);
            return cachedUrl;
        }
        log.info("Cache miss for short code: {}", shortCode);

        ShortUrlEntity entity = repository.findById(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Short code not found"));

        String originalUrl = entity.getOriginalUrl();
        redisTemplate.opsForValue().set(shortCode, originalUrl,10, TimeUnit.MINUTES);
        return originalUrl;
    }


    private String generateShortCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
