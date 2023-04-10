package com.sevenb.retenciones.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pay_order")
public class PayOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String payOrderNumber;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    private Provider provider;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Retention> retentionList;

    private String payMode;
    private String payModeNumber;
    @ManyToOne(fetch = FetchType.LAZY)

    private Company company;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Invoice> invoice;

    public PayOrder() {
        //No-args constructor
    }

    public PayOrder(Long id, LocalDate date, Provider provider, List<Retention> retentionList, String payOrderNumber,
                    String payMode, String payModeNumber, Company company, List<Invoice> invoiceList) {
        this.id = id;
        this.date = date;
        this.provider = provider;
        this.retentionList = retentionList;
        this.payOrderNumber = payOrderNumber;
        this.payMode = payMode;
        this.payModeNumber = payModeNumber;
        this.company = company;
        this.invoice = invoiceList;
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

    public List<Invoice> getInvoice() {
        return invoice;
    }

    public void setInvoice(List<Invoice> invoice) {
        this.invoice = invoice;
    }

    /*public Double calculateMunicipality(){
                return (calculateBase() * 0.007);
    }
    public Double calculateIibb(){
        return (calculateBase() * 0.0196);
    }*/

    public Double calculateBase() {
        return invoice.stream()
            .mapToDouble(Invoice::calculateBase)
            .sum();
    }

    public Double calculateTotal() {
        return invoice.stream()
            .mapToDouble(Invoice::calculateTotal)
            .sum();
    }

    public Double calculateTotalWithRetentions() {
        Double retentionTotal = retentionList.stream()
            .mapToDouble(Retention::getRetentionAmount)
            .sum();
        return this.calculateTotal() - retentionTotal;
    }

}
