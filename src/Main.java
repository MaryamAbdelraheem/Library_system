public class Main {
    public static void main(String[] args) {
        LibraryService alexLibrary = LibraryService.getInstance();
        alexLibrary.addBook(new Book("Harry Potter"));
        alexLibrary.addBook(new PhysicalBook("Lord of the Rings"));
        alexLibrary.addBook(new EBook("Effective Java"));

        User user = new User("John", true);

        alexLibrary.borrowBook("Harry Potter", user);
        alexLibrary.borrowBook("Lord of the Rings", user);
        alexLibrary.borrowBook("Effective Java", user);

        alexLibrary.returnBook("Harry Potter");
    }
}
