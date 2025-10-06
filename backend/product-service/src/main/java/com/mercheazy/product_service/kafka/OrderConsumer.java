package com.mercheazy.product_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercheazy.product_service.dto.OrderResponseDto;
import com.mercheazy.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderConsumer {

    private final ObjectMapper objectMapper;
    private final ProductService productService;

    @KafkaListener(topics = "${spring.kafka.topics.order}", groupId = "product-service-group")
    public void consumeOrderEvent(String message) {
        try {
            log.info("Received order event: {}", message);

            OrderResponseDto orderResponseDto = objectMapper.readValue(message, OrderResponseDto.class);
            for (var item : orderResponseDto.getOrderItems()) {
                productService.updateProductStock(item.getProductId(), -item.getQuantity());
            }
        } catch (Exception e) {
            log.error("Failed to process order event: {}", message, e);
        }
    }
}
