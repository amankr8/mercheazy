package com.mercheazy.order_service.dto;

import lombok.Data;

@Data
public class OrderItemResponseDto {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Double price;
}
