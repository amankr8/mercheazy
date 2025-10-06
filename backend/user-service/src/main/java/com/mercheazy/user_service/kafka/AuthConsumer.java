package com.mercheazy.user_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercheazy.user_service.dto.UserRequestDto;
import com.mercheazy.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthConsumer {

    private final ObjectMapper objectMapper;
    private final UserService userService;

    @KafkaListener(topics = "${spring.kafka.topics.auth}", groupId = "user-service-group")
    public void consumeAuthEvent(String message) {
        try {
            log.info("Received auth event: {}", message);

            UserRequestDto userRequestDto = objectMapper.readValue(message, UserRequestDto.class);
            userService.createUser(userRequestDto);
        } catch (Exception e) {
            log.error("Failed to process auth event: {}", message, e);
        }
    }
}
