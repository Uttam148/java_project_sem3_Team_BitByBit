package com.realestate.main;

import com.realestate.ui.MainApp;

import javax.swing.SwingUtilities;

/**
 * Bootstrap launcher
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApp app = new MainApp();
            app.setVisible(true);
        });
    }
}
