package com.buildoster.repository;

import com.buildoster.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    //@Query("from Product where sub_category_id = :id")
    public List<Product> findBySubCategoryId(Long id);
}
