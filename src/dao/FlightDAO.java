package dao;

import model.Flight;
import model.City;
import utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {
    // Create
    public static void insert(Flight flight) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "INSERT INTO flight (departure_date, arrival_date, departure_city_id, arrival_city_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setTimestamp(1, flight.getDeparture_date());
            pstmt.setTimestamp(2, flight.getArrival_date());
            pstmt.setInt(3, flight.getDeparture_city_id());
            pstmt.setInt(4, flight.getArrival_city_id());

            pstmt.executeUpdate();
        }
    }

    // Read
    public static Flight findById(int flightId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        Flight flight = null;

        String query = "SELECT f.*, " +
                "dc.city_id as dep_city_id, dc.name as dep_city_name, " +
                "ac.city_id as arr_city_id, ac.name as arr_city_name " +
                "FROM flight f " +
                "WHERE f.flight_id = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, flightId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    flight = new Flight(
                            rs.getInt("flight_id"),
                            rs.getTimestamp("departure_date"),
                            rs.getTimestamp("arrival_date"),
                            rs.getInt("departure_city_id"),
                            rs.getInt("arrival_city_id"));
                }
            }
        }
        return flight;
    }

    // Read All
    public static List<Flight> findAll() throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        List<Flight> flights = new ArrayList<>();

        String query = "SELECT f.*, " +
                "dc.city_id as dep_city_id, dc.name as dep_city_name, " +
                "ac.city_id as arr_city_id, ac.name as arr_city_name " +
                "FROM flight f ";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Flight flight = new Flight(
                        rs.getInt("flight_id"),
                        rs.getTimestamp("departure_date"),
                        rs.getTimestamp("arrival_date"),
                        rs.getInt("departure_city_id"),
                        rs.getInt("arrival_city_id"));
                flights.add(flight);
            }
        }
        return flights;
    }

    // Update
    public static void update(Flight flight) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "UPDATE flight SET departure_date = ?, arrival_date = ?, " +
                "departure_city_id = ?, arrival_city_id = ? WHERE flight_id = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setTimestamp(1, flight.getDeparture_date());
            pstmt.setTimestamp(2, flight.getArrival_date());
            pstmt.setInt(3, flight.getDeparture_city_id());
            pstmt.setInt(4, flight.getArrival_city_id());
            pstmt.setInt(5, flight.getFlight_id());

            pstmt.executeUpdate();
        }
    }

    // Delete
    public static void delete(int flightId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "DELETE FROM flight WHERE flight_id = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, flightId);
            pstmt.executeUpdate();
        }
    }
}