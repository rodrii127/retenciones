package com.sevenb.retenciones.entity;

public class RetentionType {

    private long id;
    private String description;
    private double aliquot;

    public RetentionType(long id, String description, double aliquot) {
        this.id = id;
        this.description = description;
        this.aliquot = aliquot;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAliquot() {
        return aliquot;
    }

    public void setAliquot(double aliquot) {
        this.aliquot = aliquot;
    }
}
