package com.ecommerce.demo.service.product;

import com.ecommerce.demo.dto.ProductDto;
import com.ecommerce.demo.error.CategoryNotFoundException;
import com.ecommerce.demo.error.ProductNotFoundException;
import com.ecommerce.demo.models.Product;

import java.util.List;

public interface IProductService {
    public List<ProductDto> getProducts();

    public ProductDto getProduct(Integer id) throws ProductNotFoundException;

    public void addProduct(Product product) throws CategoryNotFoundException;

    public void updateProduct(Integer id, Product product) throws ProductNotFoundException, CategoryNotFoundException;

    public void deleteProduct(Integer id) throws ProductNotFoundException;

    public List<ProductDto> findByName(String name);
}
