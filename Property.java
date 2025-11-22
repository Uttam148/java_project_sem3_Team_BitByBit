package com.realestate.model;

public class Property implements Comparable<Property> {
    protected int id;
    protected String title;
    protected String location;
    protected double price;
    protected String status; // available, booked, occupied
    protected String propertyType; // RENTAL or SALE

    public Property() {}

    public Property(int id, String title, String location, double price, String status, String propertyType) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.price = price;
        this.status = status;
        this.propertyType = propertyType;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPropertyType() { return propertyType; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }

    @Override
    public String toString() {
        return id + ": " + title + " (" + propertyType + ") - " + location + " | " + price + " | " + status;
    }

    @Override
    public int compareTo(Property o) {
        return Double.compare(this.price, o.price);
    }
}
