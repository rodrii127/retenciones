package com.sevenb.retenciones.dto;

import com.sevenb.retenciones.entity.RetentionType;

import java.time.LocalDate;
import java.util.List;

public class RetentionInputDto {
     List<Long> idIvoices;
     LocalDate startDate;
     Long idRetentionType;

     public List<Long> getIdIvoices() {
          return idIvoices;
     }

     public void setIdIvoices(List<Long> idIvoices) {
          this.idIvoices = idIvoices;
     }

     public LocalDate getStartDate() {
          return startDate;
     }

     public void setStartDate(LocalDate startDate) {
          this.startDate = startDate;
     }

     public Long getIdRetentionType() {
          return idRetentionType;
     }

     public void setIdRetentionType(Long idRetentionType) {
          this.idRetentionType = idRetentionType;
     }
}
