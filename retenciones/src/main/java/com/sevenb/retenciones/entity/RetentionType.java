package com.sevenb.retenciones.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "retention_type")
public class RetentionType {

    @Id
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double aliquot;
    @Column(nullable = true)
    private Double reducedAliquot;
    @Column(nullable = true)
    private Double minimumAmount;

    public RetentionType() {
        //No-args constructor
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

    public Double getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(Double minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public Double getReducedAliquot() {
        return reducedAliquot;
    }

    public void setReducedAliquot(Double reducedAliquot) {
        this.reducedAliquot = reducedAliquot;
    }

    @Override
    public String toString() {
        return "{\"RetentionType\":{"
            + "\"id\":\"" + id + "\""
            + ", \"description\":\"" + description + "\""
            + ", \"aliquot\":\"" + aliquot + "\""
            + ", \"reducedAliquot\":\"" + reducedAliquot + "\""
            + ", \"minimumAmount\":\"" + minimumAmount + "\""
            + "}}";
    }
}
