package com.mercheazy.product_service.entity;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private String description;
    private Integer stock;
    private Double price;
}
