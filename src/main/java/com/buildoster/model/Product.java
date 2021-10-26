package com.buildoster.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "product_name", nullable = false)
    @NotEmpty(message = "Please provide a product name")
    private String product_name;
    @NotEmpty(message = "Please provide a price")
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "mrp", nullable = false)
    @NotEmpty(message = "Please provide a mrp")
    private double mrp;
    @NotEmpty(message = "Please provide a sku")
    @Column(name = "sku", nullable = false)
    private String sku;
    @NotEmpty(message = "Please provide a product category id")
    @Column(name = "product_category_id", nullable = false)
    private Long product_category_id;
    @NotEmpty(message = "Please provide a category id")
    @Column(name = "category_id", nullable = false)
    private Long category_id;
    @NotEmpty(message = "Please provide a sub category id")
    @Column(name = "sub_category_id", nullable = false)
    private Long sub_category_id;
    @Transient
    private Long configuration_type_id;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id",nullable = false,updatable = false,insertable = false)
    private Category category;

    @ManyToOne(targetEntity = SubCategory.class)
    @JoinColumn(name = "sub_category_id",nullable = false,updatable = false,insertable = false)
    private SubCategory subCategory;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "specification",joinColumns = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name = "configuration_type_id"))
    private Set<ConfigurationType> configurationTypes = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Long getProduct_category_id() {
        return product_category_id;
    }

    public void setProduct_category_id(Long product_category_id) {
        this.product_category_id = product_category_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Long getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(Long sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Set<ConfigurationType> getConfigurationTypes() {
        return configurationTypes;
    }

    public void setConfigurationTypes(Set<ConfigurationType> configurationTypes) {
        this.configurationTypes = configurationTypes;
    }

    public Long getConfiguration_type_id() {
        return configuration_type_id;
    }

    public void setConfiguration_type_id(Long configuration_type_id) {
        this.configuration_type_id = configuration_type_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                ", price=" + price +
                ", mrp=" + mrp +
                ", sku='" + sku + '\'' +
                ", product_category_id=" + product_category_id +
                ", category_id=" + category_id +
                ", sub_category_id=" + sub_category_id +
                '}';
    }
}
