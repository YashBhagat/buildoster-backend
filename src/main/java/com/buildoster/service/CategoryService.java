package com.buildoster.service;

import com.buildoster.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public Category createCategory(Category category);
    public List<Category> getAllCategoryList();
    public Optional<Category> getCategoryById(Long category_id);
}
