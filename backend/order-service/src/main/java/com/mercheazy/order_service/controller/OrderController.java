package com.mercheazy.order_service.controller;

import com.mercheazy.order_service.dto.OrderRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/orders")
public interface OrderController {

    @PostMapping
    ResponseEntity<?> placeOrder(@RequestBody OrderRequestDto orderRequestDto);

    @PutMapping("/{orderId}/status")
    ResponseEntity<?> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status);

    @GetMapping("/{orderId}")
    ResponseEntity<?> getOrderById(@PathVariable Long orderId);

    @GetMapping("/user/{userId}")
    ResponseEntity<?> getOrdersByUser(@PathVariable Long userId);
}
