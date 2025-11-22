package com.realestate.util;

/**
 * Thread-safe ID generator example.
 */
public class IDGenerator {
    private static int propertyId = 1000;

    public static synchronized int nextPropertyId() {
        return ++propertyId;
    }
}
