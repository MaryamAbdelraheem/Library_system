# Library Management System

A robust, Java-based Library Management System designed with **SOLID principles** and **Design Patterns** to ensure high maintainability, scalability, and code quality.

## 📖 Project Overview

This system manages a collection of books and facilitates the borrowing/returning process for users. It supports multiple types of media, including Physical Books, E-Books, and Historical Books, each with its own specific behavior while maintaining a consistent system state.

## 🚀 Key Features

*   **Centralized Library Management**: A single service point for managing inventory and transactions.
*   **Diverse Book Types**: Support for `PhysicalBook`, `EBook`, and `HistoricalBook`.
*   **User Management**: Support for different user profiles (Regular/Premium).
*   **State Integrity**: Sophisticated borrowing logic that ensures book availability is correctly tracked across all types.

---

## 🛠 Refactoring & Design Patterns

The system underwent a major refactoring to move from a tightly coupled architecture to a clean, pattern-oriented design.

### 1. SOLID Principles Applied

*   **S - Single Responsibility**: Logic for book state management is centralized in the base class, while specific implementation details (like notification messages) are handled in subclasses.
*   **O - Open/Closed**: The system is open for extension but closed for modification. New book types can be added (e.g., `AudioBook`) without changing the `LibraryService` or the `BookFactory` logic (once registered).
*   **L - Liskov Substitution**: Fixed a critical bug where subclasses were overriding the borrow logic without updating the availability state. Now, all subclasses correctly behave as `BookInterface` objects without breaking system expectations.
*   **I - Interface Segregation**: Clients interact with the lean `BookInterface`, ensuring they only depend on the methods they actually use.
*   **D - Dependency Inversion**: `LibraryService` no longer depends on concrete classes like `PhysicalBook`. Instead, it depends on the `BookInterface` abstraction.

### 2. Design Patterns

*   **Singleton Pattern**: Implemented in `LibraryService` to ensure a single, consistent state of the library's inventory across the entire application.
*   **Factory Pattern**: Introduced `BookFactory` and `BookType` enum to centralize object creation. This decouples the client (`Main`) from concrete implementations, making the code cleaner and easier to manage.
*   **Template Method Pattern**: The base `Book` class defines the skeleton of the borrowing algorithm in `borrowBook()`, while allowing subclasses to provide specific implementations for the `onBorrow()` hook.

---

## 📁 Project Structure

```text
src/
├── BookInterface.java   # Abstraction for all book types
├── Book.java            # Base implementation & State management
├── PhysicalBook.java    # Concrete implementation for physical media
├── EBook.java           # Concrete implementation for digital media
├── HistoricalBook.java  # Concrete implementation for rare media
├── BookType.java        # Enum for supported media types
├── BookFactory.java     # Factory for creating book instances
├── User.java            # Represents library patrons
├── LibraryService.java  # Singleton service for library operations
└── Main.java            # Application entry point
```

---

## 💻 How to Run

1.  **Compile the project:**
    ```bash
    javac -d out/production/librarySystem src/*.java
    ```

2.  **Run the application:**
    ```bash
    java -cp out/production/librarySystem Main
    ```

## 📈 Impact of Refactoring

The refactoring transformed the codebase from a simple script into a professional-grade architectural framework. It reduced code duplication, eliminated state-management bugs, and made the system ready for enterprise-level features like database integration or GUI development.
