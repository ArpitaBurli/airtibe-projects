# Library Management System – Design Notes

## 1. Design Approach

The Library Management System is designed using object-oriented principles with clear separation of responsibilities.

The design separates:

* Domain entities
* Business operations
* Enumerations
* Custom exceptions
* Notification mechanisms
* Recommendation strategies

This makes the system easier to understand, maintain, test, and extend.

## 2. Entity Responsibilities

### Book

Represents a book available in the library.

Responsibilities include maintaining:

* ISBN
* Title
* Author
* Publication information
* Availability status

### Patron

Represents a library user.

A patron can borrow books and maintain borrowing history.

### BorrowingRecord

Represents a borrowing transaction between a patron and a book.

It maintains information related to the borrowing lifecycle.

### Reservation

Represents a request by a patron to reserve a book.

### Branch

Represents an individual library branch.

### Library

Represents the overall library and coordinates library-related information.

## 3. Management Classes

The business operations are separated into management classes instead of placing all logic inside entity classes.

### BookManagement

Responsible for book-related operations such as:

* Adding books
* Removing books
* Updating books
* Searching books

### PatronManagement

Responsible for patron-related operations.

### LendingManagement

Responsible for:

* Borrowing books
* Returning books
* Validating lending operations
* Maintaining borrowing records

### ReservationManagement

Responsible for:

* Creating reservations
* Managing reservations
* Handling reservation-related validation

This separation follows the Single Responsibility Principle.

## 4. Collections

In-memory collections are used because persistence is not required for this assignment.

Collections provide:

* Dynamic sizing
* Easy searching and iteration
* Convenient add/remove operations
* Better flexibility than fixed-size arrays

If the system is later extended with a database, these collections can be replaced by repository or persistence implementations.

## 5. Strategy Pattern

### Problem

The system can recommend books using different algorithms.

For example:

* Recommend books by the same author
* Recommend books based on borrowing history

Putting all recommendation logic into one class would make the class difficult to maintain as more strategies are added.

### Solution

The `RecommendationStrategy` interface defines the recommendation behavior.

Different implementations provide different algorithms:

```text
RecommendationStrategy
        |
        +-- AuthorBasedRecommendationStrategy
        |
        +-- HistoryBasedRecommendationStrategy
```

`RecommendationService` works with the strategy abstraction.

### Benefit

A new recommendation algorithm can be added without changing the existing recommendation service.

This demonstrates the Open/Closed Principle.

## 6. Observer Pattern

### Problem

When a reserved book becomes available, interested patrons should be notified.

The notification mechanism may change depending on the channel.

Possible channels include:

* Email
* SMS

### Solution

The reservation notification flow uses an observer-style design.

```text
Reservation / Availability Event
              |
              v
     ReservationObserver
          /          \
         /            \
EmailNotification   SmsNotification
```

The notification abstraction allows additional channels to be added later.

### Benefit

Adding another notification mechanism, such as push notification, does not require changing the core reservation logic.

## 7. Exception Handling

Custom exceptions are used for business validation.

Examples:

* `BookNotFoundException`
* `BookAlreadyExistsException`
* `BookAlreadyBorrowedException`
* `PatronNotFoundException`
* `PatronAlreadyExistsException`
* `ReservationNotFoundException`
* `BranchNotFoundException`

A common `LibraryException` provides a base exception abstraction for library-related business errors.

This makes errors more meaningful than using generic exceptions everywhere.

## 8. SOLID Principles

### Single Responsibility Principle

Each class has a focused responsibility.

For example:

* `BookManagement` → book operations
* `PatronManagement` → patron operations
* `LendingManagement` → lending operations
* `ReservationManagement` → reservation operations

### Open/Closed Principle

The design allows new recommendation strategies and notification implementations to be added without modifying the existing business logic.

### Liskov Substitution Principle

Concrete implementations of recommendation and notification abstractions can be substituted wherever the corresponding interface is expected.

### Interface Segregation Principle

Small, focused interfaces are preferred over large interfaces containing unrelated operations.

### Dependency Inversion Principle

Business logic depends on abstractions rather than directly depending on concrete notification or recommendation implementations.

## 9. Extensibility

The system is designed so that future features can be added without major changes to the existing structure.

Potential extensions include:

* Database persistence
* REST APIs
* Authentication
* Fine calculation
* Due-date management
* Additional notification channels
* Additional recommendation strategies
* Advanced search
* Unit testing
* Logging

## 10. Design Trade-offs

### In-Memory Storage

The current implementation uses collections instead of a database because persistence is outside the scope of this assignment.

**Advantage:** Simple and easy to demonstrate.

**Limitation:** Data is lost when the application stops.

### Separate Management Classes

Business logic is separated from entity classes.

**Advantage:** Better separation of concerns and maintainability.

**Trade-off:** More classes are introduced, but this improves organization as the application grows.

### Strategy-Based Recommendations

Recommendation logic is separated using the Strategy pattern.

**Advantage:** New algorithms can be introduced without modifying the service.

**Trade-off:** More interfaces and classes are required for a relatively small feature.

## 11. Conclusion

The design focuses on maintainability, extensibility, and separation of concerns.

Using OOP, SOLID principles, Strategy, and Observer-style notification handling allows the Library Management System to evolve without requiring major changes to existing components.
