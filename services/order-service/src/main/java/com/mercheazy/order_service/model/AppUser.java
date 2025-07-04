package com.mercheazy.order_service.model;

import lombok.Data;

@Data
public class AppUser {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String createDate;
    private String updateDate;
}
