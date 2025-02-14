package dao;

import model.SeatType;
import utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatTypeDAO {
    // Create
    public static void insert(SeatType seatType) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "INSERT INTO seat_type (type_name) VALUES (?)";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, seatType.getType_name());
            pstmt.executeUpdate();
        }
    }

    // Read
    public static SeatType findById(int seatTypeId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        SeatType seatType = null;
        String query = "SELECT * FROM seat_type WHERE seat_type_id = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, seatTypeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    seatType = new SeatType(
                            rs.getInt("seat_type_id"),
                            rs.getString("type_name")
                    );
                }
            }
        }
        return seatType;
    }

    // Read All
    public static List<SeatType> findAll() throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        List<SeatType> seatTypes = new ArrayList<>();
        String query = "SELECT * FROM seat_type";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                SeatType seatType = new SeatType(
                        rs.getInt("seat_type_id"),
                        rs.getString("type_name")
                );
                seatTypes.add(seatType);
            }
        }
        return seatTypes;
    }

    // Update
    public static void update(SeatType seatType) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "UPDATE seat_type SET type_name = ? WHERE seat_type_id = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, seatType.getType_name());
            pstmt.setInt(2, seatType.getSeat_type_id());
            pstmt.executeUpdate();
        }
    }

    // Delete
    public static void delete(int seatTypeId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "DELETE FROM seat_type WHERE seat_type_id = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, seatTypeId);
            pstmt.executeUpdate();
        }
    }
}