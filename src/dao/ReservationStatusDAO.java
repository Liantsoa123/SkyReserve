package dao;

import model.ReservationStatus;
import utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationStatusDAO {

    // Create
    public static void insert(ReservationStatus reservationStatus) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "INSERT INTO reservation_status (reservation_name) VALUES (?)";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, reservationStatus.getReservation_name());
            pstmt.executeUpdate();
        }
    }

    // Read
    public static ReservationStatus findById(int statusId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        ReservationStatus status = null;
        String query = "SELECT * FROM reservation_status WHERE reservation_status_id = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, statusId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    status = new ReservationStatus(
                            rs.getInt("reservation_status_id"),
                            rs.getString("reservation_name"));
                }
            }
        }
        return status;
    }

    // Read All
    public static List<ReservationStatus> findAll() throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        List<ReservationStatus> statuses = new ArrayList<>();
        String query = "SELECT * FROM reservation_status";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ReservationStatus status = new ReservationStatus(
                        rs.getInt("reservation_status_id"),
                        rs.getString("reservation_name"));
                statuses.add(status);
            }
        }
        return statuses;
    }

    // Update
    public static void update(ReservationStatus reservationStatus) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "UPDATE reservation_status SET reservation_name = ? WHERE reservation_status_id = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, reservationStatus.getReservation_name());
            pstmt.setInt(2, reservationStatus.getReservation_status_id());
            pstmt.executeUpdate();
        }
    }

    // Delete
    public static void delete(int statusId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "DELETE FROM reservation_status WHERE reservation_status_id = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, statusId);
            pstmt.executeUpdate();
        }
    }

    // Find by Name
    public static ReservationStatus findByName(String name) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        ReservationStatus status = null;
        String query = "SELECT * FROM reservation_status WHERE reservation_name = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    status = new ReservationStatus(
                            rs.getInt("reservation_status_id"),
                            rs.getString("reservation_name"));
                }
            }
        }
        return status;
    }
}