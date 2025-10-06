package com.mercheazy.auth_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${spring.kafka.topics.auth}")
    private String authTopic;

    public void publishAuthEvent(Object authEvent) {
        try {
            String message = objectMapper.writeValueAsString(authEvent);
            kafkaTemplate.send(authTopic, message);
            log.info("Sent auth event to Kafka: {}", message);
        } catch (Exception e) {
            log.error("Failed to send auth event: {}", authEvent, e);
        }
    }
}
