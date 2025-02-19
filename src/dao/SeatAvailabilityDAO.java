package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.SeatAvailabilityDTO;
import model.Plane;
import model.SeatType;
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
                    COALESCE(ps.number - rs.reserved_count, ps.number) as available_seats,
                    pi.unit_price,
                    pi.discount_percentage,
                    pi.number as price_info_number
                FROM seat_type st
                         JOIN plane_seats ps ON st.seat_type_id = ps.seat_type_id
                         JOIN flight f ON f.plane_id = ps.plane_id
                         LEFT JOIN reserved_seats rs ON st.seat_type_id = rs.seat_type_id
                         LEFT JOIN price_info pi ON st.seat_type_id = pi.seat_type_id AND f.flight_id = pi.flight_id
                WHERE f.flight_id = ?
                                """;

        try (Connection conn = connectionBdd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, flightId);
            pstmt.setInt(2, flightId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    SeatType seatType = SeatTypeDAO.findById(rs.getInt("seat_type_id"));
                    Plane plane = PlaneDAO.findById(rs.getInt("plane_id"));
                    SeatAvailabilityDTO availability = new SeatAvailabilityDTO(
                            seatType,
                            plane,
                            rs.getInt("total_seats"),
                            rs.getInt("available_seats"),
                            rs.getDouble("unit_price"),
                            rs.getDouble("discount_percentage"),
                            rs.getInt("price_info_number"));
                    availabilityList.add(availability);
                }
            }
        }

        return availabilityList;
    }
}
