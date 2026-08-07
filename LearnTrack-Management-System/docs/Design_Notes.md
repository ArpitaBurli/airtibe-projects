# Design Notes

## Overview

LearnTrack is a console-based Student and Course Management System developed using Core Java. The project follows a layered architecture to separate responsibilities and improve maintainability.

---

## Architecture

The project is divided into the following layers:

- Entity Layer
- Repository Layer
- Service Layer
- Utility Layer
- Main (User Interface)

Each layer has a specific responsibility, making the application easier to understand, maintain, and extend.

---

## Design Decisions

### 1. Why is Person an Abstract Class?

Person is designed as an abstract class because it represents common information shared by different types of people in the system.

Common attributes include:
- ID
- First Name
- Last Name
- Email

Student and Trainer inherit these common properties instead of duplicating them.

Using an abstract class improves code reusability and follows the DRY (Don't Repeat Yourself) principle.

---

### 2. Why Inheritance?

Inheritance is used because a Student **is a** Person.

Instead of creating duplicate fields in Student and Trainer, they inherit the common properties from Person.

This improves maintainability and reduces code duplication.

---

### 3. Why Repository Layer?

The Repository layer manages data storage.

Although this project uses in-memory `ArrayList` collections instead of a database, using a repository keeps data access separate from business logic.

If a database is introduced in the future, only the repository layer would need to change.

---

### 4. Why Service Layer?

The Service layer contains the business logic.

Examples include:

- Validating student details
- Activating or deactivating courses
- Enrolling students
- Updating enrollment status

Keeping business logic inside services makes the application modular and easier to maintain.

---

### 5. Why Utility Classes?

Utility classes provide reusable functionality.

- `InputValidator` validates user input.
- `IdGenerator` generates unique IDs for Students, Courses, and Enrollments.

This avoids code duplication across the application.

---

### 6. Why Custom Exceptions?

Custom exceptions improve error handling and make the application more readable.

The project uses:

- `EntityNotFoundException`
- `InvalidInputException`

These exceptions provide meaningful error messages instead of generic Java exceptions.

---

### 7. Why Enums?

Enums are used to represent fixed values.

For example:

- `EnrollmentStatus`

Using enums improves readability, prevents invalid values, and increases type safety.

---

### 8. Why Constructor Overloading?

Constructor overloading provides flexibility while creating objects.

Different constructors can be used depending on the available information, making object creation easier and more readable.

---

### 9. Why Collections?

The project uses `ArrayList` to store Students, Courses, and Enrollments.

Collections provide dynamic storage and simplify searching, updating, and retrieving data during application execution.

---

### 10. Future Improvements

The application can be extended by:

- Integrating a relational database (MySQL/PostgreSQL)
- Developing REST APIs using Spring Boot
- Adding authentication and authorization
- Creating a web-based user interface
- Implementing persistent storage

---

## 11. Why Constructor Injection?

The Service classes receive Repository objects through their constructors instead of creating them internally.

Example:

```java
StudentService(StudentRepository studentRepository)
```

### Benefits

- Reduces tight coupling between classes.
- Makes the code easier to test.
- Follows the Dependency Injection principle.
- Allows repositories to be replaced without modifying the service layer.

---

## 12. Why In-Memory Repository?

The project stores data using `ArrayList` instead of a database because the objective is to practice Core Java concepts.

Advantages:

- No database setup required.
- Faster development for a console application.
- Easy to understand repository design.

If a database is introduced later, only the Repository layer needs to change.

---

## 13. Why Layered Architecture?

The application is divided into different layers, each with a single responsibility.

- **Entity Layer** – Represents the domain model.
- **Repository Layer** – Stores and retrieves data.
- **Service Layer** – Contains business logic.
- **Utility Layer** – Provides reusable helper methods.
- **Main Class** – Handles user interaction through the console.

This separation improves readability, maintainability, and scalability.

---

## 14. Why an Abstract Person Class?

Both Student and Trainer share common attributes such as:

- ID
- First Name
- Last Name
- Email

Instead of duplicating these fields, they inherit them from the abstract `Person` class.

The `Person` class is declared as abstract because it represents a generic person and should not be instantiated directly.

---

## 15. Association Between Service and Repository

Each Service class contains a reference to its corresponding Repository.

For example:

- `StudentService` → `StudentRepository`
- `CourseService` → `CourseRepository`
- `EnrollmentService` → `EnrollmentRepository`

This allows the Service layer to perform business operations while delegating data storage responsibilities to the Repository layer.

---

## 16. Exception Handling Strategy

The application uses custom exceptions to provide meaningful error messages.

Examples include:

- Attempting to search for a non-existing Student.
- Attempting to enroll a student in an invalid Course.
- Invalid user input.

Using custom exceptions improves readability and keeps business logic clean.

---

## 17. Input Validation

All user input is validated before processing.

Examples include:

- Non-empty names.
- Valid email format.
- Valid batch year.
- Valid menu choices.
- Boolean input validation.

Centralizing validation inside the `InputValidator` utility class avoids duplicate validation code.

---

## 18. ID Generation Strategy

Unique IDs are generated automatically using the `IdGenerator` utility class.

Examples:

- Student → `S1`, `S2`, `S3`
- Course → `C1`, `C2`
- Enrollment → `E1`, `E2`

This removes the need for users to manually enter IDs and ensures uniqueness throughout the application.