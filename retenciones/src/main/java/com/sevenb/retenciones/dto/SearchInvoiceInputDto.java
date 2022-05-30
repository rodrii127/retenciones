package com.sevenb.retenciones.dto;

import java.time.LocalDate;

public class SearchInvoiceInputDto {
    LocalDate starDate ;
    LocalDate endDate;
    Boolean impacted;
    Long idProvider;

    public SearchInvoiceInputDto(LocalDate starDate, LocalDate endDate, Boolean impacted, Long idProvider) {
        this.starDate = starDate;
        this.endDate = endDate;
        this.impacted = impacted;
        this.idProvider = idProvider;
    }

    public LocalDate getStarDate() {
        return starDate;
    }

    public void setStarDate(LocalDate starDate) {
        this.starDate = starDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getImpacted() {
        return impacted;
    }

    public void setImpacted(Boolean impacted) {
        this.impacted = impacted;
    }

    public Long getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Long idProvider) {
        this.idProvider = idProvider;
    }
}
