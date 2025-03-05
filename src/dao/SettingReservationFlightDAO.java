package dao;

import model.SettingReservationFlight;
import utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SettingReservationFlightDAO {
    // Create
    public static void insert(SettingReservationFlight settingReservationFlight) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "INSERT INTO setting_reservation_flight (flight_id, reservation, cancelation) VALUES (?, ?, ?)";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, settingReservationFlight.getFlight_id());
            pstmt.setDouble(2, settingReservationFlight.getReservation());
            pstmt.setDouble(3, settingReservationFlight.getCancelation());
            pstmt.executeUpdate();
        }
    }

    // Read
    public static SettingReservationFlight findById(int settingReservationFlightId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        SettingReservationFlight settingReservationFlight = null;
        String query = "SELECT * FROM setting_reservation_flight WHERE setting_reservation_flight_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, settingReservationFlightId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    settingReservationFlight = new SettingReservationFlight(
                            rs.getInt("setting_reservation_flight_id"),
                            rs.getInt("flight_id"),
                            rs.getDouble("reservation"),
                            rs.getDouble("cancelation"));
                }
            }
        }
        return settingReservationFlight;
    }

    // Read All
    public static List<SettingReservationFlight> findAll() throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        List<SettingReservationFlight> settings = new ArrayList<>();
        String query = "SELECT * FROM setting_reservation_flight";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                SettingReservationFlight settingReservationFlight = new SettingReservationFlight(
                        rs.getInt("setting_reservation_flight_id"),
                        rs.getInt("flight_id"),
                        rs.getDouble("reservation"),
                        rs.getDouble("cancelation"));
                settings.add(settingReservationFlight);
            }
        }
        return settings;
    }

    // Update
    public static void update(SettingReservationFlight settingReservationFlight) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "UPDATE setting_reservation_flight SET flight_id = ?, reservation = ?, cancelation = ? WHERE setting_reservation_flight_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, settingReservationFlight.getFlight_id());
            pstmt.setDouble(2, settingReservationFlight.getReservation());
            pstmt.setDouble(3, settingReservationFlight.getCancelation());
            pstmt.setInt(4, settingReservationFlight.getSetting_reservation_flight_id());
            pstmt.executeUpdate();
        }
    }

    // Delete
    public static void delete(int settingReservationFlightId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "DELETE FROM setting_reservation_flight WHERE setting_reservation_flight_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, settingReservationFlightId);
            pstmt.executeUpdate();
        }
    }

    // Find by Flight ID
    public static SettingReservationFlight findByFlightId(int flightId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        SettingReservationFlight settingReservationFlight = null;
        String query = "SELECT * FROM setting_reservation_flight WHERE flight_id = ?";

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, flightId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    settingReservationFlight = new SettingReservationFlight(
                            rs.getInt("setting_reservation_flight_id"),
                            rs.getInt("flight_id"),
                            rs.getDouble("reservation"),
                            rs.getDouble("cancelation"));
                }
            }
        }
        return settingReservationFlight;
    }
}