public class EBook extends Book{

    public EBook(String title) {
        super(title);
    }

    @Override
    protected void onBorrow(User user) {
        System.out.println(user.getName() + " borrowed the e-book: " + getTitle());
    }
}
