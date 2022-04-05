package com.sevenb.retenciones.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "retention_type")
public class RetentionType {

    @Id
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
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
