package com.buildoster.repository;

import com.buildoster.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
    public List<Image> findByProductId(Long id);
}
