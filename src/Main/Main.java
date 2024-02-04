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

    public static void exitApplication() {
        System.out.println("Exiting application...");
        System.exit(0);
    }
}
