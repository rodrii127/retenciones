package com.sevenb.retenciones.entity;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String cuit;
    private String address;
    private String phone;
    private String email;
    private String fiscalCondition;

    private String habilitationMun;

    private String habilitationDgr;

    public Company() {
    }

    public Company(Long id, String companyName, String cuit, String address, String phone, String fiscalCondition) {
        this.id = id;
        this.companyName = companyName;
        this.cuit = cuit;
        this.address = address;
        this.phone = phone;
        this.fiscalCondition = fiscalCondition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFiscalCondition() {
        return fiscalCondition;
    }

    public void setFiscalCondition(String fiscalCondition) {
        this.fiscalCondition = fiscalCondition;
    }
}
