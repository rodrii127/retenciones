package com.sevenb.retenciones.entity;

public class Providers {

    private long id;
    private String companyName;
    private String cuit;
    private String address;
    private String phone;
    private String fiscalCondition;

    public Providers(long id, String companyName, String cuit, String address, String phone, String fiscalCondition) {
        this.id = id;
        this.companyName = companyName;
        this.cuit = cuit;
        this.address = address;
        this.phone = phone;
        this.fiscalCondition = fiscalCondition;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
