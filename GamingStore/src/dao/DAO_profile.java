package dao;

import Model.profile;
import service.ProfilService;
import connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_profile implements ProfilService {

    @Override
    public boolean addProfile(profile newProfile) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO users (username, password, email) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, newProfile.getUsernameUser());
            preparedStatement.setString(2, newProfile.getPassword());
            preparedStatement.setString(3, newProfile.getEmail());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Log the exception or throw a custom exception
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public profile getProfileByUsername(String username) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM users WHERE username = ?")) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    profile foundProfile = new profile();
                    foundProfile.setUsernameUser(resultSet.getString("username"));
                    foundProfile.setPassword(resultSet.getString("password"));
                    foundProfile.setEmail(resultSet.getString("email"));
                    return foundProfile;
                }
            }

        } catch (SQLException e) {
            // Log the exception or throw a custom exception
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<profile> getAllProfiles() {
        List<profile> profiles = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM users");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                profile retrievedProfile = new profile();
                retrievedProfile.setUsernameUser(resultSet.getString("username"));
                retrievedProfile.setPassword(resultSet.getString("password"));
                retrievedProfile.setEmail(resultSet.getString("email"));
                profiles.add(retrievedProfile);
            }

        } catch (SQLException e) {
            // Log the exception or throw a custom exception
            e.printStackTrace();
        }
        return profiles;
    }

    @Override
    public boolean updateProfile(profile updatedProfile) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE users SET password = ?, email = ? WHERE username = ?")) {

            preparedStatement.setString(1, updatedProfile.getPassword());
            preparedStatement.setString(2, updatedProfile.getEmail());
            preparedStatement.setString(3, updatedProfile.getUsernameUser());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Log the exception or throw a custom exception
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProfileByUsername(String username) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM users WHERE username = ?")) {

            preparedStatement.setString(1, username);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Log the exception or throw a custom exception
            e.printStackTrace();
            return false;
        }
    }
}
