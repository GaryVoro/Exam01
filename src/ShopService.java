import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ShopService {
    void work();
    void viewBestsellingBooks();
    void viewBookToId(Integer id);
    void viewMostPopularAuthor();
    void writeEditBooks();
    boolean sellBook(int id1, Integer id2);
    void writeBookSale();
}
