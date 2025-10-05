package com.mercheazy.order_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mercheazy.order_service.dto.OrderResponseDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "merch_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mo_id")
    private Long id;

    @Column(name = "mo_user_id", nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    @Column(name = "mo_status", nullable = false)
    private OrderStatus status;

    @CreationTimestamp
    @Column(name = "mo_create_date", nullable = false, updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "mo_update_date", nullable = false)
    private Date updateDate;

    public enum OrderStatus {
        PLACED, CREATED, SHIPPED, DELIVERED, CANCELLED
    }

    public OrderResponseDto toDto() {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(this.id);
        dto.setUserId(this.userId);
        dto.setOrderItems(orderItems.stream().map(OrderItem::toDto).toList());
        dto.setStatus(this.status);
        dto.setCreateDate(this.createDate);
        dto.setUpdateDate(this.updateDate);
        return dto;
    }
}
