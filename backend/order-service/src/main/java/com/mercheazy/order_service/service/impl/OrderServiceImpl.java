package com.mercheazy.order_service.service.impl;

import com.mercheazy.order_service.dto.OrderItemRequestDto;
import com.mercheazy.order_service.dto.OrderRequestDto;
import com.mercheazy.order_service.exception.ResourceNotFoundException;
import com.mercheazy.order_service.feign.ProductInterface;
import com.mercheazy.order_service.feign.UserInterface;
import com.mercheazy.order_service.kafka.OrderProducer;
import com.mercheazy.order_service.model.*;
import com.mercheazy.order_service.repository.OrderRepository;
import com.mercheazy.order_service.service.OrderService;
import com.mercheazy.order_service.util.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;
    private final ProductInterface productInterface;
    private final UserInterface userInterface;

    @Transactional
    @Override
    public Order placeOrder(OrderRequestDto orderRequestDto) {
        ResponseEntity<AppUser> userResponse = userInterface.getUserByEmail(AuthUtil.getUserContext());
        if (!userResponse.getStatusCode().is2xxSuccessful() || userResponse.getBody() == null) {
            throw new ResourceNotFoundException("User could not be found or is not logged in.");
        }

        Order order = new Order();
        order.setUserId(userResponse.getBody().getId());
        order.setOrderItems(new ArrayList<>());
        order.setStatus(Order.OrderStatus.PLACED);
        for (OrderItemRequestDto item : orderRequestDto.getOrderItems()) {
            Product product = productInterface.getProductById(item.getProductId()).getBody();
            if (product != null && product.getStock() >= item.getQuantity()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(item.getProductId());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setPrice(product.getPrice());
                orderItem.setOrder(order);
                order.getOrderItems().add(orderItem);
            } else {
                throw new ResourceNotFoundException("Product not available or insufficient stock for product ID: " + item.getProductId());
            }
        }

        Order savedOrder = orderRepository.save(order);

        orderProducer.publishOrder(savedOrder.toDto());

        return savedOrder;
    }

    @Override
    public Order updateOrder(Long orderId, OrderRequestDto orderRequestDto) {
        return null;
    }

    @Override
    public Order changeOrderStatus(Long orderId, String status) {
        return null;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        return List.of();
    }
}
