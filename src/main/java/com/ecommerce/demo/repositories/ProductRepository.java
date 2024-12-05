package com.ecommerce.demo.repositories;

import com.ecommerce.demo.dto.ProductDto;
import com.ecommerce.demo.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findByName(String name);


}
