package com.buildoster.serviceImpl;

import com.buildoster.model.ProductCategory;
import com.buildoster.repository.ProductCategoryRepository;
import com.buildoster.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public ProductCategory createProductCategory(ProductCategory category) {
        return productCategoryRepository.save(category);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public Optional<ProductCategory> findById(Long prod_cat_id) {
        return productCategoryRepository.findById(prod_cat_id);
    }

    @Override
    public List<ProductCategory> findBySubACategoryId(Long sub_cat_id) {
        return productCategoryRepository.findBySubCategoryId(sub_cat_id);
    }
}
