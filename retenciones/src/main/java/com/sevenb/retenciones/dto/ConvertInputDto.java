package com.sevenb.retenciones.dto;

import java.time.LocalDate;

public class ConvertInputDto {

    private String key;
   private String date;
   private String type;
   private String number;
   private String companyName;
   private String cuit;
   private String amount;
   private String aliquot;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAliquot() {
        return aliquot;
    }

    public void setAliquot(String aliquot) {
        this.aliquot = aliquot;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
