# Java Test Suite

This repository contains the Java test suite for the **Coding Paradigm** project. Its purpose is to explore and expose subtle performance and behavioural differences in Java code by isolating and testing specific language patterns, memory behaviours, and JVM characteristics.

All tests are hypothesis-driven, minimal, and structured to be reproducible. This test suite is not a benchmark of full applications but a tool for understanding how Java code behaves under different design decisions.

We recommend you to read our [website](https://coding-paradigm.pages.dev/) for entries describing tests and benchmarks under this repository.

## 📌 Features

- Focused, readable tests designed around JVM-specific behaviour
- Uses [JMH (Java Microbenchmark Harness)](https://openjdk.org/projects/code-tools/jmh/) for benchmarking
- Uses JUnit for creating and running test cases
- CI with GitHub Actions

## 🚀 Getting Started

- Java 24 is used
- Explore the `src/test/java` directory for all test cases
- Explore the `src/jmh/java` directory for all benchmarks
