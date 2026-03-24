package com.enesincekara.bitpath.urlshortener.controller;

import com.enesincekara.bitpath.urlshortener.dto.CreateShortUrlRequest;
import com.enesincekara.bitpath.urlshortener.dto.CreateShortUrlResponse;
import com.enesincekara.bitpath.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
