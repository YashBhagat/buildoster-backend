package com.buildoster.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "password_reset_token")
public class PasswordResetToket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "token", nullable = false)
    private String token;
    @Column(name = "expiry_date", nullable = false)
    private Date expiry_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }
}
