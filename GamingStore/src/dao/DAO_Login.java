package dao;

import service.serviceLogn;
import connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Akbar
 */
public class DAO_Login implements serviceLogn {

    @Override
    public boolean verifyUserCredentials(String username, String password) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // User found, check if it's an admin
                        if ("admin".equals(username)) {
                            // Additional check for admin user
                            return resultSet.getBoolean("isAdmin");
                        }
                        return true; // Regular user
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return false; // Invalid credentials
    }
}
