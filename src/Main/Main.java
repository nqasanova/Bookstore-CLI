package src.Main;

import src.Entity.*;
import src.Methods.*;
import src.Metadata.Metadata;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("Main Menu");
        while (true) {
            System.out.println("""
                    Select table to perform actions
                    1.books table
                    2.authors table
                    3.customers table
                    4.orders table
                    5.booksInfo table
                    6.ordersInfo table
                    7.Retrieve All Table names and Columns
                    8.Retrieve Column data types
                    9.Retrieve Primary keys
                    10.Retrieve Foreign keys
                    11.Exit from application""");
            int option = sc.nextInt();
            switch (option) {
                case 1 -> bookMenu();
                case 2 -> authorMenu();
                case 3 -> customerMenu();
                case 4 -> orderMenu();
                case 5 -> bookInformationMenu();
                case 6 -> orderInformationMenu();
                case 7 -> {
                    try {
                        Metadata.printTableNamesandColumns();
                        mainMenu();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 8 -> {
                    try {
                        Metadata.printColumnDetails();
                        mainMenu();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 9 -> {
                    try {
                        Metadata.printPrimaryKeys();
                        mainMenu();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 10 -> {
                    try {
                        Metadata.printForeignKeys();
                        mainMenu();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 11 -> exitApplication();
                default -> System.out.println("Invalid option! Please select a valid option.");
            }
        }
    }

    public static void bookMenu() {
        System.out.println("""
            Books table
            1.Insert new book
            2.Retrieve all books
            3.Get book by book_id
            4.Update book
            5.Delete book
            6.Get All book information
            7.Back to choices menu
                        """);
        System.out.print("Choose what to do: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1 -> {
                System.out.print("Book_id: ");
                int book_id = sc.nextInt();
                sc.nextLine();
                System.out.print("Title: ");
                String title = sc.nextLine();
                System.out.print("Genre: ");
                String genre = sc.nextLine();
                System.out.print("Price: ");
                int price = sc.nextInt();
                System.out.print("Stock: ");
                int stock = sc.nextInt();
                Books book = new Books(book_id, title, genre, price, stock);
                Books_Method.addBooks(book);
                bookMenu();
            }
            case 2 -> {
                List<Books> books = Books_Method.getAllBooks();
                if (books.size() == 0) {
                    System.out.println("No record found");
                }
                bookMenu();
            }
            case 3 -> {
                System.out.print("Enter the id of the book you want to get: ");
                int book_id = sc.nextInt();
                Books books = Books_Method.getBookById(book_id);
                bookMenu();
            }
            case 4 -> {
                System.out.print("Enter the id of the book you want to update: ");
                int book_id = sc.nextInt();
                sc.nextLine();
                Books book = Books_Method.getBookById(book_id);
                if (book == null) {
                    bookMenu();
                }

                System.out.println("Enter new values to fields you want to update");
                System.out.print("New title: ");
                String title = sc.nextLine();
                System.out.print("New genre: ");
                String genre = sc.nextLine();
                System.out.print("New price: ");
                int price = sc.nextInt();
                System.out.print("New stock: ");
                int stock = sc.nextInt();

                book.setTitle(title);
                book.setGenre(genre);
                book.setPrice(price);
                book.setStock(stock);

                Books_Method.updateBooks(book);
                bookMenu();
            }
            case 5 -> {
                System.out.print("Enter the id of book you want to delete: ");
                int book_id = sc.nextInt();
                Books book = Books_Method.getBookById(book_id);
                if (book == null) {
                    bookMenu();
                }
                Books_Method.deleteBooks(book_id);
                bookMenu();
            }
            case 6 -> {
                List<Books> books = Books_Method.getAllBookInformation();
                if (books.size() == 0) {
                    System.out.println("No records found");
                }
                bookMenu();
            }
            case 7 -> mainMenu();
            default -> System.out.println("Invalid option! Please select a valid option.");
        }
    }

    public static void authorMenu() {
        System.out.println("""
            Authors table
            1.Insert new author
            2.Retrieve all authors
            3.Get author by author_id
            4.Update author
            5.Delete author
            6.Back to choices menu
                        """);
        System.out.print("Choose what to do: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.print("author_id: ");
                int author_id = sc.nextInt();
                sc.nextLine();
                System.out.print("author_name: ");
                String author_name = sc.nextLine();
                Authors_Method.addAuthors(new Authors(author_id, author_name));
                authorMenu();
            }
            case 2 -> {
                List<Authors> authors = Authors_Method.getAllAuthors();
                if (authors.size() == 0) {
                    System.out.println("No record found");
                }
                authorMenu();
            }
            case 3 -> {
                System.out.print("Enter the id of the author you want to get: ");
                int author_id = sc.nextInt();
                Authors author = Authors_Method.getAuthorById(author_id);
                if (author == null) {
                    System.out.println("No author found");
                }
                authorMenu();
            }
            case 4 -> {
                System.out.print("Enter the id of the author you want to update: ");
                int author_id = sc.nextInt();
                sc.nextLine();
                Authors author = Authors_Method.getAuthorById(author_id);
                if (author == null) {
                    System.out.println("No author found");
                    authorMenu();
                }
                System.out.println("Enter new values to fields you want to update");
                System.out.print("New author_name: ");
                String author_name = sc.nextLine();
                author.setAuthor_name(author_name);
                Authors_Method.updateAuthors(author);
                authorMenu();
            }
            case 5 -> {
                System.out.print("Enter the id of author you want to delete: ");
                int author_id = sc.nextInt();
                Authors author = Authors_Method.getAuthorById(author_id);
                if (author == null) {
                    System.out.println("No author found");
                    authorMenu();
                }
                Authors_Method.deleteAuthors(author_id);
                authorMenu();
            }
            case 6 -> mainMenu();
            default -> System.out.println("Invalid option! Please select a valid option.");
        }
    }

    public static void customerMenu() {
        System.out.println("""
            Customers table
            1.Insert new customer
            2.Retrieve all customers
            3.Get customer by customer_id
            4.Update customer
            5.Delete customer
            6.Back to choices menu
                        """);
        System.out.print("Choose what to do: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.print("customer_id: ");
                int customer_id = sc.nextInt();
                sc.nextLine();
                System.out.print("customer_name: ");
                String customer_name = sc.nextLine();
                System.out.print("customer address: ");
                String address = sc.nextLine();
                System.out.print("customer email: ");
                String email = sc.nextLine();
                Customer_Method.addCustomer(new Customer(customer_id, customer_name, email, address));
                customerMenu();
            }
            case 2 -> {
                List<Customer> Customer = Customer_Method.getAllCustomer();
                if (Customer.size() == 0) {
                    System.out.println("No record found");
                }
                customerMenu();
            }
            case 3 -> {
                System.out.print("Enter the id of the customer you want to get: ");
                int customer_id = sc.nextInt();
                Customer customer = Customer_Method.getCustomerById(customer_id);
                if (customer == null) {
                    System.out.println("No customer found");
                }
                customerMenu();
            }
            case 4 -> {
                System.out.print("Enter the id of the customer you want to update: ");
                int customer_id = sc.nextInt();
                sc.nextLine();
                Customer customer = Customer_Method.getCustomerById(customer_id);
                if (customer == null) {
                    System.out.println("No customer found");
                    customerMenu();
                }
                System.out.println("Enter new values to fields you want to update");
                System.out.print("New customer_name: ");
                String customer_name = sc.nextLine();
                System.out.print("New email: ");
                String email = sc.nextLine();
                System.out.print("New address: ");
                String address = sc.nextLine();
                customer.setCustomer_name(customer_name);
                customer.setEmail(email);
                customer.setAddress(address);
                Customer_Method.updateCustomer(customer);
                customerMenu();
            }
            case 5 -> {
                System.out.print("Enter the id of customer you want to delete: ");
                int customer_id = sc.nextInt();
                Customer customer = Customer_Method.getCustomerById(customer_id);
                if (customer == null) {
                    System.out.println("No customer found");
                    customerMenu();
                }
                Customer_Method.deleteCustomer(customer_id);
                customerMenu();
            }
            case 6 -> mainMenu();
            default -> System.out.println("Invalid option! Please select a valid option.");
        }
    }

    public static void orderMenu() {
        System.out.println("""
            Orders table
            1.Insert new order
            2.Retrieve all orders
            3.Get order by order_id
            4.Update order
            5.Delete order
            6.Back to choices menu
                        """);
        System.out.print("Choose what to do: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.print("order_id: ");
                int order_id = sc.nextInt();
                System.out.print("customer_id: ");
                int customer_id = sc.nextInt();
                sc.nextLine();
                System.out.print("order date: ");
                String date = sc.nextLine();
                Date dateStr = Date.valueOf(date);
                System.out.print("total price: ");
                int price = sc.nextInt();
                Orders_Method.addOrders(new Orders(order_id, customer_id, dateStr, price));
                orderMenu();
            }
            case 2 -> {
                List<Orders> Orders = Orders_Method.getAllOrders();
                if (Orders.size() == 0) {
                    System.out.println("No record found");
                }
                orderMenu();
            }
            case 3 -> {
                System.out.print("Enter the id of the order you want to get: ");
                int order_id = sc.nextInt();
                Orders order = Orders_Method.getOrderById(order_id);
                if (order == null) {
                    System.out.println("No order found");
                }
                orderMenu();
            }
            case 4 -> {
                System.out.print("Enter the id of the order you want to update: ");
                int customer_id = sc.nextInt();
                sc.nextLine();
                Orders order = Orders_Method.getOrderById(customer_id);
                if (order == null) {
                    System.out.println("No order found");
                    orderMenu();
                }
                System.out.println("Enter new values to fields you want to update");
                System.out.print("New customer_id: ");
                int update_customer_id = sc.nextInt();
                sc.nextLine();
                System.out.print("New date: ");
                String date = sc.nextLine();
                Date update_dateStr = Date.valueOf(date);
                System.out.print("New total price: ");
                int total_price = sc.nextInt();
                order.setCustomer_id(update_customer_id);
                order.setOrder_date(update_dateStr);
                order.setTotal_price(total_price);
                Orders_Method.updateOrders(order);
                orderMenu();
            }
            case 5 -> {
                System.out.print("Enter the id of order you want to delete: ");
                int order_id = sc.nextInt();
                Orders orders = Orders_Method.getOrderById(order_id);
                if (orders == null) {
                    System.out.println("No order found");
                    orderMenu();
                }
                Orders_Method.deleteOrders(order_id);
                orderMenu();
            }
            case 6 -> mainMenu();
            default -> System.out.println("Invalid option! Please select a valid option.");
        }
    }

    public static void bookInformationMenu() {
        System.out.println("""
            booksInfo table
            1.Insert new book information
            2.Retrieve all book information
            3.Get all book information by book_id
            4.Update book information
            5.Delete book information
            6.Back to choices menu
                        """);
        System.out.print("Choose what to do: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.print("book_id: ");
                int book_id = sc.nextInt();
                System.out.print("author_id: ");
                int author_id = sc.nextInt();
                BooksInfo_Method.addBooksInformation(new BooksInfo(book_id, author_id));
                bookInformationMenu();
            }
            case 2 -> {
                List<BooksInfo> BooksInformation = BooksInfo_Method.getAllBooksInformation();
                if (BooksInformation.size() == 0) {
                    System.out.println("No record found");
                }
                bookInformationMenu();
            }
            case 3 -> {
                System.out.print("Enter the id of the book you want to get: ");
                int book_id = sc.nextInt();
                List<BooksInfo> booksInformation = BooksInfo_Method.getAllBooksInformationById(book_id);
                if (booksInformation.size() == 0) {
                    System.out.println("No book information found");
                }
                bookInformationMenu();
            }
            case 4 -> {
                System.out.print("Enter the id of the book you want to update: ");
                int book_id = sc.nextInt();
                System.out.print("Enter the id of the author you want to update: ");
                int author_id = sc.nextInt();
                BooksInfo booksInformation = BooksInfo_Method.getBooksInformationById(book_id, author_id);
                if (booksInformation == null) {
                    System.out.println("No book information found");
                    bookInformationMenu();
                }
                System.out.println("Enter new values to fields you want to update");
                System.out.print("New author_id: ");
                int update_author_id = sc.nextInt();
                booksInformation.setAuthor_id(update_author_id);
                BooksInfo_Method.updateBooksInformation(booksInformation);
                bookInformationMenu();
            }
            case 5 -> {
                System.out.print("Enter the id of book you want to delete: ");
                int book_id = sc.nextInt();
                System.out.print("Enter the id of author you want to delete: ");
                int author_id = sc.nextInt();
                BooksInfo booksInformation = BooksInfo_Method.getBooksInformationById(book_id, author_id);
                if (booksInformation == null) {
                    System.out.println("No book information found");
                    bookInformationMenu();
                }
                BooksInfo_Method.deleteBooksInformation(book_id, author_id);
                bookInformationMenu();
            }
            case 6 -> mainMenu();
            default -> System.out.println("Invalid option! Please select a valid option.");
        }
    }

    public static void exitApplication() {
        System.out.println("Exiting application...");
        System.exit(0);
    }
}
