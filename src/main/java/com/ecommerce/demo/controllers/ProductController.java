package com.ecommerce.demo.controllers;

import com.ecommerce.demo.dto.ProductDto;
import com.ecommerce.demo.error.CategoryNotFoundException;
import com.ecommerce.demo.error.ProductNotFoundException;
import com.ecommerce.demo.models.Product;
import com.ecommerce.demo.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000") //

public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable int id) throws ProductNotFoundException {
        return productService.getProduct(id);
    }

    @PostMapping("")
    public void addProduct(@Valid @RequestBody Product product) throws CategoryNotFoundException {
        productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) throws ProductNotFoundException {
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody Product product) throws CategoryNotFoundException, ProductNotFoundException {
        productService.updateProduct(id, product);
    }

    @GetMapping("/search")
    public List<ProductDto> findByName(@RequestParam String name) {
        return productService.findByName(name);
    }
}
