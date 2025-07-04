package com.mercheazy.product_service.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private Date createDate;
    private Date updateDate;
}
