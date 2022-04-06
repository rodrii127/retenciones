package com.sevenb.retenciones.security.entity;

/**
 * JWT User. Sets user info for token generation
 */
public class JWTUser {

    private String username;
    private String role;
    private Long id;

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

    @Override
    public String toString() {
        return "{\"JWTUser\":{"
            + "\"username\":\"" + username + "\""
            + ", \"role\":\"" + role + "\""
            + ", \"id\":\"" + id + "\""
            + "}}";
    }
}
