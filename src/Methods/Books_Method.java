package src.Methods;

import src.Entity.Authors;
import src.Entity.Books;
import src.Entity.Customer;
import src.Entity.Orders;
import src.Connectivity.Database_Connection;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Books_Method extends Database_Connection {
    public static boolean addBooks(Books book) {
        try (Connection connection = connect()) {
            PreparedStatement st = connection.prepareStatement("INSERT INTO books (book_id,title,genre,price,stock) VALUES (?,?,?,?,?)");
            st.setInt(1, book.getBook_id());
            st.setString(2, book.getTitle());
            st.setString(3, book.getGenre());
            st.setInt(4, book.getPrice());
            st.setInt(5, book.getStock());
            System.out.println("Inserting book");
            st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        System.out.println("Book inserted successfully");
        return true;
    }

    public static List<Books> getAllBooks() {
        List<Books> books = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM books");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int book_id = res.getInt("book_id");
                String title = res.getString("title");
                String genre = res.getString("genre");
                int price = res.getInt("price");
                int stock = res.getInt("stock");
                System.out.println("book_id = " + book_id + ", " +
                        "title = " + title + ", " +
                        "genre = " + genre + ", " +
                        "price = " + price + "$, " +
                        "stock = " + stock);
                books.add(new Books(book_id, title, genre, price, stock));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return books;
    }

    public static boolean updateBooks(Books book) {
        try (Connection connection = connect()) {
            PreparedStatement st = connection.prepareStatement("UPDATE books SET title=?, genre=?, price=?, stock=? WHERE book_id=?");
            st.setString(1, book.getTitle());
            st.setString(2, book.getGenre());
            st.setInt(3, book.getPrice());
            st.setInt(4, book.getStock());
            st.setInt(5, book.getBook_id());
            System.out.println("Updated successfully");
            st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean deleteBooks(int book_id) {
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("DELETE FROM books WHERE book_id = " + book_id);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        System.out.println("Successfully Deleted book");
        return true;
    }
    public static Books getBookById(int book_id) {
        Books book = null;
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM books WHERE book_id = " + book_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int id = res.getInt("book_id");
                String title = res.getString("title");
                String genre = res.getString("genre");
                int price = res.getInt("price");
                int stock = res.getInt("stock");
                System.out.println("book_id = " + book_id + ", " +
                        "title = " + title + ", " +
                        "genre = " + genre + ", " +
                        "price = " + price + "$, " +
                        "stock = " + stock);
                book = new Books(id, title, genre, price, stock);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        if (book == null) {
            System.out.println("No book found with book_id = " + book_id);
        }
        return book;
    }

    public static List<Books> getAllBookInformation(){
        List<Books> books = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM ((((books NATURAL JOIN booksInfo) JOIN authors USING(author_id))\n" +
                    " JOIN ordersInfo USING(book_id)) JOIN orders USING(order_id)) JOIN customer USING(customer_id) ORDER BY book_id");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int book_id = res.getInt("book_id");
                String title = res.getString("title");
                String genre = res.getString("genre");
                int price = res.getInt("price");
                int stock = res.getInt("stock");
                int order_id = res.getInt("order_id");
                Date order_date = res.getDate("order_date");
                int customer_id = res.getInt("customer_id");
                int total_price = res.getInt("total_price");
                String customer_name = res.getString("customer_name");
                String address = res.getString("address");
                String email = res.getString("email");
                String author_name = res.getString("author_name");
                int author_id = res.getInt("author_id");
                int ordered_books = res.getInt("ordered_books");

                System.out.println("customer_id = " + customer_id + ", " + "\n" +
                        "order_id = " + order_id + ", " + "\n" +
                        "book_id = " + book_id + ", " + "\n" +
                        "author_id = " + author_id + ", " + "\n" +
                        "title = " + title + ", " + "\n" +
                        "genre = " + genre + ", " + "\n" +
                        "price = " + price + ", " + "\n" +
                        "stock = " + stock + ", " + "\n" +
                        "author_name = " + author_name + "\n" +
                        "ordered_books = " + ordered_books + ", " + "\n" +
                        "order_date = " + order_date + ", " + "\n" +
                        "total_price = " + total_price + "\n" +
                        "address = " + address + ", " + "\n" +
                        "email = " + email +  ", " + "\n" +
                        "customer_name = " + customer_name);

                Authors author = new Authors(author_id,author_name);
                Customer customer = new Customer(customer_id,customer_name,address,email);
                Orders order = new Orders(order_id,customer_id,order_date,total_price);

                Books book = new Books(book_id,title,genre,price,stock,author,customer,order);
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return books;
    }
}