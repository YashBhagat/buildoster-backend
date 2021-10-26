package com.buildoster.jwtpayload.request;

import org.springframework.web.multipart.MultipartFile;


public class ProductDescriptionRequest {
    private String description_headline;
    private String description;
    private Long product_id;
    private MultipartFile multipartFile;

    public ProductDescriptionRequest(String description_headline, String description, Long product_id, MultipartFile multipartFile) {
        this.description_headline = description_headline;
        this.description = description;
        this.product_id = product_id;
        this.multipartFile = multipartFile;
    }

    public String getDescription_headline() {
        return description_headline;
    }

    public void setDescription_headline(String description_headline) {
        this.description_headline = description_headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    @Override
    public String toString() {
        return "ProductDescriptionRequest{" +
                "description_headline='" + description_headline + '\'' +
                ", description='" + description + '\'' +
                ", product_id=" + product_id +
                ", multipartFile=" + multipartFile +
                '}';
    }
}
