package com.airtribe.ridewise;

import com.airtribe.ridewise.exception.NoDriverAvailableException;
import com.airtribe.ridewise.model.*;
import com.airtribe.ridewise.service.DriverService;
import com.airtribe.ridewise.service.RideService;
import com.airtribe.ridewise.service.RiderService;
import com.airtribe.ridewise.strategy.*;
import com.airtribe.ridewise.util.IdGenerator;

import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final RiderService riderService = new RiderService(new IdGenerator("R"));
    private final DriverService driverService = new DriverService(new IdGenerator("D"));
    private final RideService rideService = new RideService(
            riderService,
            driverService,
            new NearestDriverStrategy(),
            new DefaultFareStrategy(),
            new IdGenerator("TR"));

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        boolean running = true;
        System.out.println("=== Welcome to RideWise ===");
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> addRider();
                    case 2 -> addDriver();
                    case 3 -> viewAvailableDrivers();
                    case 4 -> requestRide();
                    case 5 -> completeRide();
                    case 6 -> viewRides();
                    case 7 -> running = false;
                    default -> System.out.println("Please choose a number from 1 to 7.");
                }
            } catch (IllegalArgumentException | IllegalStateException | NoDriverAvailableException ex) {
                System.out.println("Could not complete action: " + ex.getMessage());
            } catch (Exception ex) {
                // Keep the console loop alive for unexpected input/runtime errors.
                System.out.println("Unexpected error: " + ex.getMessage());
            }
            System.out.println();
        }
        System.out.println("Thank you for using RideWise.");
    }

    private void printMenu() {
        System.out.println("""

                -------- MAIN MENU --------
                1. Add Rider
                2. Add Driver
                3. View Available Drivers
                4. Request Ride
                5. Complete Ride
                6. View Rides
                7. Exit
                ---------------------------
                """);
    }

    private void addRider() {
        String name = readText("Rider name: ");
        Location location = readLocation();
        Rider rider = riderService.registerRider(name, location);
        System.out.println("Rider registered: " + rider);
    }

    private void addDriver() {
        String name = readText("Driver name: ");
        VehicleType vehicleType = readVehicleType();
        Location location = readLocation();
        Driver driver = driverService.registerDriver(name, location, vehicleType);
        System.out.println("Driver registered: " + driver);
    }

    private void viewAvailableDrivers() {
        List<Driver> drivers = driverService.getAvailableDrivers();
        if (drivers.isEmpty()) {
            System.out.println("No drivers are currently available.");
            return;
        }
        System.out.println("Available drivers:");
        drivers.forEach(System.out::println);
    }

    private void requestRide() {
        String riderId = readText("Rider ID: ");
        double distance = readPositiveDouble("Trip distance (km): ");
        System.out.println("Matching strategy: 1. Nearest driver  2. Least active driver");
        int matchingChoice = readInt("Choose matching strategy: ");
        RideMatchingStrategy matchingStrategy = switch (matchingChoice) {
            case 1 -> new NearestDriverStrategy();
            case 2 -> new LeastActiveDriverStrategy();
            default -> throw new IllegalArgumentException("Choose matching strategy 1 or 2.");
        };

        System.out.println("Fare strategy: 1. Default fare  2. Peak-hour fare");
        int fareChoice = readInt("Choose fare strategy: ");
        FareStrategy fareStrategy = switch (fareChoice) {
            case 1 -> new DefaultFareStrategy();
            case 2 -> new PeakHourFareStrategy();
            default -> throw new IllegalArgumentException("Choose fare strategy 1 or 2.");
        };

        Ride ride = rideService.requestRide(riderId, distance, matchingStrategy, fareStrategy);
        System.out.println("Ride requested successfully: " + ride);
        System.out.println("Assigned driver: " + ride.getDriver());
    }

    private void completeRide() {
        String rideId = readText("Ride ID to complete: ");
        Ride ride = rideService.completeRide(rideId);
        System.out.println("Ride completed: " + ride);
        System.out.println("Fare receipt: " + ride.getFareReceipt());
    }

    private void viewRides() {
        List<Ride> rides = rideService.getAllRides();
        if (rides.isEmpty()) {
            System.out.println("No rides have been requested yet.");
            return;
        }
        rides.forEach(System.out::println);
    }

    private Location readLocation() {
        double latitude = readDouble("Latitude (-90 to 90): ");
        double longitude = readDouble("Longitude (-180 to 180): ");
        return new Location(latitude, longitude);
    }

    private VehicleType readVehicleType() {
        System.out.println("Vehicle type: 1. BIKE  2. AUTO  3. CAR");
        return switch (readInt("Choose vehicle type: ")) {
            case 1 -> VehicleType.BIKE;
            case 2 -> VehicleType.AUTO;
            case 3 -> VehicleType.CAR;
            default -> throw new IllegalArgumentException("Choose vehicle type 1, 2, or 3.");
        };
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine().trim();
        if (value.isBlank()) {
            throw new IllegalArgumentException("Input cannot be blank.");
        }
        return value;
    }

    private int readInt(String prompt) {
        try {
            return Integer.parseInt(readText(prompt));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Please enter a whole number.");
        }
    }

    private double readDouble(String prompt) {
        try {
            return Double.parseDouble(readText(prompt));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Please enter a valid number.");
        }
    }

    private double readPositiveDouble(String prompt) {
        double value = readDouble(prompt);
        if (!Double.isFinite(value) || value <= 0) {
            throw new IllegalArgumentException("Value must be a positive number.");
        }
        return value;
    }
}
