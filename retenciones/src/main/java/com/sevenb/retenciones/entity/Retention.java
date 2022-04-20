package com.sevenb.retenciones.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "retention")
public class Retention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    private RetentionType retentionType;
    @Column(nullable = false)
    private Double retentionAmount;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Invoice> invoice;
    @ManyToOne
    private Company company;

    public Retention() {
    }

    public Retention(LocalDate date, RetentionType retentionType, Double retentionAmount, List<Invoice> invoice, Company company) {
        this.date = date;
        this.retentionType = retentionType;
        this.retentionAmount = retentionAmount;
        this.invoice = invoice;
        this.company = company;
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

    public List<Invoice> getInvoice() {
        return invoice;
    }

    public void setInvoice(List<Invoice> invoice) {
        this.invoice = invoice;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Double calculateBase(){
        final Double[] base = {0D};
        invoice.forEach(i -> {
            base[0] = base[0] + i.getEngraved();});
        return base[0];
    }

    public Double calculateRet(){
         return calculateBase() * retentionType.getAliquot();
    }
}
