package com.mercheazy.order_service.controller.impl;

import com.mercheazy.order_service.controller.OrderController;
import com.mercheazy.order_service.dto.OrderRequestDto;
import com.mercheazy.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    public ResponseEntity<?> placeOrder(OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok(orderService.placeOrder(orderRequestDto));
    }

    @Override
    public ResponseEntity<?> updateOrder(Long orderId, OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok(orderService.updateOrder(orderId, orderRequestDto));
    }

    @Override
    public ResponseEntity<?> changeOrderStatus(Long orderId, String status) {
        return ResponseEntity.ok(orderService.changeOrderStatus(orderId, status));
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
