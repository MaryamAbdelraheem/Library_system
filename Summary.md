# Library System Project Summary

## Part 1: Detailed Project Summary

The current Library System is a Java-based application designed to manage a collection of books and facilitate borrowing and returning processes for users.

### Current Architecture:
1.  **Core Entities**:
    *   **`BookInterface`**: An interface defining the essential behaviors of a book: `borrowBook(User user)` and `returnBook()`.
    *   **`Book`**: The base class implementing `BookInterface`. It maintains the state of the book (title and availability).
    *   **Subclasses (`EBook`, `PhysicalBook`, `HistoricalBook`)**: These classes extend `Book` to represent specific types of media. Currently, they override the `borrowBook` method to provide type-specific print messages.
    *   **`User`**: Represents a library patron with a name and a "Premium" status.

2.  **Service Layer**:
    *   **`LibraryService`**: Acts as the central controller for the library. It manages a list of `Book` objects and provides methods to:
        *   Add books to the inventory.
        *   Search for books by title.
        *   Handle the borrowing and returning workflow by coordinating between users and books.

3.  **Execution (`Main`)**:
    *   The entry point initializes the `LibraryService`, populates it with sample books, and creates a user.

### Current Issues Identified:
*   **State Inconsistency**: Subclasses of `Book` override `borrowBook` without updating the `isAvailable` flag or calling `super.borrowBook()`. This means borrowing a specific type of book doesn't actually mark it as unavailable in the system.
*   **Tight Coupling**: `LibraryService` is coupled to the concrete `Book` class rather than the `BookInterface`.
*   **Missing Patterns**: The code hints at patterns (like Singleton in `LibraryService`) but hasn't fully implemented them.

---------------------------------------

## Part 2: Applying SOLID Principles

To improve the maintainability, scalability, and robustness of the system, we should apply the SOLID principles as follows:

### 1. Single Responsibility Principle (SRP)
*   **Current State**: `Book` handles both its data and the logic for borrowing/returning (including console output).
*   **Improvement**: Separate the notification/logging logic from the core state management. Use a dedicated `NotificationService` to handle messages, or a `BorrowingManager` to handle the transaction logic, leaving `Book` as a simple data carrier or state manager.

### 2. Open/Closed Principle (OCP)
*   **Current State**: Adding new book types is supported through inheritance.
*   **Improvement**: Ensure that `LibraryService` doesn't need to change when new book types are added. Use the **Factory Pattern** to instantiate books, so the service only interacts with the `BookInterface`.

### 3. Liskov Substitution Principle (LSP)
*   **Current State**: **Violation detected.** Subclasses like `EBook` override `borrowBook` but do not maintain the "isAvailable" state logic of the parent. Replacing a `Book` object with an `EBook` object changes the behavior of the system (the book never becomes "unavailable").
*   **Improvement**: Subclasses should either call `super.borrowBook()` or the base class should use the **Template Method Pattern**, where the base class handles the state logic and calls an abstract `onBorrow()` method for subclass-specific behavior.

### 4. Interface Segregation Principle (ISP)
*   **Current State**: `BookInterface` is small and focused.
*   **Improvement**: If we add features like "Downloading" (only for E-Books) or "Shipping" (only for Physical Books), we should create specific interfaces (`Downloadable`, `Shippable`) instead of bloating `BookInterface`.

### 5. Dependency Inversion Principle (DIP)
*   **Current State**: `LibraryService` depends on the `Book` class.
*   **Improvement**: `LibraryService` should depend on `BookInterface`. This allows the library to manage anything that "behaves like a book" without knowing the specific implementation details.

---

## Proposed Refactoring with Design Patterns
*   **Singleton Pattern**: Ensure only one instance of `LibraryService` exists.
*   **Factory Pattern**: Create a `BookFactory` to handle the creation of different book types based on input.
*   **Strategy Pattern**: Implement different borrowing strategies (e.g., `PremiumBorrowingStrategy` for faster returns or higher limits vs `RegularBorrowingStrategy`).
*   **Observer Pattern**: Allow `User` objects to subscribe to a `Book` to be notified when it is returned and becomes available.
