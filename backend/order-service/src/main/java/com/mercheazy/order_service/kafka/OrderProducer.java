package com.mercheazy.order_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercheazy.order_service.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kafka.topics.order}")
    private String orderTopic;

    public void publishOrder(Order order) {
        try {
            String message = objectMapper.writeValueAsString(order);
            kafkaTemplate.send(orderTopic, message);
            log.info("Sent order event to Kafka: {}", message);
        } catch (Exception e) {
            log.error("Failed to send order event: {}", order, e);
        }
    }
}

