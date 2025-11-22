package com.realestate.model;

public class RentalProperty extends Property {
    private double monthlyRent;
    private double deposit;
    private int leaseTermMonths;

    public RentalProperty() { this.propertyType = "RENTAL"; }

    public RentalProperty(int id, String title, String location, double price, String status,
                          double monthlyRent, double deposit, int leaseTermMonths) {
        super(id, title, location, price, status, "RENTAL");
        this.monthlyRent = monthlyRent;
        this.deposit = deposit;
        this.leaseTermMonths = leaseTermMonths;
    }

    public double getMonthlyRent() { return monthlyRent; }
    public void setMonthlyRent(double monthlyRent) { this.monthlyRent = monthlyRent; }

    public double getDeposit() { return deposit; }
    public void setDeposit(double deposit) { this.deposit = deposit; }

    public int getLeaseTermMonths() { return leaseTermMonths; }
    public void setLeaseTermMonths(int leaseTermMonths) { this.leaseTermMonths = leaseTermMonths; }
}
