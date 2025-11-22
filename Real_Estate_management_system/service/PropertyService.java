package com.realestate.service;

import com.realestate.dao.PropertyDAO;
import com.realestate.dao.impl.PropertyDAOImpl;
import com.realestate.model.Property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PropertyService - holds a synchronized list and a background loader thread.
 */
public class PropertyService {
    private final List<Property> properties = Collections.synchronizedList(new ArrayList<>());
    private final PropertyDAO propertyDAO = new PropertyDAOImpl();
    private volatile boolean running = false;
    private Thread loaderThread;

    public PropertyService() {
    }

    public void startBackgroundLoader(long intervalMillis) {
        if (running) return;
        running = true;
        loaderThread = new Thread(() -> {
            while (running) {
                try {
                    List<Property> fresh = propertyDAO.findAll();
                    synchronized (properties) {
                        properties.clear();
                        properties.addAll(fresh);
                    }
                    Thread.sleep(intervalMillis);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    System.err.println("Background loader error: " + e.getMessage());
                }
            }
        }, "Property-Background-Loader");
        loaderThread.setDaemon(true);
        loaderThread.start();
    }

    public void stopBackgroundLoader() {
        running = false;
        if (loaderThread != null) loaderThread.interrupt();
    }

    public List<Property> getSnapshot() {
        synchronized (properties) {
            return new ArrayList<>(properties);
        }
    }
}
