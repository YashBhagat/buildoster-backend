package com.buildoster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "order_date",nullable = false)
    private String order_date;
    @Column(name = "transaction_type",nullable = false)
    private String transaction_type;
    @Column(name = "order_status", nullable = false)
    private String order_status;
    @Column(name = "user_id", nullable = false)
    private Long user_id;
    @Column(name = "address_id", nullable = false)
    private Long address_id;

    @OneToMany(mappedBy="order_id",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<OrderList> orderLists;

    public Order() {
    }

    public Order(String order_date, String transaction_type, String order_status, Long user_id, Long address_id) {
        this.order_date = order_date;
        this.transaction_type = transaction_type;
        this.order_status = order_status;
        this.user_id = user_id;
        this.address_id = address_id;
    }

     @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            updatable = false,
            insertable = false
    )
    private User user;

     //@]JsonIgnore
    @ManyToOne(targetEntity = Address.class)
    @JoinColumn(
            name = "address_id",
            nullable = false,
            updatable = false,
            insertable = false
    )
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<OrderList> getOrderLists() {
        return orderLists;
    }

    public void setOrderLists(Set<OrderList> orderLists) {
        this.orderLists = orderLists;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_date='" + order_date + '\'' +
                ", transaction_type='" + transaction_type + '\'' +
                ", order_status='" + order_status + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
