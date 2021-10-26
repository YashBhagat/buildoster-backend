package com.buildoster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "order_list")
public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "product_price", nullable = false)
    private Double product_price;
    @Column(name = "total_price", nullable = false)
    private Double total_price;
    @Column(name = "order_status", nullable = false)
    private String order_status;
    @Column(name = "product_id", nullable = false)
    private Long product_id;
    @Column(name = "order_id", nullable = false)
    private Long order_id;

    public OrderList() {
    }

    public OrderList(int quantity, Double product_price, Double total_price, String order_status, Long product_id, Long order_id) {
        this.quantity = quantity;
        this.product_price = product_price;
        this.total_price = total_price;
        this.order_status = order_status;
        this.product_id = product_id;
        this.order_id = order_id;
    }

    @JsonIgnore
    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(
            name = "order_id",
            nullable = false,
            updatable = false,
            insertable = false
    )
    private Order order;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
