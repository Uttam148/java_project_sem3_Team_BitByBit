# Online Real Estate Management System  
**Java GUI-Based Desktop Application (Swing + JDBC)**

## Overview  
The **Online Real Estate Management System** is a Java-based desktop application built to manage property listings, tenants, rental agreements, and administrative tasks. It supports three roles—**Managers**, **Tenants**, and **Administrators**—allowing each to efficiently handle listings, applications, users, and system settings.

This project applies core **Java OOP principles**, structured modular design, **database-backed operations**, and **JDBC connectivity**.

---

## Features  

### Property Management
- Add, edit, delete, and view properties  
- Property status tracking (available, booked, occupied)  
- Categorization based on type (rental, sale, etc.)

### Tenant & Rental Workflow
- Tenant registration and profile management  
- Rental application submission and approval  
- Rental agreement generation and tracking

### Administrative Controls
- User account management (admin/manager/tenant)  
- Role-based access and system configurations  
- Monitoring and audit features

### GUI
- Built with **Java Swing**  
- Intuitive navigation and clean UI layouts  
- Input validation and exception handling

---

## Technologies Used  
- Java (Core + OOP)  
- Java Swing (GUI)  
- JDBC (Database Connectivity)  
- MySQL/PostgreSQL (Database)  
- Modular OOP Architecture

---

## Project Structure  
```
/src
 ├── ui/                     # Swing GUI screens and components
 ├── model/                  # Entity classes (Property, User, Tenant, Agreement...)
 ├── dao/                    # JDBC Data Access Objects
 ├── service/                # Business logic layer
 ├── util/                   # Utilities (DB connection, validation)
 └── main/                   # Application startup
```

---

## Database Schema (Conceptual)

### Tables Included
- users — user accounts with roles  
- properties — property listings  
- tenants — tenant details linked to users  
- rental_applications — application workflow  
- rental_agreements — finalized agreements  

### Core Relationships
- One user → one tenant (optional)  
- One property → multiple applications  
- One property + one tenant → rental agreement  

---

## JDBC Integration  
- Centralized DBConnection class  
- DAO classes for CRUD operations  
- Prepared statements for SQL injection prevention  
- Standardized error handling  

---

## Current Review Progress  
### 1. Project Structure Setup
- Modular package design established

### 2. OOP & Core Java Implementation
- Encapsulated entity classes  
- Constructors, accessors, and polymorphic behavior

### 3. Database Schema Design
- ER structure finalized  
- Table definitions prepared

### 4. JDBC Setup
- Connection established  
- Basic CRUD operations tested

### 5. DAO Layer Development
- CRUD operations for properties and users  
- Abstracted DB operations

---

## How to Run  
1. Clone the repository  
2. Open in IntelliJ/Eclipse/NetBeans  
3. Configure DB credentials in `DBConnection.java`  
4. Start MySQL/PostgreSQL server  
5. Run the `Main` class  

---

## Future Enhancements  
- Advanced search and filtering  
- Property image upload  
- Payment tracking  
- Reporting dashboards  
- Migration to JavaFX or web version  


