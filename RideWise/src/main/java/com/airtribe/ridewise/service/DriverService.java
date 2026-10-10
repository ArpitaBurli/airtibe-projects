package com.airtribe.ridewise.service;

import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.Location;
import com.airtribe.ridewise.model.VehicleType;
import com.airtribe.ridewise.util.IdGenerator;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DriverService {
    private final Map<String, Driver> drivers = new LinkedHashMap<>();
    private final IdGenerator idGenerator;

    public DriverService(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Driver registerDriver(String name, Location location, VehicleType vehicleType) {
        Driver driver = new Driver(idGenerator.nextId(), name, location, vehicleType);
        drivers.put(driver.getId(), driver);
        return driver;
    }

    public Driver getDriverById(String driverId) {
        Driver driver = drivers.get(driverId);
        if (driver == null) throw new IllegalArgumentException("Driver not found: " + driverId);
        return driver;
    }

    public List<Driver> getAvailableDrivers() {
        return drivers.values().stream().filter(Driver::isAvailable).toList();
    }

    public List<Driver> getAllDrivers() {
        Collection<Driver> values = drivers.values();
        return List.copyOf(values);
    }

    public void updateAvailability(String driverId, boolean available) {
        getDriverById(driverId).setAvailable(available);
    }
}
