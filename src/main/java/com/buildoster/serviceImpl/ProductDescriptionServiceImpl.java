package com.buildoster.serviceImpl;

import com.buildoster.model.ProductDescription;
import com.buildoster.repository.ProductDescriptionRepository;
import com.buildoster.service.ProductDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDescriptionServiceImpl implements ProductDescriptionService {
    @Autowired
    private ProductDescriptionRepository productDescriptionRepository;
    @Override
    public ProductDescription addProductDescription(ProductDescription productDescription) {
        return productDescriptionRepository.save(productDescription);
    }

    @Override
    public List<ProductDescription> findAllDescByProductId(Long id) {
        return productDescriptionRepository.findByProductId(id);
    }
}
