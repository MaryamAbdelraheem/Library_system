public class Main {
    public static void main(String[] args) {
        LibraryService alexLibrary = LibraryService.getInstance();

        // Use Factory to create books
        alexLibrary.addBook(BookFactory.createBook(BookType.REGULAR, "Harry Potter"));
        alexLibrary.addBook(BookFactory.createBook(BookType.PHYSICAL, "Lord of the Rings"));
        alexLibrary.addBook(BookFactory.createBook(BookType.EBOOK, "Effective Java"));
        alexLibrary.addBook(BookFactory.createBook(BookType.HISTORICAL, "The Art of War"));

        User user = new User("John", true);

        alexLibrary.borrowBook("Harry Potter", user);
        alexLibrary.borrowBook("Lord of the Rings", user);
        alexLibrary.borrowBook("Effective Java", user);
        alexLibrary.borrowBook("The Art of War", user);

        alexLibrary.returnBook("Harry Potter");
    }
}
