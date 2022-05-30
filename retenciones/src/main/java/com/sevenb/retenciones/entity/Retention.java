package com.sevenb.retenciones.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "retention",uniqueConstraints = {@UniqueConstraint(columnNames =
        {"number","retention_type_id","company_id"})})
public class Retention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long number;
    @Column(nullable = false)
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    private RetentionType retentionType;
    @Column(nullable = false)
    private Double retentionAmount;
    @ManyToOne
    private Company company;

    @ManyToOne
    private Provider provider;

    public Retention() {
    }


    public Retention(Long id, Long number, LocalDate date, RetentionType retentionType, Double retentionAmount, Company company, Provider provider) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.retentionType = retentionType;
        this.retentionAmount = retentionAmount;
        this.company = company;
        this.provider = provider;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
