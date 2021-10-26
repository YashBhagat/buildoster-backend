package com.buildoster.model;

import javax.persistence.*;

@Entity
@Table(name = "ratting")
public class Ratting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "ratting", nullable = false)
    private int ratting;
    @Column(name = "product_id", nullable = false)
    private Long product_id;
    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",insertable = false,updatable = false,nullable = false)
    private User user;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id",insertable = false,updatable = false,nullable = false)
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRatting() {
        return ratting;
    }

    public void setRatting(int ratting) {
        this.ratting = ratting;
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
