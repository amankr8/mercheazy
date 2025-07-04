package com.mercheazy.user_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "au_id")
    private Long id;

    @Column(name = "au_first_name")
    private String firstName;

    @Column(name = "au_last_name")
    private String lastName;

    @Column(name = "au_email", unique = true, nullable = false)
    private String email;

    @Column(name = "au_phone")
    private String phone;

    @CreationTimestamp
    @Column(name = "au_create_date", updatable = false, nullable = false)
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "au_update_date", nullable = false)
    private Date updateDate;
}
