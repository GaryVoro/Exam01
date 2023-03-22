import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class BookSold {
    private Integer id;
    private Integer id_book;
    private LocalDate dateBookSale;
    private Integer num_instance;

    public Integer getId() {
        return id;
    }

    public Integer getId_book() {
        return id_book;
    }

    public LocalDate getDateBookSale() {
        return dateBookSale;
    }

    DateTimeFormatter sdfr = DateTimeFormatter
            .ofLocalizedDate(FormatStyle.MEDIUM)
            .withLocale(Locale.GERMAN);

    public BookSold(Integer id, Integer id_book, String date, Integer num_instance) {
        this.id = id;
        this.id_book = id_book;
        this.dateBookSale = LocalDate.parse(date,sdfr);;
        this.num_instance = num_instance;
    }

    public Integer getNum_instance() {
        return num_instance;
    }

    public String viewData(){
        String info = null;

        info = Long.toString(id) + "," +
               Integer.toString(id_book) +"," +
               sdfr.format(dateBookSale) + "," +
               Integer.toString(num_instance);
    return info;
    }
}
