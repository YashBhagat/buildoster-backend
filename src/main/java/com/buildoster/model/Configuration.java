package com.buildoster.model;

import javax.persistence.*;

@Entity
@Table(name = "configuration")
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "configuration", nullable = false)
    private String configuration;
    @Column(name = "sub_category_id", nullable = false)
    private Long sub_category_id;

    @ManyToOne(targetEntity = SubCategory.class)
    @JoinColumn(name = "sub_category_id",insertable = false,updatable = false,nullable = false)
    private SubCategory subCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
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

    @Override
    public String toString() {
        return "Configuration{" +
                "id=" + id +
                ", configuration='" + configuration + '\'' +
                ", sub_category_id=" + sub_category_id +
                '}';
    }
}
