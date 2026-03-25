package com.enesincekara.bitpath.analyticsservice.message;

public class ClickEventMessage {
    private String shortCode;
    private String ipAddress;

    public ClickEventMessage() {}

    public String getShortCode() {
        return shortCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
