package com.sevenb.retenciones.dto;

import java.time.LocalDate;

public class PayOrderOutputDto {

    private Long id;
    private LocalDate date;
    private String number;
    private String provider;
    private String cuitProvider;
    private String base;
    private String retention;
    private String amountPaid;

    public String getRetention() {
        return retention;
    }

    public void setRetention(String retention) {
        this.retention = retention;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getCuitProvider() {
        return cuitProvider;
    }

    public void setCuitProvider(String cuitProvider) {
        this.cuitProvider = cuitProvider;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }
}
