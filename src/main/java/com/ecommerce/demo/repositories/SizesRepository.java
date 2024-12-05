package com.ecommerce.demo.repositories;

import com.ecommerce.demo.models.Sizes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SizesRepository extends CrudRepository <Sizes,Integer> {
  @Query("SELECT s FROM Sizes s WHERE s.size_name = :size_name")
  Optional<Sizes> findBySizeName(@Param("size_name") String size_name);}
