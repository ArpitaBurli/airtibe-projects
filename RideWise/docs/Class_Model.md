# Class Model

```text
Main
 ├── RiderService ── manages 0..* Rider
 ├── DriverService ── manages 0..* Driver
 └── RideService
      ├── uses RiderService and DriverService
      ├── 1 RideMatchingStrategy (interface)
      ├── 1 FareStrategy (interface)
      └── manages 0..* Ride
                  ├── 1 Rider (association)
                  ├── 1 Driver (association)
                  └── 0..1 FareReceipt (composition)
```

## Important decisions
- `Ride` references its rider and assigned driver. `Rider` and `Driver` do not hold a `List<Ride>`; ride history is queried through `RideService`.
- `Ride` stores the vehicle type selected at assignment so fare logic does not need to navigate through `ride.getDriver().getVehicleType()`.
- `FareReceipt` is created by `Ride.complete(...)`; it cannot exist on a ride before completion through the public workflow.
- `Location` is a small value object with coordinate validation and a distance calculation.
