package com.sevenb.retenciones.dto;

import java.time.LocalDate;
import java.util.List;

public class PayOrderInputDto {
    List<Long> idPayOder ;
    LocalDate startDate;

    public List<Long> getIdPayOder() {
        return idPayOder;
    }

    public void setIdPayOder(List<Long> idPayOder) {
        this.idPayOder = idPayOder;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public PayOrderInputDto(List<Long> idPayOder, LocalDate startDate) {
        this.idPayOder = idPayOder;
        this.startDate = startDate;
    }

    public PayOrderInputDto() {
    }
}
