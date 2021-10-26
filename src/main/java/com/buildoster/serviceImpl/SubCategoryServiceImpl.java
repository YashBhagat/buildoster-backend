package com.buildoster.serviceImpl;

import com.buildoster.model.SubCategory;
import com.buildoster.repository.SubCategoryRepository;
import com.buildoster.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Override
    public SubCategory createSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public List<SubCategory> getAllSubCategoryList() {
        return subCategoryRepository.findAll();
    }

    @Override
    public Optional<SubCategory> findSubCategoryById(Long sub_id) {
        return subCategoryRepository.findById(sub_id);
    }
}
