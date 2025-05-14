package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBdd {
    // Database URL, username, and password
    private final String URL = PropertiesLoader.getProperty("psql.url");
    private final String USER = PropertiesLoader.getProperty("psql.username");
    private final String PASSWORD = PropertiesLoader.getProperty("psql.password");

    public Connection getConnection() {
        Connection conn = null;
        try {
            // load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
        return conn;
    }
}
