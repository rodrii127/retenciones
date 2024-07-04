package com.sevenb.retenciones.security.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JWTUserDetails implements UserDetails {

    private static final long serialVersionUID = 8263959673102007882L;

    private String username;
    private String token;
    private Long id;
    public Collection<? extends GrantedAuthority> authorities;

    public JWTUserDetails() {
        //No-arg constructor
    }

    public JWTUserDetails(String username, String token, Long id, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.token = token;
        this.id = id;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "{\"JWTUserDetails\":{"
            + "\"username\":\"" + username + "\""
            + ", \"token\":\"" + token + "\""
            + ", \"id\":\"" + id + "\""
            + ", \"authorities\":" + authorities
            + "}}";
    }
}
