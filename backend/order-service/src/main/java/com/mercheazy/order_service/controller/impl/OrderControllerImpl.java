package com.mercheazy.order_service.controller.impl;

import com.mercheazy.order_service.controller.OrderController;
import com.mercheazy.order_service.dto.OrderRequestDto;
import com.mercheazy.order_service.model.Order;
import com.mercheazy.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public ResponseEntity<?> placeOrder(OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok(orderService.placeOrder(orderRequestDto));
    }

    @Override
    public ResponseEntity<?> updateOrderStatus(Long orderId, String status) {
        Order updatedOrder = orderService.updateOrderStatus(orderId, status);
        messagingTemplate.convertAndSend("/topic/order-status/" + updatedOrder.getUserId(), updatedOrder);
        return ResponseEntity.ok(updatedOrder);
    }

    @Override
    public ResponseEntity<?> getOrderById(Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @Override
    public ResponseEntity<?> getOrdersByUser(Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }
}
