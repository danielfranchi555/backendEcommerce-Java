package com.ecommerce.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_category;

    private String name_category;

    @OneToMany(mappedBy = "category")
    List<Product> products;
}
