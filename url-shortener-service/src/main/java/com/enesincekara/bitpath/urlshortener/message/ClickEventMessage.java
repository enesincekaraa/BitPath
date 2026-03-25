package com.enesincekara.bitpath.urlshortener.message;

public class ClickEventMessage {
    private String shortCode;
    private String ipAddress;
    public ClickEventMessage() {}
    public ClickEventMessage(String shortCode, String ipAddress) {
        this.shortCode = shortCode;
        this.ipAddress = ipAddress;
    }
    public String getShortCode() {
        return shortCode;
    }
    public String getIpAddress() {
        return ipAddress;
    }
}
