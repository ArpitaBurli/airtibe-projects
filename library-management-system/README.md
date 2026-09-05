# Library Management System – Low-Level Design

## Overview

The Library Management System is a console-based Java application designed to demonstrate object-oriented programming, SOLID principles, clean code practices, exception handling, and commonly used design patterns.

The system manages books, patrons, borrowing and returning of books, reservations, library branches, notifications, and book recommendations.

No database or external persistence is used. The application uses in-memory collections to manage the data.

## Features

### Book Management

* Add a new book
* Remove a book
* Update book details
* Search books
* Track book availability

### Patron Management

* Add a patron
* Remove/manage patron information
* Track borrowing history

### Lending Management

* Borrow a book
* Return a book
* Validate book availability
* Maintain borrowing records

### Reservation Management

* Reserve an unavailable book
* Manage reservation status
* Notify patrons when a reserved book becomes available

### Multi-Branch Support

* Support multiple library branches
* Associate books with library branches
* Identify books based on branch

### Notifications

The system supports notification channels for reservation availability:

* Email notification
* SMS notification

### Book Recommendations

The system supports different recommendation strategies:

* Author-based recommendations
* History-based recommendations

## Project Structure

```text
library-management-system/
├── src/
│   └── main/
│       └── java/
│           └── org/example/
│               ├── entity/
│               ├── enums/
│               ├── exception/
│               ├── management/
│               ├── notofication/
│               ├── recommendation/
│               └── Main.java
│
├── pom.xml
├── README.md
└── docs/
    └── Design_Notes.md
```

## Main Components

### Entity

Contains the core domain objects:

* `Book`
* `Patron`
* `BorrowingRecord`
* `Reservation`
* `Branch`
* `Library`

### Management

Contains classes responsible for business operations:

* `BookManagement`
* `PatronManagement`
* `LendingManagement`
* `ReservationManagement`

### Enum

Contains predefined states:

* `BookAvailabilityStatus`
* `BorrowingStatus`
* `ReservationStatus`

### Exception

Contains custom exceptions used for business validation:

* `BookAlreadyBorrowedException`
* `BookAlreadyExistsException`
* `BookNotFoundException`
* `PatronAlreadyExistsException`
* `PatronNotFoundException`
* `ReservationNotFoundException`
* `BranchNotFoundException`

### Notification

Provides notification functionality using different channels.

### Recommendation

Provides book recommendations using different strategies.

## Design Patterns Used

### Strategy Pattern

Used for book recommendation functionality.

Different recommendation algorithms can be implemented independently, such as:

* Author-based recommendation
* History-based recommendation

The `RecommendationStrategy` interface allows the recommendation algorithm to be changed without modifying the recommendation service.

### Observer Pattern

Used for reservation availability notifications.

When a reserved book becomes available, registered observers can be notified through different notification channels such as email or SMS.

## Object-Oriented Principles

The application demonstrates:

* **Encapsulation** – domain objects keep their data and behavior together.
* **Abstraction** – interfaces such as `NotificationChannel` and `RecommendationStrategy` hide implementation details.
* **Inheritance** – common behavior can be represented through appropriate abstractions.
* **Polymorphism** – different recommendation and notification implementations can be used through their interfaces.

## SOLID Principles

### Single Responsibility Principle

Each management class has a focused responsibility, such as book management, patron management, lending, or reservations.

### Open/Closed Principle

New recommendation strategies and notification channels can be added without modifying existing service logic.

### Liskov Substitution Principle

Implementations of interfaces can be used wherever their corresponding abstraction is expected.

### Interface Segregation Principle

Interfaces are kept focused on the behavior required by their clients.

### Dependency Inversion Principle

Business logic depends on abstractions such as `RecommendationStrategy` and `NotificationChannel` rather than concrete implementations.

## Exception Handling

Custom exceptions are used to represent business-level validation failures.

For example:

* Attempting to borrow an already borrowed book
* Searching for a book that does not exist
* Adding a duplicate book or patron
* Reserving a book when the required entity cannot be found

This keeps validation failures meaningful and easier to understand.

## Technologies Used

* Java
* Maven
* Object-Oriented Programming
* Java Collections
* Design Patterns

## How to Run

1. Clone the repository.
2. Open the `library-management-system` project in IntelliJ IDEA or another Java IDE.
3. Make sure Maven dependencies are loaded.
4. Run `Main.java`.
5. Follow the console instructions.

## Future Enhancements

The system can be extended with:

* Database persistence
* REST APIs using Spring Boot
* Authentication and authorization
* Due-date and fine calculation
* Advanced search and filtering
* Online book reservation
* Persistent notification services
* Unit and integration tests

## Conclusion

This project demonstrates how a Library Management System can be designed using clean object-oriented principles and extensible design patterns. The design keeps business responsibilities separated and allows new features to be added with minimal changes to existing code.
