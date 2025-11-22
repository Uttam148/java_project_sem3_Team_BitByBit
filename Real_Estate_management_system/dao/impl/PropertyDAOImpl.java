package com.realestate.dao.impl;

import com.realestate.dao.PropertyDAO;
import com.realestate.model.Property;
import com.realestate.model.RentalProperty;
import com.realestate.model.SaleProperty;
import com.realestate.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAOImpl implements PropertyDAO {

    private static final String INSERT_SQL = "INSERT INTO properties (title, location, price, status, property_type, monthly_rent, deposit, lease_term_months, brokerage_fee) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM properties WHERE property_id = ?";
    private static final String SELECT_ALL = "SELECT * FROM properties";
    private static final String UPDATE_SQL = "UPDATE properties SET title=?, location=?, price=?, status=?, property_type=?, monthly_rent=?, deposit=?, lease_term_months=?, brokerage_fee=? WHERE property_id=?";
    private static final String DELETE_SQL = "DELETE FROM properties WHERE property_id = ?";

    @Override
    public int create(Property property) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, property.getTitle());
            ps.setString(2, property.getLocation());
            ps.setDouble(3, property.getPrice());
            ps.setString(4, property.getStatus());
            ps.setString(5, property.getPropertyType());

            if (property instanceof RentalProperty) {
                RentalProperty rp = (RentalProperty) property;
                ps.setDouble(6, rp.getMonthlyRent());
                ps.setDouble(7, rp.getDeposit());
                ps.setInt(8, rp.getLeaseTermMonths());
                ps.setNull(9, Types.DOUBLE);
            } else if (property instanceof SaleProperty) {
                SaleProperty sp = (SaleProperty) property;
                ps.setNull(6, Types.DOUBLE);
                ps.setNull(7, Types.DOUBLE);
                ps.setNull(8, Types.INTEGER);
                ps.setDouble(9, sp.getBrokerageFee());
            } else {
                ps.setNull(6, Types.DOUBLE);
                ps.setNull(7, Types.DOUBLE);
                ps.setNull(8, Types.INTEGER);
                ps.setNull(9, Types.DOUBLE);
            }

            int rows = ps.executeUpdate();
            if (rows == 0) throw new SQLException("Creating property failed, no rows affected.");

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    property.setId(id);
                    return id;
                } else {
                    throw new SQLException("Creating property failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public Property findById(int id) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Property> findAll() throws Exception {
        List<Property> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    @Override
    public boolean update(Property property) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, property.getTitle());
            ps.setString(2, property.getLocation());
            ps.setDouble(3, property.getPrice());
            ps.setString(4, property.getStatus());
            ps.setString(5, property.getPropertyType());

            if (property instanceof RentalProperty) {
                RentalProperty rp = (RentalProperty) property;
                ps.setDouble(6, rp.getMonthlyRent());
                ps.setDouble(7, rp.getDeposit());
                ps.setInt(8, rp.getLeaseTermMonths());
                ps.setNull(9, Types.DOUBLE);
            } else if (property instanceof SaleProperty) {
                SaleProperty sp = (SaleProperty) property;
                ps.setNull(6, Types.DOUBLE);
                ps.setNull(7, Types.DOUBLE);
                ps.setNull(8, Types.INTEGER);
                ps.setDouble(9, sp.getBrokerageFee());
            } else {
                ps.setNull(6, Types.DOUBLE);
                ps.setNull(7, Types.DOUBLE);
                ps.setNull(8, Types.INTEGER);
                ps.setNull(9, Types.DOUBLE);
            }

            ps.setInt(10, property.getId());
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    private Property mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("property_id");
        String title = rs.getString("title");
        String location = rs.getString("location");
        double price = rs.getDouble("price");
        String status = rs.getString("status");
        String type = rs.getString("property_type");

        if ("RENTAL".equalsIgnoreCase(type)) {
            double monthly = rs.getDouble("monthly_rent");
            double deposit = rs.getDouble("deposit");
            int lease = rs.getInt("lease_term_months");
            return new RentalProperty(id, title, location, price, status, monthly, deposit, lease);
        } else if ("SALE".equalsIgnoreCase(type)) {
            double fee = rs.getDouble("brokerage_fee");
            return new SaleProperty(id, title, location, price, status, fee);
        } else {
            return new Property(id, title, location, price, status, type);
        }
    }
}
