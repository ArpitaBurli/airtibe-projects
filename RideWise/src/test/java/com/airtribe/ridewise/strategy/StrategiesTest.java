package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StrategiesTest {
    @Test
    void nearestStrategyChoosesClosestAvailableDriver() {
        Rider rider = new Rider("R1", "Ravi", new Location(12.9716, 77.5946));
        Driver far = new Driver("D1", "Far", new Location(13.1000, 77.7000), VehicleType.CAR);
        Driver near = new Driver("D2", "Near", new Location(12.9720, 77.5950), VehicleType.AUTO);

        Driver chosen = new NearestDriverStrategy().findDriver(rider, List.of(far, near));

        assertEquals("D2", chosen.getId());
    }

    @Test
    void matchingStrategiesIgnoreUnavailableDrivers() {
        Rider rider = new Rider("R1", "Ravi", new Location(12.97, 77.59));
        Driver unavailable = new Driver("D1", "Busy", new Location(12.97, 77.59), VehicleType.CAR);
        unavailable.setAvailable(false);

        assertNull(new NearestDriverStrategy().findDriver(rider, List.of(unavailable)));
        assertNull(new LeastActiveDriverStrategy().findDriver(rider, List.of(unavailable)));
    }

    @Test
    void peakFareIsHigherThanDefaultFare() {
        Rider rider = new Rider("R1", "Ravi", new Location(12.97, 77.59));
        Driver driver = new Driver("D1", "Driver", new Location(12.98, 77.60), VehicleType.CAR);
        Ride ride = new Ride("TR1", rider, driver, 10.0);

        double normal = new DefaultFareStrategy().calculateFare(ride);
        double peak = new PeakHourFareStrategy().calculateFare(ride);

        assertEquals(210.0, normal, 0.001);
        assertEquals(315.0, peak, 0.001);
    }
}
