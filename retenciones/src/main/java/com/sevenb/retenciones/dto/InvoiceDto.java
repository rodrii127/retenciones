package com.sevenb.retenciones.dto;

import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.Provider;

import javax.persistence.*;
import java.time.LocalDate;

public class InvoiceDto {
    private Long id;
    private Integer pointSale;
    private Long number;
    private Long provider;
    private LocalDate date;
    private Double engraved;
    private Double exempt;
    private Double iva105;
    private Double iva21;
    private Double iibb;
    private Double taxedOthers;
    private Double municipality;
    private Boolean impacted;

    public InvoiceDto() {
    }

    public InvoiceDto(Long id, Integer pointSale, Long number, Long provider, LocalDate date, Double engraved, Double exempt, Double iva105, Double iva21, Double iibb, Double taxedOthers, Double municipality, Boolean impacted) {
        this.id = id;
        this.pointSale = pointSale;
        this.number = number;
        this.provider = provider;
        this.date = date;
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

    public Long getProvider() {
        return provider;
    }

    public void setProvider(Long provider) {
        this.provider = provider;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
