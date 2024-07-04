package com.sevenb.retenciones.dto;

import com.sevenb.retenciones.entity.Company;

/**
 * Dto used in order to obtain Bearer Token payload from Authorization headers.
 */
public class BearerTokenPayloadDto {

    private String userId;
    private Company company;
    private String role;

    public BearerTokenPayloadDto() {
        //No-args constructor.
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{\"BearerTokenPayloadDto\":{"
            + "\"userId\":\"" + userId + "\""
            + ", \"company\":" + company
            + ", \"role\":\"" + role + "\""
            + "}}";
    }
}
