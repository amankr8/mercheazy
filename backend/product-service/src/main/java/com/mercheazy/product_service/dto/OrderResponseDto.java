package com.mercheazy.product_service.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private List<OrderItemResponseDto> orderItems;
    private String status;
    private Date createDate;
    private Date updateDate;
}
