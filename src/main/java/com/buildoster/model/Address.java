package com.buildoster.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "street_address_1", nullable = false)
    private String street_address_1;
    @Column(name = "streetAddress_2", nullable = false)
    private String street_address_2;
    @Column(name = "phone_number", nullable = false)
    private String phone_number;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "pin_code", nullable = false)
    private int pin_code;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            updatable = false,
            insertable = false
    )
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet_address_1() {
        return street_address_1;
    }

    public void setStreet_address_1(String street_address_1) {
        this.street_address_1 = street_address_1;
    }

    public String getStreet_address_2() {
        return street_address_2;
    }

    public void setStreet_address_2(String street_address_2) {
        this.street_address_2 = street_address_2;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPin_code() {
        return pin_code;
    }

    public void setPin_code(int pin_code) {
        this.pin_code = pin_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
}
