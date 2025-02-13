package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBdd {
    // Database URL, username, and password
    private final String URL = "jdbc:postgresql://localhost:5432/sky_reserve";
    private final String USER = "postgres";
    private final String PASSWORD = "liantsoa";

    public Connection getConnection() {
        Connection conn = null;
        try {
            // load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
        return conn;
    }
}
