package com.buildoster.service;

import com.buildoster.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Product addProductDetails(Product product);
    public List<Product> getAllProductList();
    public Optional<Product> getProductById(Long product_id);
    public List<Product> getProductListBySubCategoryId(Long subCategory_id);
}
