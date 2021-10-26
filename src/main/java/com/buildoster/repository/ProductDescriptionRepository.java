package com.buildoster.repository;

import com.buildoster.model.ProductDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDescriptionRepository extends JpaRepository<ProductDescription,Long> {
    public List<ProductDescription> findByProductId(Long id);
}
