package com.enesincekara.bitpath.urlshortener.controller;

import com.enesincekara.bitpath.urlshortener.dto.CreateShortUrlRequest;
import com.enesincekara.bitpath.urlshortener.dto.CreateShortUrlResponse;
import com.enesincekara.bitpath.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping
    public ResponseEntity<CreateShortUrlResponse> shortenUrl(
            @Valid @RequestBody
            CreateShortUrlRequest createShortUrlRequest) {
        return ResponseEntity.ok(urlService.shortenUrl(createShortUrlRequest.getUrl()));
    }

    @GetMapping("/r/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode,
            HttpServletRequest request) {

        String clientIp = request.getRemoteAddr();

        urlService.checkRateLimit(clientIp);

        String originalUrl = urlService.getOriginalUrl(shortCode);

        return ResponseEntity
                .status(302)
                .location(URI.create(originalUrl))
                .build();
    }
}
