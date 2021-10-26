package com.buildoster.serviceImpl;

import com.buildoster.model.Category;
import com.buildoster.repository.CategoryRepository;
import com.buildoster.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategoryList() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long category_id) {
        return categoryRepository.findById(category_id);
    }
}
