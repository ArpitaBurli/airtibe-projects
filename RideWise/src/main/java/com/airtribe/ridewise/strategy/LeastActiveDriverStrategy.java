package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.Rider;

import java.util.Comparator;
import java.util.List;

public class LeastActiveDriverStrategy implements RideMatchingStrategy {
    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        if (drivers == null || drivers.isEmpty()) return null;
        return drivers.stream()
                .filter(Driver::isAvailable)
                .min(Comparator.comparingInt(Driver::getTotalRides)
                        .thenComparingDouble(driver -> rider.getLocation()
                                .distanceTo(driver.getCurrentLocation())))
                .orElse(null);
    }
}
