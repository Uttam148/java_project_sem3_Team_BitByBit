package com.realestate.ui;

import com.realestate.model.Property;
import com.realestate.model.RentalProperty;
import com.realestate.model.SaleProperty;
import com.realestate.service.PropertyService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Displays properties from PropertyService. Demonstrates collections usage (Set, TreeSet).
 */
public class PropertyListPanel extends JPanel {
    private final PropertyService propertyService;
    private JTable table;
    private DefaultTableModel model;

    public PropertyListPanel(PropertyService service) {
        this.propertyService = service;
        setLayout(new BorderLayout());
        model = new DefaultTableModel(new String[]{"ID", "Title", "Type", "Location", "Price", "Status"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton loadNow = new JButton("Load Now");
        JButton showLocations = new JButton("Unique Locations (TreeSet)"); // demonstrate Set
        loadNow.addActionListener(e -> loadSnapshot());
        showLocations.addActionListener(e -> showLocations());
        bottom.add(loadNow);
        bottom.add(showLocations);
        add(bottom, BorderLayout.SOUTH);

        loadSnapshot();
    }

    private void loadSnapshot() {
        SwingUtilities.invokeLater(() -> {
            model.setRowCount(0);
            List<Property> list = propertyService.getSnapshot();
            for (Property p : list) {
                model.addRow(new Object[]{p.getId(), p.getTitle(), p.getPropertyType(), p.getLocation(), p.getPrice(), p.getStatus()});
            }
        });
    }

    private void showLocations() {
        List<Property> list = propertyService.getSnapshot();
        // TreeSet to sort unique locations
        Set<String> locations = new TreeSet<>(list.stream().map(Property::getLocation).collect(Collectors.toSet()));
        JOptionPane.showMessageDialog(this, String.join(", ", locations));
    }
}
