package com.realestate.dao.impl;

import com.realestate.dao.UserDAO;
import com.realestate.model.AbstractUser;
import com.realestate.model.Admin;
import com.realestate.model.Manager;
import com.realestate.model.TenantUser;
import com.realestate.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final String INSERT_SQL = "INSERT INTO users (name, email, role, password, contact_info) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String DELETE_SQL = "DELETE FROM users WHERE user_id = ?";

    @Override
    public int create(AbstractUser user) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getRole());
            ps.setString(4, "password"); // placeholder
            if (user instanceof TenantUser) {
                ps.setString(5, ((TenantUser) user).getContactInfo());
            } else {
                ps.setNull(5, Types.VARCHAR);
            }

            int rows = ps.executeUpdate();
            if (rows == 0) throw new SQLException("Creating user failed, no rows affected.");

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    user.setId(id);
                    return id;
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public AbstractUser findById(int id) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    @Override
    public AbstractUser findByEmail(String email) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    @Override
    public List<AbstractUser> findAll() throws Exception {
        List<AbstractUser> list = new ArrayList<>();
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
    public boolean delete(int id) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    private AbstractUser mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("user_id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String role = rs.getString("role");
        String contact = rs.getString("contact_info");

        if ("ADMIN".equalsIgnoreCase(role)) {
            return new Admin(id, name, email);
        } else if ("MANAGER".equalsIgnoreCase(role)) {
            return new Manager(id, name, email);
        } else {
            TenantUser t = new TenantUser(id, name, email, contact);
            return t;
        }
    }
}
