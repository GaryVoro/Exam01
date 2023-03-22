import java.util.Comparator;

public class BooksSort implements Comparator<Book> {
    @Override
    public int compare(Book a, Book b) {
        return b.getDateReceipt().compareTo(a.getDateReceipt());
    }

}
