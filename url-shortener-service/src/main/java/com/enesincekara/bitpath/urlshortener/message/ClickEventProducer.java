package com.enesincekara.bitpath.urlshortener.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClickEventProducer {
    private final KafkaTemplate<String,ClickEventMessage>  kafkaTemplate;

    public void sendClickEvent(String shortCode,String ip) {
        kafkaTemplate.send("click-events", new ClickEventMessage(shortCode,ip));
        log.debug("Sent click event with shortCode {} and ip {}", shortCode, ip);
    }
}
