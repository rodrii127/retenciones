package com.sevenb.retenciones.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "provider")
public class Provider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    @Column(nullable = false)
    private String cuit;
    private String address;
    private String phone;
    private String fiscalCondition;
    private Boolean isAgreement = Boolean.FALSE;
    private Boolean isIibbExcept = Boolean.FALSE;
    private Boolean isMunicipalityExcept = Boolean.FALSE;

    @ManyToOne
    private Company company;

    public Provider() {
        //No-args constructor
    }

    public Provider(Long id, String companyName, String cuit, String address, String phone, String fiscalCondition, Company company) {
        this.id = id;
        this.companyName = companyName;
        this.cuit = cuit;
        this.address = address;
        this.phone = phone;
        this.fiscalCondition = fiscalCondition;
        this.company = company;
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

    public Boolean getAgreement() {
        return isAgreement;
    }

    public void setAgreement(Boolean agreement) {
        isAgreement = agreement;
    }

    public Boolean getIibbExcept() {
        return isIibbExcept;
    }

    public void setIibbExcept(Boolean iibbExcept) {
        isIibbExcept = iibbExcept;
    }

    public Boolean getMunicipalityExcept() {
        return isMunicipalityExcept;
    }

    public void setMunicipalityExcept(Boolean municipalityExcept) {
        isMunicipalityExcept = municipalityExcept;
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
            + ", \"isAgreement\":\"" + isAgreement + "\""
            + ", \"isIibbExcept\":\"" + isIibbExcept + "\""
            + ", \"isMunicipalityExcept\":\"" + isMunicipalityExcept + "\""
            + ", \"company\":" + company
            + "}}";
    }
}

