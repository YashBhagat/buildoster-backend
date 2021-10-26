package com.buildoster.serviceImpl;

import com.buildoster.model.Image;
import com.buildoster.repository.ImageRepository;
import com.buildoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public Image addImages(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public List<Image> getProductImage(Long product_id) {
        return imageRepository.findByProductId(product_id);
    }
}
