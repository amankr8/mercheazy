package com.mercheazy.order_service.model;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Date createDate;
    private Date updateDate;
}
