package com.sevenb.retenciones.entity;

public class Retention {

    private Long id;
    private String date;
    private PayOrder payOrder;
    private RetentionType retentionType;
    private Double retentionAmount;


    public Retention() {
    }

    public Retention(Long id, String date, PayOrder payOrder, RetentionType retentionType, Double retentionAmount) {
        this.id = id;
        this.date = date;
        this.payOrder = payOrder;
        this.retentionType = retentionType;
        this.retentionAmount = retentionAmount;
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

    public PayOrder getPayOrder() {
        return payOrder;
    }

    public void setPayOrder(PayOrder payOrder) {
        this.payOrder = payOrder;
    }

    public RetentionType getRetentionType() {
        return retentionType;
    }

    public void setRetentionType(RetentionType retentionType) {
        this.retentionType = retentionType;
    }

    public Double getRetentionAmount() {
        return retentionAmount;
    }

    public void setRetentionAmount(Double retentionAmount) {
        this.retentionAmount = retentionAmount;
    }
}
