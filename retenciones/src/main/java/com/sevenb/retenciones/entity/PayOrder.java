package com.sevenb.retenciones.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pay_order")
public class PayOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    private Provider provider;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Retention> retentionList;
    private String payOrderNumber;
    private String payMode;
     private String payModeNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;

    public PayOrder() {
    }

    public PayOrder(Long id,LocalDate date, Provider provider, String payOrderNumber, String payMode, String payModeNumber, Company company, List<Retention> retentionList) {
        this.id = id;
        this.date = date;
        this.provider = provider;
        this.payOrderNumber = payOrderNumber;
        this.payMode = payMode;
        this.payModeNumber = payModeNumber;
       this.company = company;
       this.retentionList = retentionList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
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

    public List<Retention> getRetentionList() {
        return retentionList;
    }

    public void setRetentionList(List<Retention> retentionList) {
        this.retentionList = retentionList;
    }

    public Double calculateMunicipality(){
       final Double[] base = {0D};
        retentionList.forEach(i -> {
            if(i.getRetentionType().getId() == 1L)
                base[0] = base[0] + i.calculateRet();
        });
        return base[0];
    }

    public Double calculateIibb(){
        final Double[] base = {0D};
        retentionList.forEach(i -> {
            if(i.getRetentionType().getId()==2L)
                base[0] = base[0] + i.calculateRet();
        });
        return base[0];
    }
    public Double calculateBase(){
        final Double[] base = {0D};
        retentionList.forEach(i -> {
              base[0] = base[0] + i.calculateBase();
        });
        return base[0];
    }

    public Double calculateTotal(){
        final Double[] base = {0D};
        retentionList.forEach(i -> {
            i.getInvoice().forEach(invoice -> {
                base[0] = base[0] + invoice.calculateTotal();
            });
        });
        return base[0];
    }


}
