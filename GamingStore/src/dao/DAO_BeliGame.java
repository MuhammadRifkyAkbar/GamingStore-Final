package dao;

import Model.BeliGame;
import connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import service.BeliGameService;

public class DAO_BeliGame implements BeliGameService {
    private Connection connection;

    public DAO_BeliGame() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider throwing a custom exception or rethrowing a more specific exception
        }
    }

    @Override
    public boolean addBeliGame(BeliGame newBeliGame) {
        try {
            String query = "INSERT INTO beligame (NamaGame, harga, ppn, income, email) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newBeliGame.getNamaGame());
                preparedStatement.setString(2, newBeliGame.getHarga());
                preparedStatement.setString(3, newBeliGame.getPpn());
                preparedStatement.setString(4, newBeliGame.getIncome());
                preparedStatement.setString(5, newBeliGame.getEmail());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<BeliGame> getAllBeliGames() {
        List<BeliGame> beliGames = new ArrayList<>();
        String query = "SELECT * FROM beligame";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                BeliGame retrievedBeliGame = new BeliGame(
                        resultSet.getString("NamaGame"),
                        resultSet.getString("harga"),
                        resultSet.getString("ppn"),
                        resultSet.getString("income"),
                        resultSet.getString("email")
                );
                beliGames.add(retrievedBeliGame);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beliGames;
    }

    @Override
    public BeliGame getBeliGameByEmail(String email) {
        try {
            String query = "SELECT * FROM beligame WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new BeliGame(
                                resultSet.getString("NamaGame"),
                                resultSet.getString("harga"),
                                resultSet.getString("ppn"),
                                resultSet.getString("income"),
                                resultSet.getString("email")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateBeliGame(BeliGame updatedBeliGame) {
        try {
            String query = "UPDATE beligame SET NamaGame=?, harga=?, ppn=?, income=? WHERE email=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, updatedBeliGame.getNamaGame());
                preparedStatement.setString(2, updatedBeliGame.getHarga());
                preparedStatement.setString(3, updatedBeliGame.getPpn());
                preparedStatement.setString(4, updatedBeliGame.getIncome());
                preparedStatement.setString(5, updatedBeliGame.getEmail());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBeliGameByEmail(String email) {
        try {
            String query = "DELETE FROM beligame WHERE email=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
