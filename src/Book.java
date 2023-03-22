import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public class Book implements BookInfo{
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private LocalDate dateReceipt;
    private Integer amountReceipt;
    private Integer amount;
    private String genre; // жанр
    private Integer series;     //номер книги в серии (продолжении)

    //DateTimeFormatter sdfr = DateTimeFormatter.ofPattern("dd.mm.yyyy", new Locale("ru"));
    DateTimeFormatter sdfr = DateTimeFormatter
            .ofLocalizedDate(FormatStyle.MEDIUM)
            .withLocale(Locale.GERMAN);

   // private String strcomparison;

    public Book(Integer id, String title, String author, String publisher, String dateReceipt, Integer amountReceipt, Integer amount, String genre, Integer series) throws ParseException {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.dateReceipt = LocalDate.parse(dateReceipt,sdfr); //sdfr.parse(dateReceipt);
       this.amountReceipt = amountReceipt;
        this.amount = amount;
        this.genre = genre;
        this.series = series;
    }


    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDateReceipt() {
        return dateReceipt;
    }

    public void setDateReceipt(String dateReceipt) throws ParseException {
        this.dateReceipt = LocalDate.parse(dateReceipt, sdfr);
    }

    public Integer getAmountReceipt() {
        return amountReceipt;
    }

    public void setAmountReceipt(Integer amountReceipt) {
        this.amountReceipt = amountReceipt;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        series = series;
    }

    @Override
    public String viewInfo() {
        String info = null;

            info = id.toString() + "," +
                    title       + "," +
                    author      + ","+
                    publisher   + ","+
                    sdfr.format(dateReceipt) + ","+
                    amountReceipt.toString() + ","+
                    amount.toString() + ","+
                    genre       + ","+
                    series.toString();
        return info;
    }

   // public void setStrcomparison(String strcomparison) {
   //     this.strcomparison = strcomparison;
   // }
}
