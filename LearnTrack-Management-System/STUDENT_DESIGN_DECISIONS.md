# LearnTrack - Design Decisions

This document records the important design decisions made while developing the LearnTrack project. For every decision, the reason for choosing it and the drawbacks of alternative approaches are documented.

---

# Student Module

## 1. Why did I create a `StudentRepository` object instead of making everything static?

### Decision

Created a `StudentRepository` object and stored the student list as an instance variable.

### Reason

* A repository represents an object responsible for managing student data.
* It follows Object-Oriented Programming principles.
* In Spring Boot, repositories are managed as objects through Dependency Injection.

### If I had used static

* No need to create an object.
* Difficult to test and mock.
* Not compatible with Dependency Injection.
* Poor object-oriented design.

---

## 2. Why is the `students` list an instance variable instead of a static variable?

### Decision

```java
private ArrayList<Student> students = new ArrayList<>();
```

### Reason

Every repository object owns and manages its own collection.

### If I had used static

```java
private static ArrayList<Student> students = new ArrayList<>();
```

* Every repository object would share the same data.
* Makes testing difficult.
* Reduces flexibility.
* Creates unnecessary global state.

---

## 3. Why does `Main` not create the `Student` object?

### Decision

`Main` collects user input and passes it to `StudentService`.

### Reason

Object creation is part of the business logic.

`StudentService`:

* Generates the Student ID.
* Creates the `Student` object.
* Passes it to the repository.

### If I had created the object in `Main`

* UI would contain business logic.
* ID generation logic would leak into the presentation layer.
* Difficult to maintain.

---

## 4. Why is Student ID generated inside the Service?

### Decision

Student IDs are generated in the service before creating the `Student` object.

### Reason

Generating IDs is part of the application's business logic.

### If I had generated IDs in `Main`

* UI would know business rules.
* ID generation logic would be duplicated if students were added from another source (file, API, etc.).

---

## 5. Why doesn't the Repository generate Student IDs?

### Decision

The repository only stores and retrieves data.

### Reason

A repository should not make business decisions.

### If I had generated IDs in the Repository

The repository would have multiple responsibilities:

* Data storage
* Business logic

This violates the Single Responsibility Principle.

---

## 6. Why does `searchStudentById()` return a `Student` instead of printing it?

### Decision

Repository returns the matching `Student`.

### Reason

Repositories should return data, not display it.

### If Repository printed the student

* UI logic would be mixed with data access.
* Other layers couldn't reuse the returned object.

---

## 7. Why does `viewAllStudents()` return a list instead of printing?

### Decision

Repository returns:

```java
List<Student>
```

### Reason

The service or UI decides how to display the data.

### If Repository printed the list

* Repository becomes dependent on console output.
* Data cannot be reused elsewhere.

---

## 8. Why is student deactivation handled in the Service?

### Decision

The service changes the student's active status.

### Reason

Changing a student's status is a business operation.

### If Repository handled deactivation

Repository would start containing business logic instead of only data access.

---

## 9. Why did I create `InputValidator`?

### Decision

All user input validation is centralized in one utility class.

### Reason

Avoids repeating validation code throughout `Main`.

### If I validated directly in `Main`

* Repeated code.
* Harder to maintain.
* Less reusable.

---

## 10. Why is `IdGenerator` separate from `InputValidator`?

### Decision

Created two separate utility classes.

### Reason

Each class has one responsibility.

* `InputValidator` → validates user input.
* `IdGenerator` → generates unique IDs.

### If both were combined

One class would perform unrelated tasks, making the code harder to understand and maintain.

---

## 11. Why is the ID counter static?

### Decision

```java
private static int count = 1000;
```

### Reason

The counter must be shared across the entire application.

### If it were not static

Every `IdGenerator` object would start counting from 1000, leading to duplicate student IDs.

---

## 12. Why is the ID counter private?

### Decision

```java
private static int count;
```

### Reason

No other class should modify the counter directly.

### If it were public

Any class could accidentally reset the counter, resulting in duplicate IDs.

---

## 13. Why introduce a `Person` class?

### Decision

Moved common fields into a base class.

### Reason

Both `Student` and `Trainer` share:

* id
* firstName
* lastName
* email

### If I hadn't created `Person`

These fields would be duplicated across multiple classes.

---

## 14. Why does `Student` extend `Person`?

### Decision

```java
public class Student extends Person
```

### Reason

A Student **is a** Person.

This models the real-world relationship using inheritance.

### If Student didn't extend Person

Common fields and methods would be duplicated.

---

## 15. Why use `super()` in the constructor?

### Decision

Parent fields are initialized by the parent constructor.

### Reason

Each class initializes only its own fields.

### If I initialized everything in `Student`

The parent class would lose responsibility for its own state.

---

## 16. Why override `getDisplayName()`?

### Decision

`Student` provides its own implementation.

### Reason

Allows specialized behavior while reusing common functionality from `Person`.

### If I didn't override it

All subclasses would behave exactly like `Person`, even when different behavior is required.

---

## 17. Why create `EntityNotFoundException`?

### Decision

Throw a custom exception when a requested entity is not found.

### Reason

Provides meaningful business-specific error messages.

### If I returned `null`

Every caller would need to perform null checks, increasing the chance of bugs.

---

## 18. Why create `InvalidInputException`?

### Decision

Use a custom exception for business validation failures.

### Reason

Separates invalid business input from missing data.

### Example

* Invalid joining year.
* Invalid course capacity.

---

## 19. Why does `Main` handle exceptions?

### Decision

`Main` catches exceptions thrown by the service.

### Reason

The service reports problems, while the UI decides how to display them.

### If Service printed error messages

Business logic would become dependent on console output.

---

## 20. Why use layered architecture?

### Decision

```text
Main (UI)
      │
      ▼
StudentService
      │
      ▼
StudentRepository
      │
      ▼
ArrayList<Student>
```

### Reason

Each layer has one clear responsibility.

* UI → User interaction
* Service → Business logic
* Repository → Data storage
* Entity → Data representation

### If everything were inside `Main`

The application would become difficult to understand, maintain, test, and extend.
