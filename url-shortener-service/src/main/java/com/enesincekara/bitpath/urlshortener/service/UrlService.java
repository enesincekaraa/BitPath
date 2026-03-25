package com.enesincekara.bitpath.urlshortener.service;

import com.enesincekara.bitpath.urlshortener.dto.CreateShortUrlResponse;
import com.enesincekara.bitpath.urlshortener.entity.ClickEvent;
import com.enesincekara.bitpath.urlshortener.entity.ShortUrlEntity;
import com.enesincekara.bitpath.urlshortener.exception.RateLimitException;
import com.enesincekara.bitpath.urlshortener.exception.UrlNotFoundException;
import com.enesincekara.bitpath.urlshortener.repository.ClickEventRepository;
import com.enesincekara.bitpath.urlshortener.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlService {

    @Value("${app.rate-limit.prefix}")
    private  String RATE_LIMIT_PREFIX;

    @Value("${app.rate-limit.max-requests}")
    private  int MAX_REQUESTS;

    private final ShortUrlRepository repository;
    private final StringRedisTemplate redisTemplate;
    private final ClickEventRepository clickEventRepository;


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
    public String getOriginalUrl(String shortCode,String ipAddress) {
        checkRateLimit(ipAddress);

        String cachedUrl = redisTemplate.opsForValue().get(shortCode);

        if (cachedUrl != null) {
            log.info("Cache hit for short code: {}", shortCode);
            saveClick(shortCode, ipAddress);
            return cachedUrl;
        }
        log.info("Cache miss for short code: {}", shortCode);

        ShortUrlEntity entity = repository.findById(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Short code not found"));

        String originalUrl = entity.getOriginalUrl();
        redisTemplate.opsForValue().set(shortCode, originalUrl,10, TimeUnit.MINUTES);
        saveClick(shortCode, ipAddress);
        return originalUrl;
    }


    public void checkRateLimit(String clientIp) {
        String key = RATE_LIMIT_PREFIX + clientIp;
        String countStr = redisTemplate.opsForValue().get(key);
        int count = (countStr != null) ? Integer.parseInt(countStr) : 0;
        if (count >= MAX_REQUESTS) {
            throw new RateLimitException();
        }
        redisTemplate.opsForValue()
                .set(key, String.valueOf(count + 1), 1, TimeUnit.MINUTES);
    }

    private String generateShortCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private void saveClick(String shortCode, String ip) {
        ClickEvent clickEvent =new ClickEvent(shortCode, ip);
        clickEventRepository.save(clickEvent);
    }

}
