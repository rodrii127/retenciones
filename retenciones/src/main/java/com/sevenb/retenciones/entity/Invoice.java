package com.sevenb.retenciones.entity;

public class Invoice {

    private long id;
    private double pv;
    private long number;
    private long providerFk;
    private double net;
    private double notTaxed;
    private double iva_105;
    private double iva_21;
    private double iibb;
    private double taxedOthers;
    private double municipality;
    private boolean impacted;

    public Invoice(long id, double pv, long number, long providerFk, double net, double notTaxed, double iva_105, double iva_21, double iibb, double taxedOthers, double municipality, boolean impacted) {
        this.id = id;
        this.pv = pv;
        this.number = number;
        this.providerFk = providerFk;
        this.net = net;
        this.notTaxed = notTaxed;
        this.iva_105 = iva_105;
        this.iva_21 = iva_21;
        this.iibb = iibb;
        this.taxedOthers = taxedOthers;
        this.municipality = municipality;
        this.impacted = impacted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPv() {
        return pv;
    }

    public void setPv(double pv) {
        this.pv = pv;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getProviderFk() {
        return providerFk;
    }

    public void setProviderFk(long providerFk) {
        this.providerFk = providerFk;
    }

    public double getNet() {
        return net;
    }

    public void setNet(double net) {
        this.net = net;
    }

    public double getNotTaxed() {
        return notTaxed;
    }

    public void setNotTaxed(double notTaxed) {
        this.notTaxed = notTaxed;
    }

    public double getIva_105() {
        return iva_105;
    }

    public void setIva_105(double iva_105) {
        this.iva_105 = iva_105;
    }

    public double getIva_21() {
        return iva_21;
    }

    public void setIva_21(double iva_21) {
        this.iva_21 = iva_21;
    }

    public double getIibb() {
        return iibb;
    }

    public void setIibb(double iibb) {
        this.iibb = iibb;
    }

    public double getTaxedOthers() {
        return taxedOthers;
    }

    public void setTaxedOthers(double taxedOthers) {
        this.taxedOthers = taxedOthers;
    }

    public double getMunicipality() {
        return municipality;
    }

    public void setMunicipality(double municipality) {
        this.municipality = municipality;
    }

    public boolean isImpacted() {
        return impacted;
    }

    public void setImpacted(boolean impacted) {
        this.impacted = impacted;
    }
}
