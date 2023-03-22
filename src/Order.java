/* Заказ */
public class Order {
    private long id;            // ид. операции
    private long id_book;       // ид. заказанной книги
    private long id_customer;   // ид. покупателя
    private String dataSold;    // дата заказа    дд.мм.гггг

    public Order(long id, long id_book, long id_customer, String dataSold) {
        this.id = id;
        this.id_book = id_book;
        this.id_customer = id_customer;
        this.dataSold = dataSold;
    }
}
