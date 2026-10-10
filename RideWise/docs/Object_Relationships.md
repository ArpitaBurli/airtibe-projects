# Object Relationships

## Rider → Ride: association
A ride references the rider who requested it. The rider can exist before and after any particular ride. `Rider` does not own the ride lifecycle.

## Driver → Ride: association
A ride references its assigned driver. A driver can be assigned many rides over time. Completing a ride makes the driver available again; it does not destroy either object.

## Ride → FareReceipt: composition
The ride creates its receipt only when completed and keeps the receipt as part of its state. The console workflow does not create or register receipts independently.

## RideService → strategies: uses/composition-style dependency
`RideService` receives strategy objects through its constructor and uses their interfaces. In this MVP, the service retains default strategy references and accepts per-request overrides. The strategy objects themselves are stateless policies and are not domain entities.

## Why not `List<Ride>` in `Rider`?
The requirements need ride history, but do not require navigation from a `Rider` object to all its rides. `RideService.getRidesForRider(riderId)` provides that use case without bidirectional links. This keeps the model simple and avoids two places needing to maintain the same relationship.
