package com.sevenb.retenciones.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "retention")
public class Retention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    private RetentionType retentionType;
    @Column(nullable = false)
    private Double retentionAmount;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Invoice> invoice;

    public Retention() {
    }

    public Retention(Date date, RetentionType retentionType, Double retentionAmount, List<Invoice> invoice) {
        this.date = date;
        this.retentionType = retentionType;
        this.retentionAmount = retentionAmount;
        this.invoice = invoice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RetentionType getRetentionType() {
        return retentionType;
    }

    public void setRetentionType(RetentionType retentionType) {
        this.retentionType = retentionType;
    }

    public Double getRetentionAmount() {
        return retentionAmount;
    }

    public void setRetentionAmount(Double retentionAmount) {
        this.retentionAmount = retentionAmount;
    }

    public List<Invoice> getInvoice() {
        return invoice;
    }

    public void setInvoice(List<Invoice> invoice) {
        this.invoice = invoice;
    }
}
