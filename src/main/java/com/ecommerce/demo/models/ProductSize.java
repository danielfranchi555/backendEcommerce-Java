package com.ecommerce.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int stock;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Sizes size;




}
