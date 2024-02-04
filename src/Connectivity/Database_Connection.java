package src.Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Database_Connection {
    public static Connection connect() {
        String url = "jdbc:postgresql://localhost:5432/DatabaseName";
        String user = "username";
        String password = "password";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Database successfully.");
        } catch (SQLException var5) {
            System.out.println(var5.getMessage());
        }
        return con;
    }
}