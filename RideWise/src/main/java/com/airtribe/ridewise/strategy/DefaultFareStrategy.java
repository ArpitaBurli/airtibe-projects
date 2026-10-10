package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.model.Ride;
import com.airtribe.ridewise.model.VehicleType;

public class DefaultFareStrategy implements FareStrategy {
    private static final double BASE_FARE = 30.0;

    @Override
    public double calculateFare(Ride ride) {
        if (ride == null) throw new IllegalArgumentException("Ride is required.");
        double perKmRate = switch (ride.getVehicleType()) {
            case BIKE -> 8.0;
            case AUTO -> 12.0;
            case CAR -> 18.0;
        };
        return roundToTwoDecimals(BASE_FARE + ride.getDistance() * perKmRate);
    }

    protected double roundToTwoDecimals(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }
}
