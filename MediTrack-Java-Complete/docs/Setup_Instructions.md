# MediTrack Setup Instructions

## 1. Install JDK

Install a supported JDK such as JDK 17 or later.

Verify:

```bash
java -version
javac -version
```

Both commands should point to the intended JDK installation.

## 2. Configure JAVA_HOME

Windows example:

```text
JAVA_HOME=C:\Program Files\Java\jdk-21
```

Linux/macOS example:

```bash
export JAVA_HOME=/path/to/jdk
export PATH=$JAVA_HOME/bin:$PATH
```

Verify:

```bash
echo %JAVA_HOME%
```

on Windows CMD, or:

```bash
echo $JAVA_HOME
```

on Linux/macOS.

## 3. Compile

```bash
javac -d out $(find src -name "*.java")
```

Windows PowerShell:

```powershell
Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName } > sources.txt
javac -d out @sources.txt
```

## 4. Run

```bash
java -cp out com.airtribe.meditrack.Main
```

## 5. Screenshots

Add your own screenshots here before submission:
- `java -version`
- `javac -version`
- compilation success
- application startup
- sample menu operation
- `--loadData` execution
