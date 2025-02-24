package dao;

import model.Reservation;
import utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    // Create
    public static void insert(Reservation reservation) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "INSERT INTO reservation (reservation_date, seats_number, has_promotion, " +
                "reservation_status_id, seat_type_id, flight_id, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setTimestamp(1, reservation.getReservation_date());
            pstmt.setInt(2, reservation.getSeats_number());
            pstmt.setBoolean(3, reservation.isHas_promotion());
            pstmt.setInt(4, reservation.getReservation_status_id());
            pstmt.setInt(5, reservation.getSeat_type_id());
            pstmt.setInt(6, reservation.getFlight_id());
            pstmt.setInt(7, reservation.getUser_id());
            pstmt.executeUpdate();
        }
    }

    // Read
    public static Reservation findById(int reservationId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        Reservation reservation = null;
        String query = "SELECT * FROM reservation WHERE reservation_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, reservationId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    reservation = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getTimestamp("reservation_date"),
                            rs.getInt("seats_number"),
                            rs.getBoolean("has_promotion"),
                            rs.getInt("reservation_status_id"),
                            rs.getInt("seat_type_id"),
                            rs.getInt("flight_id"),
                            rs.getInt("user_id"));
                }
            }
        }
        return reservation;
    }

    // Read All
    public static List<Reservation> findAll() throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getTimestamp("reservation_date"),
                        rs.getInt("seats_number"),
                        rs.getBoolean("has_promotion"),
                        rs.getInt("reservation_status_id"),
                        rs.getInt("seat_type_id"),
                        rs.getInt("flight_id"),
                        rs.getInt("user_id"));
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    // Update
    public static void update(Reservation reservation) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "UPDATE reservation SET reservation_date = ?, seats_number = ?, " +
                "has_promotion = ?, reservation_status_id = ?, seat_type_id = ?, " +
                "flight_id = ?, user_id = ? WHERE reservation_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setTimestamp(1, reservation.getReservation_date());
            pstmt.setInt(2, reservation.getSeats_number());
            pstmt.setBoolean(3, reservation.isHas_promotion());
            pstmt.setInt(4, reservation.getReservation_status_id());
            pstmt.setInt(5, reservation.getSeat_type_id());
            pstmt.setInt(6, reservation.getFlight_id());
            pstmt.setInt(7, reservation.getUser_id());
            pstmt.setInt(8, reservation.getReservation_id());
            pstmt.executeUpdate();
        }
    }

    // Delete
    public static void delete(int reservationId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "DELETE FROM reservation WHERE reservation_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, reservationId);
            pstmt.executeUpdate();
        }
    }
}