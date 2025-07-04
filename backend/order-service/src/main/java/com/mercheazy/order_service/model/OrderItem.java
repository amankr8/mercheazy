package com.mercheazy.order_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oi_id")
    private Long id;

    @Column(name = "oi_product_id", nullable = false)
    private Long productId;

    @Column(name = "oi_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "oi_price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oi_order_id", nullable = false)
    @JsonManagedReference
    private Order order;
}
