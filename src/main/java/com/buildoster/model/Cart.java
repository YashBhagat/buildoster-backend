package com.buildoster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "product_id", nullable = false)
    private Long product_id;
    @Column(name = "user_id", nullable = false)
    private Long user_id;
    @JsonIgnore
    //ManyToOne mapping with cart table to user table
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",nullable = false,updatable = false,insertable = false)
    private User user;
    //ManyToOne mapping with cart table to product table
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id",nullable = false,insertable = false,updatable = false)
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
