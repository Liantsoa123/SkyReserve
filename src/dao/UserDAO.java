package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utils.ConnectionBdd;

public class UserDAO {
    public static User login(String username, String password) throws SQLException {
        ConnectionBdd connectionBdd = new ConnectionBdd();
        User user = null;

        try (Connection conn = connectionBdd.getConnection()) {
            String query = "SELECT user_id, name, role, password FROM _user_ WHERE name = ? AND password = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        user = new User(
                                rs.getInt("user_id"),
                                rs.getString("name"),
                                rs.getString("password"),
                                rs.getString("role"));
                    }
                }
            }
        }

        return user;
    }
}
