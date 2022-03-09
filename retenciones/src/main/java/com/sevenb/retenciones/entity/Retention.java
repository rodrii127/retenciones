package com.sevenb.retenciones.entity;

public class Retention {

    private long id;
    private String date;
    private long payOrderNumberFk;
    private long retentionType;
    private double retentionAmount;

    public Retention(long id, String date, long payOrderNumberFk, long retentionType, double retentionAmount) {
        this.id = id;
        this.date = date;
        this.payOrderNumberFk = payOrderNumberFk;
        this.retentionType = retentionType;
        this.retentionAmount = retentionAmount;
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

    public long getPayOrderNumberFk() {
        return payOrderNumberFk;
    }

    public void setPayOrderNumberFk(long payOrderNumberFk) {
        this.payOrderNumberFk = payOrderNumberFk;
    }

    public long getRetentionType() {
        return retentionType;
    }

    public void setRetentionType(long retentionType) {
        this.retentionType = retentionType;
    }

    public double getRetentionAmount() {
        return retentionAmount;
    }

    public void setRetentionAmount(double retentionAmount) {
        this.retentionAmount = retentionAmount;
    }
}
