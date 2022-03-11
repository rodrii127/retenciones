package com.sevenb.retenciones.entity;

public class RetentionType {

    private Long id;
    private String description;
    private Double aliquot;

    public RetentionType() {
    }

    public RetentionType(Long id, String description, Double aliquot) {
        this.id = id;
        this.description = description;
        this.aliquot = aliquot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAliquot() {
        return aliquot;
    }

    public void setAliquot(Double aliquot) {
        this.aliquot = aliquot;
    }
}
