###Online Real Estate Management System

###Java GUI-Based Desktop Application (Swing + JDBC)

##📌 Overview

The Online Real Estate Management System is a Java-based desktop application built to manage property listings, tenants, rental agreements, and administrative tasks. It supports three roles—Managers, Tenants, and Administrators—allowing each to efficiently handle listings, applications, users, and system settings.

This project applies core Java OOP principles, structured modular design, database-backed operations, and JDBC connectivity.

##✨ Features
Property Management

Add, edit, delete, and view properties

Property status tracking (available, booked, occupied)

Categorization based on type (rental, sale, etc.)

Tenant & Rental Workflow

Tenant registration and profile management

Rental application submission and approval

Rental agreement generation and tracking

Administrative Controls

User account management (admin/manager/tenant)

Role-based access and system configurations

Monitoring and audit features

#GUI

Built with Java Swing

Intuitive navigation and clean UI layouts

Input validation and exception handling

##🛠 Technologies Used

Java (Core + OOP)

Java Swing (GUI)

JDBC (Database Connectivity)

MySQL/PostgreSQL (Database)

Modular OOP Architecture

📂 Project Structure
/src
 ├── ui/                     # Swing GUI screens and components
 ├── model/                  # Entity classes (Property, User, Tenant, Agreement...)
 ├── dao/                    # JDBC Data Access Objects
 ├── service/                # Business logic layer
 ├── util/                   # Utilities (DB connection, validation)
 └── main/                   # Application startup

🗄 Database Schema (Conceptual)
Tables Included

users — user accounts with roles

properties — property listings

tenants — tenant details linked to users

rental_applications — application workflow

rental_agreements — finalized agreements

Core Relationships

One user → one tenant (optional)

One property → multiple applications

One property + one tenant → rental agreement

🔌 JDBC Integration

Centralized DBConnection class for connection pooling

DAO classes for:

User management

Property CRUD

Application and agreement handling

Uses prepared statements for security

Standardized exception handling

📘 Current Review Progress

This review includes the foundational implementation:

1. Project Structure Setup

Modular package design established

2. OOP & Core Java Implementation

Encapsulated entities

Constructors, accessors, and polymorphic behavior defined

3. Database Schema Design

ER structure finalized

Necessary table definitions prepared

4. JDBC Setup

Connectivity established using JDBC drivers

Tested basic CRUD operations

5. DAO Layer Development

CRUD operations for properties and users

Abstraction of DB operations via DAO classes

▶ How to Run

Clone the repository

Open project in IntelliJ/Eclipse/NetBeans

Update database credentials in DBConnection.java

Start your MySQL/PostgreSQL server

Run the Main class to launch the GUI

🚀 Future Enhancements

Advanced search and filtering

Property image upload

Payment tracking

Reporting and dashboard visualizations

Migration to JavaFX or web version (optional)

