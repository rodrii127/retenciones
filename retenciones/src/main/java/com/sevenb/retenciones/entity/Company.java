package com.sevenb.retenciones.entity;

public class Company {

    private long id;
    private String companyName;
    private String cuit;
    private String address;
    private String phone;
    private String conditionFiscal;

    public Company(long id, String companyName, String cuit, String address, String phone, String conditionFiscal) {
        this.id = id;
        this.companyName = companyName;
        this.cuit = cuit;
        this.address = address;
        this.phone = phone;
        this.conditionFiscal = conditionFiscal;
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

    public String getConditionFiscal() {
        return conditionFiscal;
    }

    public void setConditionFiscal(String conditionFiscal) {
        this.conditionFiscal = conditionFiscal;
    }
}
