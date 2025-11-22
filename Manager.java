package com.realestate.model;

public class Manager extends AbstractUser {
    public Manager() { this.role = "MANAGER"; }
    public Manager(int id, String name, String email) {
        super(id, name, email, "MANAGER");
    }

    @Override
    public String dashboardGreeting() {
        return "Welcome Manager: " + name;
    }

    // Manager-specific methods
}
