package com.buildoster.service;

import com.buildoster.model.SubCategory;

import java.util.List;
import java.util.Optional;

public interface SubCategoryService {
    public SubCategory createSubCategory(SubCategory subCategory);
    public List<SubCategory> getAllSubCategoryList();
    public Optional<SubCategory> findSubCategoryById(Long sub_id);
}
