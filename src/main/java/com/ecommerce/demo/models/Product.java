package com.ecommerce.demo.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_product;
    @NotBlank(message = "please add name product")
    private String name;
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than 0")
    private double price;
    @NotBlank(message = "please add description product")
    private String description;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id_category")
    @NotNull(message = "Category cannot be null")
    @Valid // Para validar los campos internos de category
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @NotNull(message = "please add images")
    private List<ProductsImage> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItems> cartItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductSize> productSizes;


}
