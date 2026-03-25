package com.enesincekara.bitpath.urlshortener.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "click_events")
public class ClickEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String shortCode;
    private String ipAddress;
    private LocalDateTime createdAt;

    protected ClickEvent() {}

    public ClickEvent(String shortCode, String ipAddress) {
        this.shortCode = shortCode;
        this.ipAddress = ipAddress;
        this.createdAt = LocalDateTime.now();
    }
}
