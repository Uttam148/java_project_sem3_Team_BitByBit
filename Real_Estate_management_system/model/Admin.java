package com.realestate.model;

public class Admin extends AbstractUser {
    public Admin() { this.role = "ADMIN"; }
    public Admin(int id, String name, String email) {
        super(id, name, email, "ADMIN");
    }

    @Override
    public String dashboardGreeting() {
        return "Welcome Admin: " + name;
    }

    // Admin specific methods can be added here
}
