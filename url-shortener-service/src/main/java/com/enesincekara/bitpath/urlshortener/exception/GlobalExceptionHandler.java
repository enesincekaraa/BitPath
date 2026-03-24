package com.enesincekara.bitpath.urlshortener.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleUrlNotFoundException(UrlNotFoundException ex) {
        return ResponseEntity.status(404).body(Map.of(
                "error", "Not Found",
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(RateLimitException.class)
    public ResponseEntity<Map<String,Object>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(429).body(Map.of(
                "error", "Too Many Requests",
                "message", ex.getMessage()
        ));
    }
}
