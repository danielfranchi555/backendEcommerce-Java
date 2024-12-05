package com.ecommerce.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_cartDetail;
    private int quantity;
    private double unit_price;
    private double total_amount;

    @OneToOne()
    @JoinColumn(name = "cart_id",referencedColumnName = "id_cart")
    private Cart cart;

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id_product")
    private Product product;

}
