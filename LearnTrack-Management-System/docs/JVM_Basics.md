# JVM Basics

## Introduction

Java is a platform-independent programming language because Java source code is compiled into bytecode, which can run on any operating system that has a Java Virtual Machine (JVM).

---

## Java Compilation Process

The execution flow of a Java program is:

```text
Java Source Code (.java)
          │
          ▼
     Java Compiler (javac)
          │
          ▼
      Bytecode (.class)
          │
          ▼
 Java Virtual Machine (JVM)
          │
          ▼
 Machine Code
          │
          ▼
 Program Execution
```

---

## JDK, JRE, and JVM

### JDK (Java Development Kit)

The JDK provides everything required to develop Java applications.

It includes:
- Java Compiler (`javac`)
- Java Runtime Environment (JRE)
- Development tools

---

### JRE (Java Runtime Environment)

The JRE provides the environment required to run Java applications.

It includes:
- JVM
- Core Java libraries

The JRE does not contain development tools like the Java compiler.

---

### JVM (Java Virtual Machine)

The JVM is responsible for executing Java bytecode.

Its responsibilities include:

- Loading classes
- Verifying bytecode
- Managing memory
- Executing bytecode
- Performing Garbage Collection

---

## Why Java is Platform Independent

Java source code is compiled into platform-independent bytecode.

The same bytecode can run on different operating systems because each operating system has its own JVM implementation.

This follows the principle:

> **Write Once, Run Anywhere (WORA)**

---

## Memory Areas in JVM

The JVM manages memory using different runtime areas.

- Heap Memory – Stores objects and instance variables.
- Stack Memory – Stores method calls, local variables, and references.
- Method Area – Stores class metadata and static members.
- Program Counter Register – Keeps track of the current executing instruction.
- Native Method Stack – Supports native (non-Java) methods.

---

## Garbage Collection

Garbage Collection automatically removes objects that are no longer referenced by the application.

This helps:
- Free unused memory
- Reduce memory leaks
- Improve memory management

---

## JVM in LearnTrack

While developing the LearnTrack application:

- Source files were compiled using `javac`.
- The generated `.class` files were executed by the JVM.
- Objects such as Student, Course, and Enrollment were created in Heap Memory.
- Local variables inside methods were stored in Stack Memory.
- Unused objects were automatically cleaned by the Garbage Collector.