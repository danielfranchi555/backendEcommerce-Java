package com.ecommerce.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private String name;
    private String last_name;
    private String address;
    private String phone;
    private String email;
}
