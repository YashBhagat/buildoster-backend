package com.buildoster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "product_category")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "product_category_name",nullable = false)
    private String product_category_name;
    @Column(name = "sub_category_id", nullable = false)
    private Long sub_category_id;

    @JsonIgnore
    @ManyToOne(targetEntity = SubCategory.class)
    @JoinColumn(name = "sub_category_id",insertable = false,updatable = false,nullable = false)
    private SubCategory subCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_category_name() {
        return product_category_name;
    }

    public void setProduct_category_name(String product_category_name) {
        this.product_category_name = product_category_name;
    }

    public Long getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(Long sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}
