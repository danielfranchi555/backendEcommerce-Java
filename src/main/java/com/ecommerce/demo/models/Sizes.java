package com.ecommerce.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Sizes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_size;
    private String size_name;

    @OneToMany(mappedBy = "size",cascade = CascadeType.ALL)
    List<ProductSize> productSizes;
}
