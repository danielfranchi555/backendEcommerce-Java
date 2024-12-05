package com.ecommerce.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SizeStockDto implements Serializable {
    private String sizeName;
    private int stock;


    public SizeStockDto(String sizeName, int stock) {
        this.sizeName = sizeName;
        this.stock = stock;
    }
}
