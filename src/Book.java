
public class Book implements BookInterface {
    private String title;
    private boolean isAvailable;

    public Book(String title) {
        this.title = title;
        this.isAvailable = true;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void borrowBook(User user) {
        if (isAvailable) {
            isAvailable = false;
            onBorrow(user);
        } else {
            System.out.println(title + " is not available.");
        }
    }

    @Override
    public void returnBook() {
        isAvailable = true;
        onReturn();
    }

    protected void onBorrow(User user) {
        System.out.println(user.getName() + " " + title + " has been borrowed.");
    }

    protected void onReturn() {
        System.out.println(title + " has been returned.");
    }
}
