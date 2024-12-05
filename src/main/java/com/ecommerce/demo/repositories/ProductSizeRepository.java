package com.ecommerce.demo.repositories;

import com.ecommerce.demo.models.ProductSize;
import com.ecommerce.demo.models.Sizes;
import org.springframework.data.repository.CrudRepository;

public interface ProductSizeRepository extends CrudRepository<ProductSize,Integer> {

}
