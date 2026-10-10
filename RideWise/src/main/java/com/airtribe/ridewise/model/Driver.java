package com.airtribe.ridewise.model;

import java.util.Objects;

public class Driver {
    private final String id;
    private final String name;
    private final VehicleType vehicleType;
    private Location currentLocation;
    private boolean available;
    private int totalRides;

    public Driver(String id, String name, Location currentLocation, VehicleType vehicleType) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Driver ID cannot be blank.");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Driver name cannot be blank.");
        this.id = id.trim();
        this.name = name.trim();
        this.currentLocation = Objects.requireNonNull(currentLocation, "Driver location is required.");
        this.vehicleType = Objects.requireNonNull(vehicleType, "Vehicle type is required.");
        this.available = true;
        this.totalRides = 0;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public VehicleType getVehicleType() { return vehicleType; }
    public Location getCurrentLocation() { return currentLocation; }
    public boolean isAvailable() { return available; }
    public int getTotalRides() { return totalRides; }

    public void updateLocation(Location location) {
        this.currentLocation = Objects.requireNonNull(location, "Location is required.");
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    /** Called when this driver is assigned a ride. */
    public void recordRideAssignment() {
        totalRides++;
        available = false;
    }

    @Override
    public String toString() {
        return "Driver{id='%s', name='%s', vehicle=%s, location=%s, available=%s, totalRides=%d}"
                .formatted(id, name, vehicleType, currentLocation, available, totalRides);
    }
}
