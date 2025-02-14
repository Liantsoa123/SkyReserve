package dao;

import model.Plane;
import utils.ConnectionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaneDAO {
    // Create
    public static void insert(Plane plane) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "INSERT INTO plane (model, manufacture_date) VALUES (?, ?)";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, plane.getModel());
            pstmt.setDate(2, plane.getManufacture_date());
            pstmt.executeUpdate();
        }
    }

    // Read
    public static Plane findById(int planeId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        Plane plane = null;

        String query = "SELECT * FROM plane WHERE plane_id = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, planeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    plane = new Plane(
                            rs.getInt("plane_id"),
                            rs.getString("model"),
                            rs.getDate("manufacture_date")
                    );
                }
            }
        }
        return plane;
    }

    // Read All
    public static List<Plane> findAll() throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        List<Plane> planes = new ArrayList<>();

        String query = "SELECT * FROM plane";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Plane plane = new Plane(
                        rs.getInt("plane_id"),
                        rs.getString("model"),
                        rs.getDate("manufacture_date")
                );
                planes.add(plane);
            }
        }
        return planes;
    }

    // Update
    public static void update(Plane plane) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "UPDATE plane SET model = ?, manufacture_date = ? WHERE plane_id = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, plane.getModel());
            pstmt.setDate(2, plane.getManufacture_date());
            pstmt.setInt(3, plane.getPlane_id());
            pstmt.executeUpdate();
        }
    }

    // Delete
    public static void delete(int planeId) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        String query = "DELETE FROM plane WHERE plane_id = ?";

        try (Connection conn = connectionBdd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, planeId);
            pstmt.executeUpdate();
        }
    }
}