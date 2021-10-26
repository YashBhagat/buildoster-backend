package com.buildoster.jwtpayload.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private String token;
    //private String type="Bearer";
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String token, String email, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.email = email;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
