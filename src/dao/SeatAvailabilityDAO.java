package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.SeatAvailabilityDTO;
import utils.ConnectionBdd;

public class SeatAvailabilityDAO {
    public static List<SeatAvailabilityDTO> getAvailableSeats(int flightId) throws SQLException {
        List<SeatAvailabilityDTO> availabilityList = new ArrayList<>();
        ConnectionBdd connectionBdd = new ConnectionBdd();

        String query = """
                    WITH reserved_seats AS (
                        SELECT
                            seat_type_id,
                            SUM(seats_number) as reserved_count
                        FROM reservation
                        WHERE flight_id = ?
                            AND resrvation_status_id != (SELECT resrvation_status_id FROM reservation_status WHERE reseravtion_name = 'Cancelled')
                        GROUP BY seat_type_id
                    )
                    SELECT
                        st.seat_type_id,
                        st.type_name,
                        ps.plane_id,
                        ps.number as total_seats,
                        COALESCE(ps.number - rs.reserved_count, ps.number) as available_seats
                    FROM seat_type st
                    JOIN plane_seats ps ON st.seat_type_id = ps.seat_type_id
                    JOIN flight f ON f.plane_id = ps.plane_id
                    LEFT JOIN reserved_seats rs ON st.seat_type_id = rs.seat_type_id
                    WHERE f.flight_id = ?
                """;

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, flightId);
            pstmt.setInt(2, flightId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    SeatAvailabilityDTO availability = new SeatAvailabilityDTO(
                            rs.getInt("seat_type_id"),
                            rs.getInt("plane_id"),
                            rs.getInt("total_seats"),
                            rs.getInt("available_seats"));
                    availabilityList.add(availability);
                }
            }
        }

        return availabilityList;
    }
}
