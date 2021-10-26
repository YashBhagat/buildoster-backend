package com.buildoster.model;

import javax.persistence.*;

@Entity
@Table(name = "product_description")
public class ProductDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "description_headline", nullable = false)
    private String description_headline;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "image_url", nullable = false)
    private String image_url;
    @Column(name = "product_id", nullable = false)
    private Long product_id;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id",nullable = false,updatable = false,insertable = false)
    private Product product;

    public ProductDescription(String description_headline, String description, String image_url, Long product_id) {
        this.description_headline = description_headline;
        this.description = description;
        this.image_url = image_url;
        this.product_id = product_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductDescription{" +
                "id=" + id +
                ", description_headline='" + description_headline + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
