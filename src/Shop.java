import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Shop implements ShopService{
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Price> prices = new ArrayList<>();
    private ArrayList<BookSold> solds = new ArrayList<>();

    private String strcomparison;



    final String fileBook = "Book.in";
    final String fileAddBook = "AddBook.in";
    final String fileDecommissioned = "BookDecommissioned.out";
    final String fileBookOrder = "BookOrder.out";
    final String fileCustomer = "Customer.in";
    final String fileEditBook = "EditBook.in";
    final String filePrice = "Price.in";
    final String fileBookSold = "BookSold.out";
    final String fileEditBooks = "BooksEdit.out";
    final String fileEditPrices = "PricesEdit.out";
    String path = System.getProperty("user.dir") + "\\src\\files\\";

    DateTimeFormatter sdfr = DateTimeFormatter
            .ofLocalizedDate(FormatStyle.MEDIUM)
            .withLocale(Locale.GERMAN);
    private int id_customer;
    private Integer idbook;

    /*
        public static void setBooks(ArrayList<Book> books) {
            Shop.books = books;
        }
    */
    void initData() {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + fileBook))) {
            String line = null;
            line = bufferedReader.readLine();  //заголовок полей
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                //System.out.println(line);
                books.add(new Book(Integer.parseInt(words[0]),// Integer id;
                        words[1],                           // String title;
                        words[2],                           // String author;
                        words[3],                           // String publisher;
                        words[4],                           // String dateReceipt;
                        Integer.parseInt(words[5]),         // String amountReceipt;
                        Integer.parseInt(words[6]),         // Float amount;
                        words[7],                           // String genre; жанр
                        Integer.parseInt(words[8])));       // Integer Series;
            }

        } catch (IOException | ParseException e) { e.printStackTrace(); }
       /* for (Book item : books) {
            System.out.println(item.getAuthor());
        }
        */
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + filePrice))) {
            String line = null;
            line = bufferedReader.readLine();  //заголовок полей
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                //System.out.println(line);
                prices.add(new Price(Integer.parseInt(words[0]),// Integer id;
                        Integer.parseInt(words[1]),           // Integer id_boob
                        Float.parseFloat(words[2]),         //
                        Float.parseFloat(words[3])));       //
            }

        } catch (IOException e) { e.printStackTrace(); }
        /*for (Price item : prices) {
            System.out.println(item.getId_Book() + "  " + item.getCostPrice());
        }
         */

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + fileBookSold))) {
            String line = null;
            line = bufferedReader.readLine();  //заголовок полей
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                //System.out.println(line);
                solds.add(new BookSold(
                        Integer.parseInt(words[0]),   // int id;
                        Integer.parseInt(words[1]),   // int id_book
                        words[2],                   // date as string
                        Integer.parseInt(words[3]))); // num instance
            }
        } catch (IOException e) { e.printStackTrace(); }

    }
/*************************************************************************************/
        @Override
    public void work() {
        // Сортируем ArrayList используя метод Collections.sort()
            BooksSort compar = new BooksSort();
            List<Book> s_books = books.stream() //сортируем список по датам
                .sorted(compar) //Comparator.comparing(Book::getDateReceipt)
                .collect(Collectors.toList());
        //        .forEach(n-> System.out.println(n.viewInfo()));
        /*1*/
        //s_books.forEach(n-> System.out.println(n.viewInfo()));

        Iterator<Book> iterator = s_books.iterator();
        for (int i = 0; i < 5 && iterator.hasNext(); i++)
            System.out.println(iterator.next().viewInfo());

        System.out.println("*****************************************");

/*
            Collections.sort(books, new BooksSort());
            for (Book item : books) {
                System.out.println(item.viewInfo());
            }
*/

    }
/*************************************************************************************/
    @Override
    public void viewBestsellingBooks() {
        List<BookSold> s_books = solds.stream()
                .sorted(Comparator.comparing(BookSold::getNum_instance).reversed()) //Comparator.comparing(Book::getDateReceipt)
                .collect(Collectors.toList());
                //.forEach(n-> n.viewData());
        /* 1 */
    //    s_books.forEach(n-> n.viewData());
    //    System.out.println("*****************************************");
        List <Integer> newList = new ArrayList<>();
        for(BookSold n : s_books){
            if (!newList.contains(n.getId_book())) {
                newList.add(n.getId_book());
            }
        }

        System.out.println("Самые продаваемые комиксы:");
        for (Integer n : newList.subList(0, 4))
        {          viewBookToId(n);        }

        System.out.println("*****************************************");

    }
/************************************************************************************/
    @Override
    public void viewBookToId(Integer id_book) {
        for (Book item : books) {
            if (item.getId() == id_book) {
                System.out.println(item.viewInfo());
            }
        }
    }
/************************************************************************************/
    @Override
    public void viewMostPopularAuthor() {
        System.out.println("************************************");
        System.out.println("Самые популярные авторы:");
        List<BookSold> s_books = solds.stream()
                .sorted(Comparator.comparing(BookSold::getNum_instance).reversed()) //Comparator.comparing(Book::getDateReceipt)
                .collect(Collectors.toList());

        List <Integer> newList = new ArrayList<>();
        for(BookSold n : s_books){
            if (!newList.contains(n.getId_book())) {
                newList.add(n.getId_book());
            }
        }

        Map <String, Integer> frequencyMap = new LinkedHashMap<>();
        String str = null;
        for (Integer n : newList)
        {
           str = books.get(Math.toIntExact(n)-1).getAuthor();
           if (frequencyMap.containsKey(str)) {//если слово уже содержится с массиве - увеличиваем счетчик наличия слова
                int currentFrequency = frequencyMap.get(str);
                frequencyMap.replace(str, currentFrequency + 1);
            } else {
                //На данный момент частота данного слова равна 1, т.к мы встретили это слово в первый раз.
                frequencyMap.put(str, 1); /* если слова нет в массиве, добавить его в массив и счетчик установить в 1*/
            }
        }
        /* вывод первых 5 самых популярных авторов */
        frequencyMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()); // .forEach(System.out::println);
        Iterator<Map.Entry<String, Integer>> itr = frequencyMap.entrySet().iterator();
        int i =0;
        while (itr.hasNext() && (i <= 4)) { // 5 самых, либо все
            System.out.println(itr.next().getKey());
            i++;
        }
    }
/*************************************************************************************/
    @Override
    public void writeEditBooks() {

        File fileEBooks = new File(path + fileEditBooks);
        File fileEPrices = new File(path + fileEditPrices);
        /* вариант записи в одну таблицу
        ArrayList <String> joinBookPrices = new ArrayList<>();

        int i = 0;
        for (Book item: books) {
            joinBookPrices.add(item.viewInfo() + ","
                    + prices.get(i).getCostPrice() + ","
                    + prices.get(i).getCostSale());
            i++;
        }
        int u = 0;
         try {
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (String current : joinBookPrices){
                writer.write(current+ "\n");
            }

            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
         */

        try {
            fileEBooks.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileEBooks));

            for (Book item : books){
                writer.write(item.viewInfo() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            fileEPrices.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileEPrices));

            for (Price item : prices){
                writer.write(item.viewInfo() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
/************************************************************************************/
    @Override
    public boolean sellBook(int id_customer, Integer idbook) {

        Integer currentinstance = 0;
        String dateSale = LocalDate.now().format(sdfr);


        Book seelbook = books.stream()
                .filter(n->idbook.equals(n.getId()))
                .findAny()//terminal operartion
                .orElse(null);

        if (seelbook == null){
            System.out.println("Ошибочный код книги");
            return false;
        }

        Integer index = books.indexOf(seelbook); //номер найденного объекта
        if (books.get(index).getAmount() == 0){
            System.out.println("Экземпляры данной книги закончиличь");
            return false;
        }

        books.get(index).setAmount(books.get(index).getAmount()-1);//уменьшить кол. экз. книги в магазине
        info_assortiment(1);
        /* определить продаванный номер экземляра в таб. BookSold */
        /* получить наибольший проданный экземпляр
        BookSold maxnumexeplar = solds.stream()
                .max(Comparator.comparingInt(BookSold::getNum_instance))
                .get();
        Integer num = maxnumexeplar.getNum_instance();
        System.out.println(num);
         */

        for(BookSold item : solds){ //найти последний номер экземпляра проданной книги
            if(item.getId_book() == idbook){
                currentinstance = item.getNum_instance();
            }
        }

        if(currentinstance == 0){ //книга еще не продавалась

        }
        currentinstance++; //увел. кол. продаж

        /* записать таблицу Book */
        writeEditBooks();

        /* записать таблицу BookSold */
        BookSold booksale = new BookSold(solds.size()+1,
                idbook,
                dateSale,
                currentinstance);
        solds.add(booksale);

        return true;
    }
/***************************************************************************************/
    @Override
    public void writeBookSale() {

        File fileEBooks = new File(path + fileBookSold);


        try {
            fileEBooks.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileEBooks));

            for (BookSold item : solds){
                writer.write(item.viewData() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void info_assortiment(int parameter) {
        String[] arrInfo = new String[books.size()];
        int index = 0;
            for (Book item : books) {
                if (parameter == 1) {
                    arrInfo[index] = item.viewInfo();
                    index++;
                } else if (parameter == 2) {

                } else if (parameter == 3) {
                    if (item.getAuthor().equals(strcomparison)) {
                        arrInfo[index] = item.viewInfo();
                        index++;
                    }
                }
            }
            if(index != 0 ){
                for (int i = 0; i< index; i++) { System.out.println(arrInfo[i]); }
            } else System.out.println("По Вашему запросу ничего не найдено.");
    }

    public void setStrcomparison(String str) {
        this.strcomparison = str;
    }

    void editBook() {
        String file = System.getProperty("user.dir") + "\\src\\files\\" + fileEditBook;
        Integer id_book = null;
        Float amount;
        Integer series;
        LocalDate dateReceipt = null;

/* заполнить данные о книге */
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = null;
            line = bufferedReader.readLine();  //заголовок полей
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                //валидация данных
                try {
                    id_book = Integer.parseInt(words[0]);
                    amount = Float.parseFloat(words[6]);
                    series = Integer.parseInt(words[8]);
                } catch (NumberFormatException e){
                    System.out.println("Ошибка в данных" + e.getMessage());
                }

                try {
                    dateReceipt = LocalDate.parse(words[4], sdfr);
                } catch (DateTimeParseException e) { // Throw invalid date message
                    System.out.println("Неправильный формат даты ");
                }
                //
                Integer idbook = Integer.parseInt(words[0]);
                Book bk = new Book(
                        idbook,
                        words[1],                   // String title;
                        words[2],                   // String author;
                        words[3],                   // String publisher;
                        words[4],                   // String dateReceipt;
                        Integer.parseInt(words[5]), // Integer amountReceipt;
                        Integer.parseInt(words[6]), // Integer amount;
                        words[7],                   // String genre; жанр
                        Integer.parseInt(words[8]));// Integer Series;

               books.set(id_book-1, bk);
/* получить id записи в таблице цен, по которому будет запись обновлена*/
               Price oldPrice = prices.stream()
                       .filter(n->idbook.equals(n.getId_Book()))
                       .findAny()//terminal operartion
                       .orElse(null);
                assert oldPrice != null;
                Integer id = oldPrice.getId();

                /*  обновить запись в таблице цен*/
                Price newPrice = new Price(id,
                        idbook,
                        Float.parseFloat(words[9]),
                        Float.parseFloat(words[10]));

                prices.set(id-1,newPrice);

            } // while ((line = bufferedReader.readLine()) != null) {
        } catch (IOException | ParseException e) { e.printStackTrace(); }
        System.out.println("Данные для редактирования считаны успешно! " + dateReceipt);
        //info_assortiment(1);
        writeEditBooks();

    }

    void deleteBook(Integer current_id){
        boolean rez1;
        boolean rez2;
        rez1 = books.removeIf(b -> current_id.equals(b.getId()));
        rez2 = prices.removeIf(p->current_id.equals(p.getId_Book()));
        if(rez1 && rez2) System.out.println("Book is delete!");
        else System.out.println("Error delete of book!");
        writeEditBooks();
    }

}
