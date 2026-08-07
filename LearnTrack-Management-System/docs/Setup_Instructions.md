# Setup Instructions

## Prerequisites

Before running the project, ensure the following software is installed:

- Java Development Kit (JDK) 25 (LTS)
- IntelliJ IDEA Community Edition (recommended) or any Java IDE
- Git (optional, for cloning the repository)

---

## Clone the Repository

Clone the repository using Git:

```bash
git clone <repository-url>
```

Replace `<repository-url>` with your GitHub repository URL.

Alternatively, you can download the project as a ZIP file and extract it.

---

## Open the Project

1. Open IntelliJ IDEA.
2. Click **Open**.
3. Select the **LearnTrack-Management-System** project folder.
4. Wait for IntelliJ IDEA to index the project.
5. Verify that the Project SDK is set to **JDK 25**.

---

## Compile the Project

Open PowerShell or Command Prompt from the project root directory and execute:

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src/main/java | ForEach-Object { $_.FullName })
```

### Explanation

- `javac` compiles Java source files.
- `-d out` stores the compiled `.class` files inside the `out` directory.
- `Get-ChildItem -Recurse -Filter *.java` finds all Java source files.
- `ForEach-Object { $_.FullName }` passes the full path of every Java file to the compiler.

This command compiles the entire project, including all packages and classes.

---

## Run the Application

Execute the following command from the project root directory:

```powershell
java -cp out com.airtribe.learntrack.Main
```

### Explanation

- `java` starts the Java Virtual Machine (JVM).
- `-cp out` sets the classpath to the compiled classes inside the `out` directory.
- `com.airtribe.learntrack.Main` is the application's entry point.

After execution, the console-based LearnTrack application starts.

---

## Project Structure

The project follows a layered architecture.

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
                    ├── util
                    ├── enums
                    ├── constants
                    └── exception
```

### Package Description

| Package | Responsibility |
|---------|----------------|
| entity | Domain model classes |
| repository | In-memory data storage using ArrayList |
| service | Business logic |
| util | Utility/helper classes |
| enums | Enumeration classes |
| constants | Application constants |
| exception | Custom exception classes |

---

## Troubleshooting

If the project does not compile or run correctly:

- Verify Java installation using:

```bash
java -version
```

- Verify the Java compiler using:

```bash
javac -version
```

- Ensure the package names match the folder structure.

- Run the compile command from the project root directory.

- If required, delete the `out` directory and compile the project again.

---

## Expected Output

After running the application, the main menu is displayed with options to manage:

- Students
- Courses
- Enrollments

The application runs entirely through the console.