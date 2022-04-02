package com.sevenb.retenciones.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pay_order")
public class PayOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    private Provider provider;
    private String payOrderNumber;
    private String payMode;
     private String payModeNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;

    public PayOrder() {
    }

    public PayOrder(Date date, Provider provider, String payOrderNumber, String payMode, String payModeNumber, Company company) {
        this.date = date;
        provider = provider;
        this.payOrderNumber = payOrderNumber;
        this.payMode = payMode;
        this.payModeNumber = payModeNumber;
       company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        provider = provider;
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
        company = company;
    }
}
