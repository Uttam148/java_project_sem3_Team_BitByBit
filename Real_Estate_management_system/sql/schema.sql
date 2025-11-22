-- SQL schema for the upgraded sample project (MySQL syntax)
CREATE DATABASE IF NOT EXISTS realestate;
USE realestate;

CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    role VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    contact_info VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS properties (
    property_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    price DOUBLE,
    status VARCHAR(50) DEFAULT 'available',
    property_type VARCHAR(20) DEFAULT 'RENTAL',
    monthly_rent DOUBLE,
    deposit DOUBLE,
    lease_term_months INT,
    brokerage_fee DOUBLE
);

CREATE TABLE IF NOT EXISTS tenants (
    tenant_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    contact_info VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS rental_applications (
    application_id INT AUTO_INCREMENT PRIMARY KEY,
    tenant_id INT,
    property_id INT,
    status VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tenant_id) REFERENCES tenants(tenant_id) ON DELETE SET NULL,
    FOREIGN KEY (property_id) REFERENCES properties(property_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS rental_agreements (
    agreement_id INT AUTO_INCREMENT PRIMARY KEY,
    property_id INT,
    tenant_id INT,
    start_date DATE,
    end_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (property_id) REFERENCES properties(property_id) ON DELETE CASCADE,
    FOREIGN KEY (tenant_id) REFERENCES tenants(tenant_id) ON DELETE SET NULL
);

-- sample data
INSERT INTO users (name, email, role, password) VALUES ('Admin User', 'admin@example.com', 'ADMIN', 'password');
INSERT INTO users (name, email, role, password) VALUES ('Manager One', 'manager@example.com', 'MANAGER', 'password');
INSERT INTO users (name, email, role, password) VALUES ('Tenant One', 'tenant@example.com', 'TENANT', 'password');

INSERT INTO properties (title, location, price, status, property_type, monthly_rent, deposit, lease_term_months) VALUES
('Cozy Apartment', 'Delhi', 15000, 'available', 'RENTAL', 15000, 30000, 12),
('Family House', 'Noida', 5000000, 'available', 'SALE', NULL, NULL, NULL, 50000);
