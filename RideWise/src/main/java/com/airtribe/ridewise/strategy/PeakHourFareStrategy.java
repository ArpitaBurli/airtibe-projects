package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.model.Ride;

/** Represents a peak-hour pricing mode selected by the user in the console. */
public class PeakHourFareStrategy extends DefaultFareStrategy {
    private final double multiplier;

    public PeakHourFareStrategy() {
        this(1.5);
    }

    public PeakHourFareStrategy(double multiplier) {
        if (!Double.isFinite(multiplier) || multiplier < 1.0) {
            throw new IllegalArgumentException("Peak multiplier must be a finite number >= 1.0.");
        }
        this.multiplier = multiplier;
    }

    @Override
    public double calculateFare(Ride ride) {
        return roundToTwoDecimals(super.calculateFare(ride) * multiplier);
    }

    public double getMultiplier() {
        return multiplier;
    }
}
