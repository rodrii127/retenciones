package com.sevenb.retenciones.entity;

public class PayOrder {

    private Long id;
    private String date;
    private Long providers;
    private String payOrderNumber;
    private String payMode;
    private String payModeNumber;
    private Company company;

    public PayOrder() {
    }

    public PayOrder(Long id, String date, Long providers, String payOrderNumber, String payMode, String payModeNumber, Company company) {
        this.id = id;
        this.date = date;
        this.providers = providers;
        this.payOrderNumber = payOrderNumber;
        this.payMode = payMode;
        this.payModeNumber = payModeNumber;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getProviders() {
        return providers;
    }

    public void setProviders(Long providers) {
        this.providers = providers;
    }

    public String getPayOrderNumber() {
        return payOrderNumber;
    }

    public void setPayOrderNumber(String payOrderNumber) {
        this.payOrderNumber = payOrderNumber;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getPayModeNumber() {
        return payModeNumber;
    }

    public void setPayModeNumber(String payModeNumber) {
        this.payModeNumber = payModeNumber;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
