package com.sevenb.retenciones.security.entity;

import com.sevenb.retenciones.entity.Company;

/**
 * JWT User. Sets user info for token generation
 */
public class JWTUser {

    private String username;
    private String role;
    private Long id;
    private Company company;

    public JWTUser() {
        //No-args constructor
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "{\"JWTUser\":{"
            + "\"username\":\"" + username + "\""
            + ", \"role\":\"" + role + "\""
            + ", \"id\":\"" + id + "\""
            + ", \"company\":\"" + company + "\""
            + "}}";
    }
}
