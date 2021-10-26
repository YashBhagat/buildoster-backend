package com.buildoster.service;

import com.buildoster.model.ProductDescription;

import java.util.List;

public interface ProductDescriptionService {
    public ProductDescription addProductDescription(ProductDescription productDescription);
    public List<ProductDescription> findAllDescByProductId(Long id);
}
