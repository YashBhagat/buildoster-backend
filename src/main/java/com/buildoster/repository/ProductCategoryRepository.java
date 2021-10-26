package com.buildoster.repository;

import com.buildoster.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {
    //@Query("from ProductCategory p where p.sub_category_id= :sub_cat_id")
    public List<ProductCategory> findBySubCategoryId(Long sub_cat_id);
}
