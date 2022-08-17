package com.sevenb.retenciones.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "point_sale", nullable = false)
    private Integer pointSale;
    @Column(nullable = false)
    private Long number;
    @ManyToOne
    private Provider provider;
    @Column(nullable = false)
    private LocalDate date;

    private Double engraved;
    private Double exempt;
    private Double iva105;
    private Double iva21;
    private Double iibb;
    private Double taxedOthers;
    private Double municipality;
    private Boolean impacted;
    @ManyToOne
    private Company company;

    public Invoice() {
    }

    public Invoice(Integer pointSale, Long number, Provider provider, LocalDate date, Double engraved, Double exempt, Double iva105, Double iva21, Double iibb, Double taxedOthers, Double municipality, Boolean impacted, Company company) {

        this.pointSale = pointSale;
        this.date = date;
        this.number = number;
        this.provider = provider;
        this.engraved = engraved;
        this.exempt = exempt;
        this.iva105 = iva105;
        this.iva21 = iva21;
        this.iibb = iibb;
        this.taxedOthers = taxedOthers;
        this.municipality = municipality;
        this.impacted = impacted;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPointSale() {
        return pointSale;
    }

    public void setPointSale(Integer pointSale) {
        this.pointSale = pointSale;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {

        this.provider = provider;
    }

    public Double getEngraved() {
        return engraved;
    }

    public void setEngraved(Double engraved) {
        this.engraved = engraved;
    }

    public Double getExempt() {
        return exempt;
    }

    public void setExempt(Double exempt) {
        this.exempt = exempt;
    }

    public Double getIva105() {
        return iva105;
    }

    public void setIva105(Double iva105) {
        this.iva105 = iva105;
    }

    public Double getIva21() {
        return iva21;
    }

    public void setIva21(Double iva21) {
        this.iva21 = iva21;
    }

    public Double getIibb() {
        return iibb;
    }

    public void setIibb(Double iibb) {
        this.iibb = iibb;
    }

    public Double getTaxedOthers() {
        return taxedOthers;
    }

    public void setTaxedOthers(Double taxedOthers) {
        this.taxedOthers = taxedOthers;
    }

    public Double getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Double municipality) {
        this.municipality = municipality;
    }

    public Boolean getImpacted() {
        return impacted;
    }

    public void setImpacted(Boolean impacted) {
        this.impacted = impacted;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Double calculateBase() {
        return engraved + exempt;
    }

    public Double calculateTotal() {
        return engraved + exempt + iva105 + iva21 + iibb + taxedOthers + municipality;
    }

}
