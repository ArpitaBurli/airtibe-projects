# Design Notes

## Architecture

```text
Main
 |
 +-- PatientService ---- DataStore<Patient>
 |
 +-- DoctorService ----- DataStore<Doctor>
 |
 +-- AppointmentService - DataStore<Appointment>
 |
 +-- BillFactory -------- BillingStrategy
 |
 +-- CSVUtil ------------ Filesystem
```

## OOP mapping

- Encapsulation: private fields + validation
- Inheritance: Person -> Doctor / Patient
- Abstraction: MedicalEntity
- Polymorphism: Person reference to Patient, overloaded search, overridden behavior
- Interface: Searchable, Payable
- Generics: DataStore<T>
- Immutability: BillSummary
- Enum: Specialization, AppointmentStatus
- Static initialization: Validator/Main
- Cloning: Patient and Appointment

## Patterns

- Singleton: IdGenerator
- Factory: BillFactory
- Strategy: BillingStrategy
- Observer: AppointmentNotifier + NotificationObserver
- Template Method: not required in the core application; can be added as an extension.


## Important Java naming note

The brief says the package should be named `interface`. That exact package name cannot compile because `interface` is a Java reserved keyword. Therefore this implementation uses:

```text
com.airtribe.meditrack.interfaces
```

This is the correct Java-compatible equivalent and still satisfies the requirement to have a dedicated interface package.
