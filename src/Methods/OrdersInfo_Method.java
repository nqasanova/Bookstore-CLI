package src.Methods;

import src.Entity.Books;
import src.Entity.OrdersInfo;
import src.Connectivity.Database_Connection;
import src.Entity.OrdersInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrdersInfo_Method extends Database_Connection {
    public static boolean addOrderInformation(OrdersInfo OrderInformation) {
        try (Connection connection = connect();
             PreparedStatement insertOrderStatement = connection.prepareStatement("INSERT INTO ordersInfo (order_id, book_id,ordered_books) VALUES (?, ?, ?)");
             PreparedStatement updateBookStatement = connection.prepareStatement("UPDATE books SET stock = ? WHERE book_id = ?")) {

            connection.setAutoCommit(false);

            int book_id = OrderInformation.getBook_id();
            int quantity = OrderInformation.getOrdered_books();
            Books book = Books_Method.getBookById(book_id);

            if (book != null && book.getStock() >= quantity) {
                insertOrderStatement.setInt(1, OrderInformation.getOrder_id());
                insertOrderStatement.setInt(2, OrderInformation.getBook_id());
                insertOrderStatement.setInt(3, OrderInformation.getOrdered_books());

                insertOrderStatement.executeUpdate();

                int updatedStock = book.getStock() - quantity;
                updateBookStatement.setInt(1, updatedStock);
                updateBookStatement.setInt(2, book_id);

                updateBookStatement.executeUpdate();

                connection.commit();

                System.out.println("Order detail inserted successfully");
                return true;
            } else {
                connection.rollback();
                System.out.println("Not enough books in stock for this order.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    public static List<OrdersInfo> getAllOrderInformation() {
        List<OrdersInfo> OrderInformation = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM ordersInfo");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int order_id = res.getInt("order_id");
                int book_id = res.getInt("book_id");
                int ordered_books = res.getInt("ordered_books");
                System.out.println("order_id = " + order_id + ", " +
                        "book_id = " + book_id + ", " +
                        "ordered_books = " + ordered_books);
                OrderInformation.add(new OrdersInfo(order_id, book_id, ordered_books));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return OrderInformation;
    }

    public static boolean updateOrderInformation(OrdersInfo OrderInformation) {
        try (Connection connection = connect()) {
            PreparedStatement st = connection.prepareStatement("UPDATE ordersInfo SET book_id=?, ordered_books=? WHERE order_id=?");
            st.setInt(1, OrderInformation.getBook_id());
            st.setInt(2, OrderInformation.getOrder_id());
            st.setInt(3, OrderInformation.getOrdered_books());
            System.out.println("Updated successfully");
            st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        return true;
    }
    public static boolean deleteOrderInformation(int order_id, int book_id) {
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("DELETE FROM ordersInfo WHERE order_id = " + order_id + " AND book_id = " + book_id);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        System.out.println("Successfully deleted ordersInfo");
        return true;
    }

    public static List<OrdersInfo> getAllOrderInformationById(int order_id) {
        List<OrdersInfo> orderInformation = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM ordersInfo WHERE order_id = " + order_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int id = res.getInt("order_id");
                int book_id = res.getInt("book_id");
                int ordered_books = res.getInt("ordered_books");
                System.out.println("order_id = " + order_id + ", " +
                        "book_id = " + book_id + ", " +
                        "ordered_books = " + ordered_books);
                orderInformation.add(new OrdersInfo(id, book_id, ordered_books));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return orderInformation;
    }

    public static OrdersInfo getOrderInformationByOrderIdAndBookId(int order_id, int book_id) {
        OrdersInfo orderInformation = null;
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM ordersInfo WHERE order_id = " + order_id + " AND book_id = " + book_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int id = res.getInt("order_id");
                int get_book_id = res.getInt("book_id");
                int ordered_books = res.getInt("ordered_books");
                System.out.println("order_id = " + order_id + ", " +
                        "book_id = " + get_book_id + ", " +
                        "ordered_books = " + ordered_books);
                orderInformation = new OrdersInfo(id, get_book_id, ordered_books);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return orderInformation;
    }
}