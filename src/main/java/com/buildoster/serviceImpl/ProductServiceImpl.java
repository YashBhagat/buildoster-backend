package com.buildoster.serviceImpl;

import com.buildoster.model.Product;
import com.buildoster.repository.ProductRepository;
import com.buildoster.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product addProductDetails(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProductList() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long product_id) {
        return productRepository.findById(product_id);
    }

    @Override
    public List<Product> getProductListBySubCategoryId(Long subCategory_id) {
        return productRepository.findBySubCategoryId(subCategory_id);
    }
}
