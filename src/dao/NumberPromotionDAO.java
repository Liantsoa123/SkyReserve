package dao;

import model.NumberPromotion;
import model.Flight;
import utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NumberPromotionDAO {
    // Create
    public static void insert(NumberPromotion numberPromotion) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "INSERT INTO number_promotion (number, flight_id) VALUES (?, ?)";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, numberPromotion.getNumber());
            pstmt.setInt(2, numberPromotion.getFlight().getFlight_id());
            pstmt.executeUpdate();
        }
    }

    // Read
    public static NumberPromotion findByIdFlight(int flightId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        NumberPromotion numberPromotion = null;
        String query = "SELECT * FROM number_promotion WHERE flight_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, flightId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Flight flight = FlightDAO.findById(rs.getInt("flight_id"));
                    numberPromotion = new NumberPromotion(
                            rs.getInt("number_promotion_id"),
                            rs.getInt("number"),
                            flight);
                }
            }
        }
        return numberPromotion;
    }

    // Update
    public static void update(NumberPromotion numberPromotion) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "UPDATE number_promotion SET number = ? WHERE flight_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, numberPromotion.getNumber());
            pstmt.setInt(2, numberPromotion.getFlight().getFlight_id());
            pstmt.executeUpdate();
        }
    }

    // Delete
    public static void delete(int numberPromotionId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "DELETE FROM number_promotion WHERE number_promotion_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, numberPromotionId);
            pstmt.executeUpdate();
        }
    }
}