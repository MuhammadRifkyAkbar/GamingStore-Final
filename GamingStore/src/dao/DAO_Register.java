package dao;

import Model.profile;
import java.sql.Connection; // Correct import statement
import java.sql.PreparedStatement;
import java.sql.SQLException;
import connection.DBConnection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import service.servRegist;

public class DAO_Register implements servRegist {

    // ... (existing methods)
    @Override
    public boolean registerUser(String username, String password, String email) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public profile getUserByUsername(String username) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        profile retrievedProfile = new profile();
                        retrievedProfile.setUsernameUser(resultSet.getString("username"));
                        retrievedProfile.setPassword(resultSet.getString("password"));
                        retrievedProfile.setEmail(resultSet.getString("email"));
                        return retrievedProfile;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(profile updatedProfile) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "UPDATE users SET password=?, email=? WHERE username=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, updatedProfile.getPassword());
                preparedStatement.setString(2, updatedProfile.getEmail());
                preparedStatement.setString(3, updatedProfile.getUsernameUser());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<profile> getAllUsers() {
        List<profile> userList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM users";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    profile retrievedProfile = new profile();
                    retrievedProfile.setUsernameUser(resultSet.getString("username"));
                    retrievedProfile.setPassword(resultSet.getString("password"));
                    retrievedProfile.setEmail(resultSet.getString("email"));
                    userList.add(retrievedProfile);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
