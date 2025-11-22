package com.realestate.model;

public class TenantUser extends AbstractUser {
    private String contactInfo;

    public TenantUser() { this.role = "TENANT"; }

    public TenantUser(int id, String name, String email, String contactInfo) {
        super(id, name, email, "TENANT");
        this.contactInfo = contactInfo;
    }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    @Override
    public String dashboardGreeting() {
        return "Welcome Tenant: " + name;
    }
}
