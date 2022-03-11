package com.sevenb.retenciones.entity;

public class Invoice {

    private Long id;
    private Double PointSale;
    private Long number;
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

    public Invoice(Long id, Double pointSale, Long number, Provider provider, Double engraved, Double exempt, Double iva105, Double iva21, Double iibb, Double taxedOthers, Double municipality, Boolean impacted) {
        this.id = id;
        PointSale = pointSale;
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
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPointSale() {
        return PointSale;
    }

    public void setPointSale(Double pointSale) {
        PointSale = pointSale;
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
}
