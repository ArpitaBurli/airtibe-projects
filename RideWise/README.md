# RideWise — Modular Ride-Sharing System

A console-based Java 17 project demonstrating object-oriented design, Strategy Pattern, SOLID principles, composition, associations, validation, and unit tests.

## Requirements covered

- Register riders and drivers.
- View available drivers.
- Request a ride and match a driver with a replaceable strategy.
- Calculate fares with a replaceable strategy.
- Track `REQUESTED`, `ASSIGNED`, `COMPLETED`, and `CANCELLED` statuses.
- Complete a ride and create its fare receipt.
- Console menu with invalid-input handling.
- Unit tests for service and strategy behaviour.
- Design notes in `docs/`.

## Run

Prerequisites: JDK 17+ and Maven 3.9+.

```bash
mvn clean test
mvn package
java -jar target/ridewise-1.0.0.jar
```

The project currently builds a normal Maven JAR (not a fat JAR), so the most reliable launch command is:

```bash
mvn exec:java -Dexec.mainClass="com.airtribe.ridewise.Main"
```

If the Exec Maven plugin is not installed/cached, run `com.airtribe.ridewise.Main` from your IDE after importing the project as a Maven project.

## Console workflow

1. Add a rider using a name and latitude/longitude.
2. Add a driver using a name, vehicle type, and latitude/longitude.
3. View available drivers.
4. Request a ride using rider ID, distance in km, and fare strategy.
5. Complete the ride using its ride ID.
6. View all rides and their status/receipt.

Latitude and longitude are used by `NearestDriverStrategy`. For this educational MVP, ride distance is entered separately because actual road-route distance would require a mapping service.

## Design decisions

- `Ride` points to its `Rider` and assigned `Driver`; neither entity stores a back-reference to all rides by default. `RideService` keeps the ride collection and can list ride history. This avoids unnecessary bidirectional links.
- `Ride` creates and owns its `FareReceipt`, so the receipt is part of the ride's lifecycle.
- `RideService` depends on `RideMatchingStrategy` and `FareStrategy` interfaces, not concrete implementations.
- `NearestDriverStrategy` chooses the available driver with the smallest geographic distance.
- `LeastActiveDriverStrategy` chooses the available driver with the lowest number of rides assigned historically, then uses distance as a tie-breaker.
- `PeakHourFareStrategy` applies a configurable peak multiplier (default 1.5x). Choosing this strategy in the menu represents peak-hour pricing; it deliberately avoids hidden dependence on the computer's current clock.
- The in-memory collections are appropriate for a small console assignment; a database is intentionally out of scope (YAGNI).

## Suggested demo

Register two riders and three drivers in different locations. Request a ride with each matching strategy, compare the assigned driver and fare strategy, complete a ride, and show that the driver becomes available again and the ride receives a fare receipt.
