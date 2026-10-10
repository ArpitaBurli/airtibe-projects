package com.airtribe.ridewise.model;

import java.util.Objects;

public class Ride {
    private final String id;
    private final Rider rider;
    private final Driver driver;
    private final double distance;
    private final VehicleType vehicleType;
    private RideStatus status;
    // Composition: a ride owns its receipt; receipt is absent until completion.
    private FareReceipt fareReceipt;

    public Ride(String id, Rider rider, Driver driver, double distance) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Ride ID cannot be blank.");
        if (!Double.isFinite(distance) || distance <= 0) {
            throw new IllegalArgumentException("Ride distance must be positive.");
        }
        this.id = id.trim();
        this.rider = Objects.requireNonNull(rider, "Rider is required.");
        this.driver = Objects.requireNonNull(driver, "Driver is required.");
        this.distance = distance;
        this.vehicleType = driver.getVehicleType();
        this.status = RideStatus.REQUESTED;
    }

    public String getId() { return id; }
    public Rider getRider() { return rider; }
    public Driver getDriver() { return driver; }
    public double getDistance() { return distance; }
    public VehicleType getVehicleType() { return vehicleType; }
    public RideStatus getStatus() { return status; }
    public FareReceipt getFareReceipt() { return fareReceipt; }

    public void assignDriver() {
        if (status != RideStatus.REQUESTED) {
            throw new IllegalStateException("Only a requested ride can be assigned.");
        }
        status = RideStatus.ASSIGNED;
    }

    public FareReceipt complete(double fareAmount) {
        if (status != RideStatus.ASSIGNED) {
            throw new IllegalStateException("Only an assigned ride can be completed.");
        }
        fareReceipt = new FareReceipt(id, fareAmount);
        status = RideStatus.COMPLETED;
        return fareReceipt;
    }

    public void cancel() {
        if (status == RideStatus.COMPLETED || status == RideStatus.CANCELLED) {
            throw new IllegalStateException("Completed or cancelled rides cannot be cancelled.");
        }
        status = RideStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return "Ride{id='%s', rider='%s', driver='%s', distance=%.2f km, vehicle=%s, status=%s, receipt=%s}"
                .formatted(id, rider.getId(), driver.getId(), distance, vehicleType, status,
                        fareReceipt == null ? "not generated" : fareReceipt);
    }
}
