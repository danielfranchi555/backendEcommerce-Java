package com.ecommerce.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProductsImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_productImage;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id_product")
    private Product product;

}
