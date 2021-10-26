package com.buildoster.service;

import com.buildoster.model.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {
    public ProductCategory createProductCategory(ProductCategory category);
    public List<ProductCategory> findAll();
    public Optional<ProductCategory> findById(Long prod_cat_id);
    public List<ProductCategory> findBySubACategoryId(Long sub_cat_id);
}
