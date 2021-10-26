package com.buildoster.jwtpayload.request;

import java.util.Set;

public class SignupRequest {
    private String f_name;
    private String l_name;
    private String password;
    private String phone;
    private String email;
    private Set<String> role;

    public SignupRequest() {
    }

    public SignupRequest(String f_name, String l_name, String password, String phone, String email) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
