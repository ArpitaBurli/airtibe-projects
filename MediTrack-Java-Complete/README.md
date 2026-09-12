# MediTrack – Java OOP Console Application

MediTrack is a menu-driven medical appointment and billing application built to demonstrate Java fundamentals, OOP, collections, generics, exceptions, cloning, immutability, enums, interfaces, file I/O, streams/lambdas, design patterns, testing, JavaDocs, and command-line usage.

## Requirements
- JDK 17+ (JDK 20/21/25 also works)
- No external dependencies
- Git

## Run
From the project root:

```bash
javac -d out $(find src -name "*.java")
java -cp out com.airtribe.meditrack.Main
```

On Windows PowerShell:

```powershell
Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName } > sources.txt
javac -d out @sources.txt
java -cp out com.airtribe.meditrack.Main
```

To load CSV data:

```bash
java -cp out com.airtribe.meditrack.Main --loadData
```

## Main demonstrations
- Person -> Doctor/Patient inheritance
- MedicalEntity abstraction
- Payable and Searchable interfaces
- Overloading and overriding
- Dynamic dispatch
- Deep cloning
- Immutable BillSummary
- Generic DataStore<T>
- HashMap and ArrayList
- Custom exceptions
- try-with-resources and CSV persistence
- Singleton IdGenerator
- Factory for Bill creation
- Strategy for billing
- Observer-style appointment notifications
- Streams and lambdas
- AtomicInteger and TimerTask
- Manual TestRunner

## Package structure

```text
com.airtribe.meditrack
├── Main.java
├── entity
├── service
├── util
├── exception
├── interface
├── constants
└── test
```
