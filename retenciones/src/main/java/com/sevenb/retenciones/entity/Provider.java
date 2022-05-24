package com.sevenb.retenciones.entity;

import javax.persistence.*;

@Entity
@Table(name = "provider")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    @Column(nullable = false)
    private String cuit;
    private String address;
    private String phone;
    private String fiscalCondition;

    @ManyToOne
    private Company company;

    public Provider() {
        //No-args constructor
    }

    public Provider(Long id, String companyName, String cuit, String address, String phone, String fiscalCondition) {
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "{\"Provider\":{"
            + "\"id\":\"" + id + "\""
            + ", \"companyName\":\"" + companyName + "\""
            + ", \"cuit\":\"" + cuit + "\""
            + ", \"address\":\"" + address + "\""
            + ", \"phone\":\"" + phone + "\""
            + ", \"fiscalCondition\":\"" + fiscalCondition + "\""
            + ", \"company\":" + company
            + "}}";
    }
}
