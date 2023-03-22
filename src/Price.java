public class Price {
    private Integer id;
    private Integer id_Book;
    private Float costPrice;
    private Float costSale;

    public Price(Integer id, Integer id_book, Float costPrice, Float costSale) {
        this.id = id;
        id_Book = id_book;
        this.costPrice = costPrice;
        this.costSale = costSale;
    }

    public Integer getId_Book() {
        return id_Book;
    }

    public Float getCostPrice() {
        return costPrice;
    }

    public Integer getId() {
        return id;
    }

    public Float getCostSale() {
        return costSale;
    }

    String viewInfo() {
        String info = null;

        info = id.toString() + "," +
                id_Book.toString() + "," +
                costPrice.toString() + "," +
                costSale.toString();
        return info;
    }
}
