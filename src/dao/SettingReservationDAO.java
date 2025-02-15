package dao;

import model.SettingReservation;
import utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SettingReservationDAO {
    // Create
    public static void insert(SettingReservation settingReservation) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "INSERT INTO setting_reservation (reservation, cancelation) VALUES (?, ?)";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setDouble(1, settingReservation.getReservation());
            pstmt.setDouble(2, settingReservation.getCancelation());
            pstmt.executeUpdate();
        }
    }

    // Read All
    public static List<SettingReservation> findAll() throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        List<SettingReservation> settings = new ArrayList<>();
        String query = "SELECT * FROM setting_reservation";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                SettingReservation settingReservation = new SettingReservation(
                        rs.getInt("setting_reservation_id"),
                        rs.getDouble("reservation"),
                        rs.getDouble("cancelation")
                );
                settings.add(settingReservation);
            }
        }
        return settings;
    }

    // Update
    public static void update(SettingReservation settingReservation) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "UPDATE setting_reservation SET reservation = ?, cancelation = ? WHERE setting_reservation_id = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setDouble(1, settingReservation.getReservation());
            pstmt.setDouble(2, settingReservation.getCancelation());
            pstmt.setInt(3, settingReservation.getSetting_reservation_id());
            pstmt.executeUpdate();
        }
    }

    // Delete
    public static void delete(int settingReservationId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "DELETE FROM setting_reservation WHERE setting_reservation_id = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, settingReservationId);
            pstmt.executeUpdate();
        }
    }
}