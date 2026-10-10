package com.airtribe.ridewise.service;

import com.airtribe.ridewise.exception.NoDriverAvailableException;
import com.airtribe.ridewise.model.*;
import com.airtribe.ridewise.strategy.DefaultFareStrategy;
import com.airtribe.ridewise.strategy.NearestDriverStrategy;
import com.airtribe.ridewise.util.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RideServiceTest {
    private RiderService riderService;
    private DriverService driverService;
    private RideService rideService;
    private Rider rider;
    private Driver driver;

    @BeforeEach
    void setUp() {
        riderService = new RiderService(new IdGenerator("R"));
        driverService = new DriverService(new IdGenerator("D"));
        rideService = new RideService(riderService, driverService,
                new NearestDriverStrategy(), new DefaultFareStrategy(), new IdGenerator("TR"));
        rider = riderService.registerRider("Ravi", new Location(12.9716, 77.5946));
        driver = driverService.registerDriver("Asha", new Location(12.9720, 77.5950), VehicleType.AUTO);
    }

    @Test
    void requestRideAssignsDriverAndMarksRideAssigned() {
        Ride ride = rideService.requestRide(rider.getId(), 5.0);

        assertEquals(RideStatus.ASSIGNED, ride.getStatus());
        assertEquals(driver.getId(), ride.getDriver().getId());
        assertFalse(driver.isAvailable());
        assertNull(ride.getFareReceipt());
    }

    @Test
    void completeRideCreatesReceiptAndReleasesDriver() {
        Ride ride = rideService.requestRide(rider.getId(), 5.0);

        rideService.completeRide(ride.getId());

        assertEquals(RideStatus.COMPLETED, ride.getStatus());
        assertNotNull(ride.getFareReceipt());
        assertEquals(ride.getId(), ride.getFareReceipt().getRideId());
        assertEquals(90.0, ride.getFareReceipt().getAmount(), 0.001);
        assertTrue(driver.isAvailable());
    }

    @Test
    void requestRideFailsWhenNoDriverIsAvailable() {
        driver.setAvailable(false);

        assertThrows(NoDriverAvailableException.class,
                () -> rideService.requestRide(rider.getId(), 5.0));
    }

    @Test
    void riderRideHistoryCanBeQueriedWithoutListInsideRider() {
        rideService.requestRide(rider.getId(), 5.0);
        driverService.registerDriver("Second", new Location(12.98, 77.60), VehicleType.BIKE);
        rideService.requestRide(rider.getId(), 2.0);

        assertEquals(2, rideService.getRidesForRider(rider.getId()).size());
    }
}
