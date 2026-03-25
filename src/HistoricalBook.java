public class HistoricalBook extends Book {
    public HistoricalBook(String title) {
        super(title);
    }
    @Override
    protected void onBorrow(User user) {
        System.out.println("Historical Book: " + getTitle() + " has been borrowed by " + user.getName() + ".");
    }
 }
