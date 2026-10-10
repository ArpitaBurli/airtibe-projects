# Requirements

## Functional
- Register riders and drivers.
- List available drivers.
- Request a ride for a registered rider.
- Match a driver using a strategy.
- Calculate fare using a strategy chosen for the ride.
- Complete or cancel a ride.
- Track requested, assigned, completed, and cancelled states.
- Generate a fare receipt when a ride completes.
- View rides and retrieve rides for a rider.

## Non-functional
- Keep service responsibilities focused.
- Add matching/pricing algorithms through interfaces.
- Avoid coupling `RideService` to specific strategy implementations.
- Validate user input and invalid state transitions.
- Keep the first version in memory; persistence and maps API are outside MVP scope.

## Assumptions
- Coordinates are latitude/longitude; nearest matching uses straight-line Haversine distance, not road travel time.
- Trip distance in kilometres is entered by the console user.
- Selecting Peak-hour fare means the peak pricing mode applies a 1.5 multiplier.
- A driver can handle one assigned ride at a time.
