# Contributing to the Coding Paradigm Test Suite

Thank you for your interest in contributing to the Coding Paradigm test suite!

This test suite is designed to demonstrate JVM-level performance and behavioural differences in Java code. Each test case is hypothesis-driven and should clearly illustrate a specific concept (e.g. allocation cost, method inlining effects, loop behaviour).

---

## 🛠 How to Contribute

### 🧱 Structure

- `src/jmh/java` for benchmarks
- `src/test/java` for tests
- Use `Blackhole` where appropriate to avoid dead code elimination

### 🔍 Naming Conventions

- Test and benchmark class names should be descriptive
- Tests must be prefixed with 'T_NumericalID_Name' and benchmarks prefixed with 'BM_NumericalID_Name'
    - Does not matter if it ends up conflicting, the repository maintainers will fix this when the PR is merged

---

## 📖 Documentation

Please include a brief explanation of your test logic in your PR and in the code comments.

---

## ✅ Code Style

Follow the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html). We recommend running a `./gradlew spotlessApply` before PRing.

---

Thank you for helping to teach a more understandable Java!
