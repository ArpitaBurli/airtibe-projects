package com.airtribe.ridewise.service;

import com.airtribe.ridewise.exception.NoDriverAvailableException;
import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.Ride;
import com.airtribe.ridewise.model.RideStatus;
import com.airtribe.ridewise.model.Rider;
import com.airtribe.ridewise.strategy.FareStrategy;
import com.airtribe.ridewise.strategy.RideMatchingStrategy;
import com.airtribe.ridewise.util.IdGenerator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RideService {
    private final RiderService riderService;
    private final DriverService driverService;
    private final Map<String, Ride> rides = new LinkedHashMap<>();
    private final IdGenerator idGenerator;
    private RideMatchingStrategy rideMatchingStrategy;
    private FareStrategy fareStrategy;

    public RideService(RiderService riderService,
                       DriverService driverService,
                       RideMatchingStrategy rideMatchingStrategy,
                       FareStrategy fareStrategy,
                       IdGenerator idGenerator) {
        this.riderService = Objects.requireNonNull(riderService);
        this.driverService = Objects.requireNonNull(driverService);
        this.rideMatchingStrategy = Objects.requireNonNull(rideMatchingStrategy);
        this.fareStrategy = Objects.requireNonNull(fareStrategy);
        this.idGenerator = Objects.requireNonNull(idGenerator);
    }

    public Ride requestRide(String riderId, double distance) {
        return requestRide(riderId, distance, rideMatchingStrategy, fareStrategy);
    }

    /** Allows a caller to select strategies for one request without changing global service state. */
    public Ride requestRide(String riderId, double distance,
                            RideMatchingStrategy matchingStrategy,
                            FareStrategy selectedFareStrategy) {
        Objects.requireNonNull(matchingStrategy, "Matching strategy is required.");
        Objects.requireNonNull(selectedFareStrategy, "Fare strategy is required.");
        Rider rider = riderService.getRiderById(riderId);
        List<Driver> availableDrivers = driverService.getAvailableDrivers();
        Driver driver = matchingStrategy.findDriver(rider, availableDrivers);

        if (driver == null) {
            throw new NoDriverAvailableException("No suitable drivers are available.");
        }

        Ride ride = new Ride(idGenerator.nextId(), rider, driver, distance);
        ride.assignDriver();
        driver.recordRideAssignment();
        rides.put(ride.getId(), ride);
        // Save the selected fare strategy with the ride so completion uses the strategy selected at booking.
        fareStrategiesByRide.put(ride.getId(), selectedFareStrategy);
        return ride;
    }

    private final Map<String, FareStrategy> fareStrategiesByRide = new LinkedHashMap<>();

    public Ride completeRide(String rideId) {
        Ride ride = getRideById(rideId);
        FareStrategy selectedFareStrategy = fareStrategiesByRide.get(rideId);
        double fare = selectedFareStrategy.calculateFare(ride);
        ride.complete(fare);
        driverService.updateAvailability(ride.getDriver().getId(), true);
        return ride;
    }

    public Ride cancelRide(String rideId) {
        Ride ride = getRideById(rideId);
        ride.cancel();
        driverService.updateAvailability(ride.getDriver().getId(), true);
        return ride;
    }

    public Ride getRideById(String rideId) {
        Ride ride = rides.get(rideId);
        if (ride == null) throw new IllegalArgumentException("Ride not found: " + rideId);
        return ride;
    }

    public List<Ride> getAllRides() {
        return List.copyOf(rides.values());
    }

    public List<Ride> getRidesForRider(String riderId) {
        return rides.values().stream()
                .filter(ride -> ride.getRider().getId().equals(riderId))
                .toList();
    }

    public void setRideMatchingStrategy(RideMatchingStrategy strategy) {
        this.rideMatchingStrategy = Objects.requireNonNull(strategy);
    }

    public void setFareStrategy(FareStrategy strategy) {
        this.fareStrategy = Objects.requireNonNull(strategy);
    }
}
