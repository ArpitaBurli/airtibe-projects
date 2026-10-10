# SOLID Reflection

- **SRP:** `RiderService` manages riders, `DriverService` manages drivers, `RideService` orchestrates ride workflows, and each strategy calculates one matching/pricing policy.
- **OCP:** Add a new `RideMatchingStrategy` or `FareStrategy` implementation without changing the existing strategy implementations or core calculation logic. A menu option may be added to expose the new option.
- **LSP:** Every implementation respects the interface contract: matching returns an available candidate or `null`; fare strategies return a non-negative fare for a valid ride.
- **ISP:** The two strategy interfaces are small and focused; matching implementations do not need to implement fare calculation and vice versa.
- **DIP:** `RideService` accepts `RideMatchingStrategy` and `FareStrategy` interfaces in its constructor, rather than constructing a specific strategy internally.
