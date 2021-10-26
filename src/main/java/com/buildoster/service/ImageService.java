package com.buildoster.service;

import com.buildoster.model.Image;

import java.util.List;

public interface ImageService {
    public Image addImages(Image image);
    List<Image> getProductImage(Long product_id);
}
