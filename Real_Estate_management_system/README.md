# Online Real Estate Management System - Full (Hybrid) Implementation (Academic + Demo)

This upgraded project implements both **User** and **Property** inheritance hierarchies, demonstrates collections & generics, includes multithreading with synchronization, and provides full DAO layers with JDBC connectivity.

## Highlights
- AbstractUser → Admin / Manager / TenantUser (inheritance + polymorphism)
- Property → RentalProperty / SaleProperty (domain inheritance)
- Custom exceptions (NotFoundException, ValidationException)
- Generic `Result<T>` wrapper
- Thread-safe ID generator
- PropertyService with background loader (daemon thread)
- Synchronized collections and TreeSet usage in UI
- DAO implementations for Users and Properties (JDBC + PreparedStatement)
- SQL schema with sample data (MySQL)

## How to use
1. Run `sql/schema.sql` in your MySQL to create DB and sample data.
2. Configure DB credentials in `com.realestate.util.DBConnection`.
3. Import project into IDE and run `com.realestate.main.Main`.

This is a hybrid design: production-feel structure without complexity of frameworks.
