package com.sevenb.retenciones.entity;

public class PayOrder {

    private long id;
    private String date;
    private long providersFk;
    private String payOrderNumber;
    private String payMode;
    private String payReceiptNumber;
    private long companyFK;

    public PayOrder(long id, String date, long providersFk, String payOrderNumber, String payMode, String payReceiptNumber, long companyFK) {
        this.id = id;
        this.date = date;
        this.providersFk = providersFk;
        this.payOrderNumber = payOrderNumber;
        this.payMode = payMode;
        this.payReceiptNumber = payReceiptNumber;
        this.companyFK = companyFK;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getProvidersFk() {
        return providersFk;
    }

    public void setProvidersFk(long providersFk) {
        this.providersFk = providersFk;
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

    public String getPayReceiptNumber() {
        return payReceiptNumber;
    }

    public void setPayReceiptNumber(String payReceiptNumber) {
        this.payReceiptNumber = payReceiptNumber;
    }

    public long getCompanyFK() {
        return companyFK;
    }

    public void setCompanyFK(long companyFK) {
        this.companyFK = companyFK;
    }
}
