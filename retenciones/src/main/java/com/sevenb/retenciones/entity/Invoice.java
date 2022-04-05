package com.sevenb.retenciones.entity;

import javax.persistence.*;

@Entity
@Table(name = "invoice")
public class Invoice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer pointSale;
    @Column(nullable = false)
    private Long number;
    @ManyToOne(fetch = FetchType.EAGER)
    private Provider provider;
    private Double engraved;
    private Double exempt;
    private Double iva105;
    private Double iva21;
    private Double iibb;
    private Double taxedOthers;
    private Double municipality;
    private Boolean impacted;

    public Invoice() {
    }

    public Invoice(Integer pointSale, Long number, Provider provider, Double engraved, Double exempt, Double iva105, Double iva21, Double iibb, Double taxedOthers, Double municipality, Boolean impacted) {
        this.pointSale = pointSale;
        this.number = number;
        provider = provider;
        this.engraved = engraved;
        this.exempt = exempt;
        this.iva105 = iva105;
        this.iva21 = iva21;
        this.iibb = iibb;
        this.taxedOthers = taxedOthers;
        this.municipality = municipality;
        this.impacted = impacted;
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
        provider = provider;
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
}
