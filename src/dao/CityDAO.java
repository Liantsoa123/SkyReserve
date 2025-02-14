package dao;

import model.City;
import utils.ConnectionBdd;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
    // Create
    public static void insert(City city) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "INSERT INTO city (city_name, country) VALUES (?,?)";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, city.getCity_name());
            pstmt.setString(2, city.getCountry());
            pstmt.executeUpdate();
        }
    }

    // Read
    public static City findById(int cityId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        City city = null;
        String query = "SELECT * FROM city WHERE city_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, cityId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    city = new City(
                            rs.getInt("city_id"),
                            rs.getString("city_name"),
                            rs.getString("country"));
                }
            }
        }
        return city;
    }

    // Read All
    public static List<City> findAll() throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        List<City> cities = new ArrayList<>();
        String query = "SELECT * FROM city";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                City city = new City(
                        rs.getInt("city_id"),
                        rs.getString("city_name"),
                        rs.getString("country"));
                cities.add(city);
            }
        }
        return cities;
    }

    // Update
    public static void update(City city) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "UPDATE city SET name = ?, country = ?  WHERE city_id = ? ";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, city.getCity_name());
            pstmt.setString(2, city.getCountry());
            pstmt.setInt(3, city.getCity_id());
            pstmt.executeUpdate();
        }
    }

    // Delete
    public static void delete(int cityId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "DELETE FROM city WHERE city_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, cityId);
            pstmt.executeUpdate();
        }
    }
}