package com.ecommerce.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "please add the name")
    private String name;
    @NotBlank(message = "please add the last name")
    private String last_name;
    @Size(min = 6 , max = 100,message = "password must be between 6 and 100 characters ")
    private String password;
    @NotBlank(message = "please add the address")
    private String address;
    @NotBlank(message = "please add the phone")
    private String phone;
    @NotBlank(message = "please add the email")
    private String email;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "userRoles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Rol> roles;

    @OneToMany(mappedBy = "user")
    private List<Orders> order;


}
