package com.enesincekara.bitpath.analyticsservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "click_events")
public class ClickEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String shortCode;

    private String ipAddress;
    private LocalDateTime createdAt;
    protected  ClickEvent() {
    }
    public ClickEvent(String shortCode, String ipAddress) {
        this.shortCode = shortCode;
        this.ipAddress = ipAddress;
        this.createdAt = LocalDateTime.now();

    }

}
