package com.ecommerce.demo.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductDto implements Serializable {
    private int id_product;
    private String name;
    private double price;
    private String description;
    private String category;
    private List<String> images;
    private List<SizeStockDto> sizes;
}
