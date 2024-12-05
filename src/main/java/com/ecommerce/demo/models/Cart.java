package com.ecommerce.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_cart;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartItems> cartItems;
}
