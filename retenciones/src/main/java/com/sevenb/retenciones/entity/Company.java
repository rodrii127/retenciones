package com.sevenb.retenciones.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private Boolean iibb = Boolean.FALSE;
    private Boolean municipalityRet = Boolean.FALSE;
    private String fantasyName;

    public Company() {
        //No-args constructor
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHabilitationMun() {
        return habilitationMun;
    }

    public void setHabilitationMun(String habilitationMun) {
        this.habilitationMun = habilitationMun;
    }

    public String getHabilitationDgr() {
        return habilitationDgr;
    }

    public void setHabilitationDgr(String habilitationDgr) {
        this.habilitationDgr = habilitationDgr;
    }

    public Boolean getIibb() {
        return iibb;
    }

    public void setIibb(Boolean iibb) {
        this.iibb = iibb;
    }

    public Boolean getMunicipalityRet() {
        return municipalityRet;
    }

    public void setMunicipalityRet(Boolean municipalityRet) {
        this.municipalityRet = municipalityRet;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    @Override
    public String toString() {
        return "{\"Company\":{"
            + "\"id\":\"" + id + "\""
            + ", \"companyName\":\"" + companyName + "\""
            + ", \"cuit\":\"" + cuit + "\""
            + ", \"address\":\"" + address + "\""
            + ", \"phone\":\"" + phone + "\""
            + ", \"email\":\"" + email + "\""
            + ", \"fiscalCondition\":\"" + fiscalCondition + "\""
            + ", \"habilitationMun\":\"" + habilitationMun + "\""
            + ", \"habilitationDgr\":\"" + habilitationDgr + "\""
            + ", \"iibb\":\"" + iibb + "\""
            + ", \"municipalityRet\":\"" + municipalityRet + "\""
            + ", \"fantasyName\":\"" + fantasyName + "\""
            + "}}";
    }
}
