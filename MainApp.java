package com.realestate.ui;

import com.realestate.model.*;
import com.realestate.service.PropertyService;
import com.realestate.dao.impl.PropertyDAOImpl;
import com.realestate.dao.impl.UserDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Main application frame - simple role selection and property viewer.
 */
public class MainApp extends JFrame {
    private PropertyService propertyService = new PropertyService();

    public MainApp() {
        super("Online Real Estate Management System - Hybrid Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        initUI();
        // Start background loader with 5 seconds interval
        propertyService.startBackgroundLoader(5000);
    }

    private void initUI() {
        JPanel top = new JPanel();
        JButton adminBtn = new JButton("Login as Admin (demo)");
        JButton managerBtn = new JButton("Login as Manager (demo)");
        JButton tenantBtn = new JButton("Login as Tenant (demo)");
        JButton refreshBtn = new JButton("Refresh Properties Now");

        adminBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Admin dashboard (demo)"));
        managerBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Manager dashboard (demo)"));
        tenantBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Tenant dashboard (demo)"));
        refreshBtn.addActionListener(e -> refreshList());

        top.add(adminBtn);
        top.add(managerBtn);
        top.add(tenantBtn);
        top.add(refreshBtn);

        add(top, BorderLayout.NORTH);

        // default center: property list panel
        add(new PropertyListPanel(propertyService), BorderLayout.CENTER);
    }

    private void refreshList() {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this, "Refreshing... (background loader also updates every 5s)"); 
        });
    }
}
