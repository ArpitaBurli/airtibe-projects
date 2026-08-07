# LearnTrack

## Project Description

LearnTrack is a console-based Student and Course Management System developed using Core Java. It allows administrators to manage students, courses, and enrollments through a menu-driven console application.

The project is designed to strengthen Core Java concepts such as Object-Oriented Programming (OOP), encapsulation, inheritance, polymorphism, collections, exception handling, and clean code practices.


## Features

### Student Management
- Add a new student
- View all students
- Search a student by ID
- Deactivate a student

### Course Management
- Add a new course
- View all courses
- Activate or deactivate a course

### Enrollment Management
- Enroll a student in a course
- View enrollments for a student
- Mark an enrollment as completed
- Cancel an enrollment

## Technologies Used

- Java (Core Java)
- Object-Oriented Programming (OOP)
- Collections (ArrayList)
- Exception Handling
- Console-based User Interface
- Git & GitHub


## How to Compile and Run

### Compile

```bash
javac -d out (Get-ChildItem -Recurse -Filter *.java src/main/java | ForEach-Object { $_.FullName })
```

### Run

```bash
java -cp out com.airtribe.learntrack.Main
```


## Project Structure

```text
src
└── main
    └── java
        └── com
            └── airtribe
                └── learntrack
                    ├── Main.java
                    ├── entity
                    ├── repository
                    ├── service
                    ├── exception
                    ├── util
                    ├── constants
                    └── enums
```

## Class Diagram

The following UML Class Diagram illustrates the relationships between the main classes used in the LearnTrack application.

![LearnTrack Class Diagram](docs/images/class-diagram.png)

## Future Enhancements

- Persist data using a relational database (MySQL/PostgreSQL)
- Build REST APIs using Spring Boot
- Add role-based authentication (Admin/Trainer/Student)
- Develop a web-based user interface
- Generate reports for students and enrollments

# LearnTrack

Short Summary

## Project Description

## Features

## Technologies Used

## Project Structure

## Architecture

## How to Compile and Run

## Class Diagram

## Future Enhancements

## Learning Outcomes