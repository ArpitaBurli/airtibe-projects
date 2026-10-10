# JVM Report

## JDK, JRE and JVM

**JDK** = Java Development Kit. It contains tools such as `javac` and the runtime needed to develop and run Java applications.

**JRE** = Java Runtime Environment conceptually contains the JVM and runtime libraries needed to run Java programs. Modern JDK distributions are normally used directly instead of installing a separate JRE.

**JVM** = Java Virtual Machine. It executes Java bytecode.

Flow:

```text
.java source
   |
 javac
   |
.class bytecode
   |
  JVM
   |
machine code
```

## Class Loader

The Class Loader loads `.class` bytecode into JVM memory.

Important loading stages:
1. Loading
2. Linking
   - Verification
   - Preparation
   - Resolution
3. Initialization

Common class-loader hierarchy:
- Bootstrap ClassLoader
- Platform ClassLoader
- Application/System ClassLoader

## Runtime Data Areas

### Heap
Objects and arrays are generally allocated in the heap. Garbage collection manages unreachable objects.

### Stack
Each thread has its own JVM stack. Method calls create stack frames containing local variables, operand-stack information and references.

### Method Area
Stores class-level metadata such as class structure, runtime constant-pool information and method metadata. In HotSpot this is implemented using Metaspace.

### PC Register
Each JVM thread has a program-counter register identifying the current bytecode instruction being executed.

## Execution Engine

The execution engine executes bytecode. It can initially interpret bytecode and can compile frequently executed code to native machine code.

## Interpreter vs JIT

**Interpreter:** executes bytecode instruction by instruction. Startup can be fast, but repeatedly executed code can be slower.

**JIT compiler:** detects hot code and compiles it to native machine code, improving performance during long-running execution.

Modern JVMs use both techniques.

## Write Once, Run Anywhere

Java source is compiled to platform-independent bytecode. A compatible JVM on Windows, Linux or macOS can execute that bytecode.

```text
Java source
    |
   javac
    |
Bytecode
 /    |    \
JVM  JVM   JVM
Win Linux macOS
```

The JVM abstracts operating-system and CPU differences, although native libraries and environment-specific behavior can still affect portability.
