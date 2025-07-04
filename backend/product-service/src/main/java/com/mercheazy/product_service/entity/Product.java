package com.mercheazy.product_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "merch_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mp_id")
    private Long id;

    @Column(name = "mp_name")
    private String name;

    @Column(name = "mp_description")
    private String description;

    @Column(name = "mp_price")
    private Double price;

    @Column(name = "mp_stock")
    private Integer stock;

    @CreationTimestamp
    @Column(name = "mp_create_date", nullable = false, updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "mp_update_date", nullable = false)
    private Date updateDate;

    public ProductResponseDto toResponseDto() {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(this.id);
        responseDto.setName(this.name);
        responseDto.setDescription(this.description);
        responseDto.setPrice(this.price);
        responseDto.setStock(this.stock);
        responseDto.setCreateDate(this.createDate);
        responseDto.setUpdateDate(this.updateDate);
        return responseDto;
    }
}
