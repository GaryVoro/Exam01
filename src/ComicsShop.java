//import Console.Console;

import java.util.ArrayList;
import java.util.Scanner;

public class ComicsShop extends Shop {
    //public ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        ComicsShop myShop = new ComicsShop();
        myShop = myShop.mainMenu(myShop);

        System.out.println("*** Магазин закрылся ***");


        int num = 0;

        //меню
        //1. Вывести все комиксы.-->Новинки, Самые продаваемые, Самых популярных авторов,
        //                          Самых популярных жанров по итогам дня, недели, месяца, года
        //2. Добавить книгу.
        //3. Удалить книгу.
        //4. Отсортировать книги.--> Название, Автор, Жанр
        //5. Редактировать параметры книги. -->
        //6. Продать книгу.
        //7. Зарезервировать книгу.
        //8. Списать книгу.
        //9. Внести книгу в акцию. --> Новогодняя тематика, Черная пятница

    }

    private ComicsShop mainMenu(ComicsShop menu) {
        Scanner scnr = new Scanner(System.in);
        System.out.println(" *** Добро пожаловать в магазин комиксов! *** ");
        int selection = 0;

        menu.initData();

        do {
            System.out.println("[1] Информация и статистика. >>>");
            System.out.println("[2] Добавить книгу.");
            System.out.println("[3] Удалить книгу.");
            System.out.println("[4] Показать книги. >>>");
            System.out.println("[5] Редактировать параметры книги.");
            System.out.println("[6] Продать книгу. >>>");
            System.out.println("[7] Зарезервировать книгу.");
            System.out.println("[8] Списать книгу.");
            System.out.println("[9] Внести книгу в акцию. >>>");
            System.out.println("[0] Выход.");

            System.out.print("Введите номер выбора: ");
            selection = scnr.nextInt();

            switch (selection) {
                case 1: return menu.submenu1(menu);
                case 2: System.out.println("2"); break;
                case 3: menu.submenu3(menu); break;
                case 4: return menu.submenu4(menu);
                case 5: menu.editBook(); break;
                case 6: menu.submenu6(menu); break;
                case 7: System.out.println("7"); break;
                case 8: System.out.println("8"); break;
                case 9: return menu.submenu9(menu);
                case 0: return menu;
                default:
                    System.out.println("Ошибочный выбор!");
            }
        } while (selection != 0);
        return menu;
    }
    /*********************************************************************/
    private ComicsShop submenu1(ComicsShop menu) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Информация и статистика");

        int selection = 0;

        do {
            System.out.println("[1] Список новинок");
            System.out.println("[2] Список самых продаваемых комиксов");
            System.out.println("[3] Список самых популярных художников/авторов");
            System.out.println("[4] Список самых популярных жанров");
            System.out.println("[0] Выход");

            System.out.print("Введите номер выбора: ");
            selection = scnr.nextInt();

            switch (selection) {
                case 1: menu.work(); break;
                case 2: menu.viewBestsellingBooks(); break;
                case 3: menu.viewMostPopularAuthor(); break;
                case 4: System.out.println("4"); break;
                case 0: return menu.mainMenu(menu);
                default:
                    System.out.println("The selection was invalid!");
            }
        } while (selection != 0);
        return menu;
    }
    private ComicsShop submenu4(ComicsShop menu) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Параметры отбора книг");

        int selection = 0;

        do {
            System.out.println("[1] Все");
            System.out.println("[2] Название комикса");
            System.out.println("[3] Художник/автор");
            System.out.println("[4] Жанр");
            System.out.println("[0] Выход");

            System.out.print("Введите номер выбора: ");
            selection = scnr.nextInt();

            switch (selection) {
                case 1: menu.info_assortiment(selection); /*System.out.println("1");*/ break;
                case 2:
                    System.out.println("2"); break;
                case 3:
                    scnr.nextLine();
                    System.out.print("Введите имя автора: ");
                    String str = scnr.nextLine();
                    menu.setStrcomparison(str);
                    menu.info_assortiment(selection);
                    System.out.println("3"); break;
                case 4: System.out.println("4"); break;
                case 0: return menu.mainMenu(menu);
                default:
                    System.out.println("Ошибочный выбор!");
            }
        } while (selection != 0);
        return menu;
    }
    /*****************************************************************/
    private ComicsShop submenu3(ComicsShop menu) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Введите индекс книги для удаления: ");
        int selection = 0;
        selection = scnr.nextInt();
        if(selection > 0) menu.deleteBook(selection);
        else System.out.println("Введен неправильный индекс книги.");
        return menu;
    }
    /**************************/
    private ComicsShop submenu6(ComicsShop menu) {
        Scanner scnr = new Scanner(System.in);
        int selection1 = 0;
        System.out.println("Выбирете покупателя и укажите код книги");
        do {
            System.out.println("[1] Сидоров Алексей");
            System.out.println("[2] Романов Иван");
            System.out.println("[3] Симонов Эльдар");
            System.out.println("[4] Погодина Татьяна");
            System.out.println("[5] Кондрашина Светлана");
            System.out.println("[6] Молодцова Людмила");
            System.out.println("[7] Телятников Сергей");
            System.out.println("[8] Айтуганова Эльвира");
            System.out.println("[0] Выход");

            System.out.print("Введите номер выбора: ");
            selection1 = scnr.nextInt();

            Integer selection2 = 0;
            System.out.print("Введите код книги: ");
            selection2 = scnr.nextInt();

            if (selection1 > 0 && selection1 < 9) {
                menu.sellBook(selection1, selection2);
            } else if (selection1 == 0) return menu;
              else System.out.println("The selection was invalid!");

        } while (selection1 != 0);
        return menu;
    }

    /**************************/
    private ComicsShop submenu9(ComicsShop menu) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Информация и статистика");

        int selection = 0;

        do {
            System.out.println("[1] Неделя комиксов новогодней тематики");
            System.out.println("[2] Скидка к черной пятнице");
            System.out.println("[0] Выход");

            System.out.print("Введите номер выбора: ");
            selection = scnr.nextInt();

            switch (selection) {
                case 1: System.out.println("1"); break;
                case 2: System.out.println("2"); break;
                case 0: return menu.mainMenu(menu);
                default:
                    System.out.println("The selection was invalid!");
            }
        } while (selection != 0);
        return menu;
    }
/*
    @Override
    public void work() {

    }

 */
}