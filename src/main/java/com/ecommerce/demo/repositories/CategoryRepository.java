package com.ecommerce.demo.repositories;

import com.ecommerce.demo.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    @Query("SELECT c FROM Category c WHERE c.name_category = :name")
    Category findByName_category(@Param("name") String name);}
