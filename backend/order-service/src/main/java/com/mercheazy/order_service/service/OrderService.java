package com.mercheazy.order_service.service;

import com.mercheazy.order_service.model.Order;
import com.mercheazy.order_service.dto.OrderRequestDto;

import java.util.List;

public interface OrderService {

    Order placeOrder(OrderRequestDto orderRequestDto);

    Order updateOrder(Long orderId, OrderRequestDto orderRequestDto);

    Order changeOrderStatus(Long orderId, String status);

    Order getOrderById(Long orderId);

    List<Order> getOrdersByUser(Long userId);
}
