package com.realestate.model;

public class SaleProperty extends Property {
    private double brokerageFee;

    public SaleProperty() { this.propertyType = "SALE"; }

    public SaleProperty(int id, String title, String location, double price, String status, double brokerageFee) {
        super(id, title, location, price, status, "SALE");
        this.brokerageFee = brokerageFee;
    }

    public double getBrokerageFee() { return brokerageFee; }
    public void setBrokerageFee(double brokerageFee) { this.brokerageFee = brokerageFee; }
}
