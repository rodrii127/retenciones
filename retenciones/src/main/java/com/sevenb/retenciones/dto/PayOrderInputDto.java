package com.sevenb.retenciones.dto;

import java.time.LocalDate;
import java.util.List;

public class PayOrderInputDto {
    List<Long> idInvoices ;
    LocalDate startDate;


    public List<Long> getIdInvoices() {
        return idInvoices;
    }

    public void setIdInvoices(List<Long> idInvoices) {
        this.idInvoices = idInvoices;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public PayOrderInputDto(List<Long> idInvoices, LocalDate startDate) {
        this.idInvoices = idInvoices;
        this.startDate = startDate;
    }

    public PayOrderInputDto() {
    }
}
