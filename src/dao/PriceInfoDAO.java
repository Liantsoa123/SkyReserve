package dao;

import model.PriceInfo;
import model.Flight;
import model.SeatType;
import utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PriceInfoDAO {
    // Create
    public static void insert(PriceInfo priceInfo) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "INSERT INTO price_info (seat_type_id, flight_id, unit_price, discount_percentage, number) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, priceInfo.getSeat_type_id());
            pstmt.setInt(2, priceInfo.getFlight_id());
            pstmt.setDouble(3, priceInfo.getUnit_price());
            pstmt.setDouble(4, priceInfo.getDiscount_percentage());
            pstmt.setInt(5, priceInfo.getNumber());
            pstmt.executeUpdate();
        }
    }

    // Read
    public static PriceInfo findById(int seatTypeId, int flightId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        PriceInfo priceInfo = null;
        String query = "SELECT * FROM price_info WHERE seat_type_id = ? AND flight_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, seatTypeId);
            pstmt.setInt(2, flightId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    priceInfo = new PriceInfo(
                            rs.getInt("seat_type_id"),
                            rs.getInt("flight_id"),
                            rs.getDouble("unit_price"),
                            rs.getDouble("discount_percentage"),
                            rs.getInt("number"));
                }
            }
        }
        return priceInfo;
    }

    // Read All
    public static List<PriceInfo> findAll() throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        List<PriceInfo> priceInfos = new ArrayList<>();
        String query = "SELECT * FROM price_info";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                PriceInfo priceInfo = new PriceInfo(
                        rs.getInt("seat_type_id"),
                        rs.getInt("flight_id"),
                        rs.getDouble("unit_price"),
                        rs.getDouble("discount_percentage"),
                        rs.getInt("number"));
                priceInfos.add(priceInfo);
            }
        }
        return priceInfos;
    }

    // Update
    public static void update(PriceInfo priceInfo) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "UPDATE price_info SET unit_price = ?, discount_percentage = ?, number = ? WHERE seat_type_id = ? AND flight_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setDouble(1, priceInfo.getUnit_price());
            pstmt.setDouble(2, priceInfo.getDiscount_percentage());
            pstmt.setInt(3, priceInfo.getNumber());
            pstmt.setInt(4, priceInfo.getSeat_type_id());
            pstmt.setInt(5, priceInfo.getFlight_id());
            pstmt.executeUpdate();
        }
    }

    // Delete
    public static void delete(int seatTypeId, int flightId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "DELETE FROM price_info WHERE seat_type_id = ? AND flight_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, seatTypeId);
            pstmt.setInt(2, flightId);
            pstmt.executeUpdate();
        }
    }

    // Read by flight_id
    public static List<PriceInfo> findByFlightId(int flightId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        List<PriceInfo> priceInfos = new ArrayList<>();
        String query = "SELECT * FROM price_info WHERE flight_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, flightId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PriceInfo priceInfo = new PriceInfo(
                            rs.getInt("seat_type_id"),
                            rs.getInt("flight_id"),
                            rs.getDouble("unit_price"),
                            rs.getDouble("discount_percentage"),
                            rs.getInt("number"));
                    priceInfos.add(priceInfo);
                }
            }
        }
        return priceInfos;
    }
}