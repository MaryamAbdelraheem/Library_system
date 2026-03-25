public class BookFactory {
    public static BookInterface createBook(BookType type, String title) {
        switch (type) {
            case PHYSICAL:
                return new PhysicalBook(title);
            case EBOOK:
                return new EBook(title);
            case HISTORICAL:
                return new HistoricalBook(title);
            case REGULAR:
            default:
                return new Book(title);
        }
    }
}
