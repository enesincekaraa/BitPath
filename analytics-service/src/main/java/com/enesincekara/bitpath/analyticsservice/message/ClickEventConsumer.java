package com.enesincekara.bitpath.analyticsservice.message;

import com.enesincekara.bitpath.analyticsservice.entity.ClickEvent;
import com.enesincekara.bitpath.analyticsservice.repository.ClickEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClickEventConsumer {
    private final ClickEventRepository repository;

    @KafkaListener(topics = "click-events",groupId = "analytics-group")
    public void consume(ClickEventMessage message) {
        log.info("Received ClickEvent message={}", message);
        ClickEvent event = new ClickEvent(
                message.getShortCode(),
                message.getIpAddress()
        );
        repository.save(event);
        log.info("ClickEvent saved for message={}", event);
    }
}
