package com.airtribe.ridewise.model;

import java.time.LocalDateTime;

/** Receipt is created and owned by a Ride when that ride is completed. */
public final class FareReceipt {
    private final String rideId;
    private final double amount;
    private final LocalDateTime generatedAt;

    FareReceipt(String rideId, double amount) {
        if (rideId == null || rideId.isBlank()) throw new IllegalArgumentException("Ride ID cannot be blank.");
        if (!Double.isFinite(amount) || amount < 0) throw new IllegalArgumentException("Fare cannot be negative.");
        this.rideId = rideId;
        this.amount = Math.round(amount * 100.0) / 100.0;
        this.generatedAt = LocalDateTime.now();
    }

    public String getRideId() { return rideId; }
    public double getAmount() { return amount; }
    public LocalDateTime getGeneratedAt() { return generatedAt; }

    @Override
    public String toString() {
        return "FareReceipt{rideId='%s', amount=%.2f, generatedAt=%s}"
                .formatted(rideId, amount, generatedAt);
    }
}
