package src.Methods;

import src.Connectivity.Database_Connection;
import src.Entity.Books;
import src.Entity.BooksInfo;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BooksInfo_Method extends Database_Connection {
    public static boolean addBooksInformation(BooksInfo BooksInformation) {
        try (Connection connection = connect();) {
            PreparedStatement st = connection.prepareStatement("INSERT INTO booksInfo (book_id,author_id) VALUES (?,?)");
            st.setInt(1, BooksInformation.getBook_id());
            st.setInt(2, BooksInformation.getAuthor_id());
            System.out.println("Inserting Book Information");
            st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        System.out.println("Book Information inserted successfully");
        return true;
    }

    public static List<BooksInfo> getAllBooksInformation() {
        List<BooksInfo> BooksInformation = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM booksInfo");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int book_id = res.getInt("book_id");
                int author_id = res.getInt("author_id");
                System.out.println("book_id = " + book_id + ", author_id = " + author_id);
                BooksInformation.add(new BooksInfo(book_id, author_id));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return BooksInformation;
    }

    public static boolean updateBooksInformation(BooksInfo BooksInformation) {
        try (Connection connection = connect()) {
            PreparedStatement st = connection.prepareStatement("UPDATE booksInfo SET author_id=? WHERE book_id=?");
            st.setInt(1, BooksInformation.getAuthor_id());
            st.setInt(2, BooksInformation.getBook_id());
            System.out.println("Updated successfully");
            st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean deleteBooksInformation(int book_id, int author_id) {
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("DELETE FROM booksInfo WHERE book_id = " + book_id + " AND author_id = " + author_id);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        System.out.println("Successfully Deleted Book Information");
        return true;
    }

    public static List<BooksInfo> getAllBooksInformationById(int book_id) {
        List<BooksInfo> booksInformation = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM booksInfo WHERE book_id = " + book_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int id = res.getInt("book_id");
                int id2 = res.getInt("author_id");
                System.out.println("book_id = " + book_id + ", author_id = " + id2);
                booksInformation.add(new BooksInfo(id, id2));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return booksInformation;
    }

    public static BooksInfo getBooksInformationById(int book_id, int author_id) {
        BooksInfo booksInformation = null;
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM booksInfo WHERE book_id = " + book_id + " AND author_id = " + author_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int id = res.getInt("book_id");
                int id2 = res.getInt("author_id");
                System.out.println("book_id = " + book_id + ", author_id = " + id2);
                booksInformation = new BooksInfo(id, id2);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return booksInformation;
    }
}