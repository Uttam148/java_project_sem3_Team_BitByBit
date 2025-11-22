package com.realestate.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Centralized JDBC connection helper. Configure URL/USER/PASS for your DB.
 */
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/realestate";
    private static final String USER = "root";
    private static final String PASS = "password";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
