package dao;

import Model.ItemGame;
import connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_ItemGame {
    private Connection connection;

    public DAO_ItemGame() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider throwing a custom exception or rethrowing a more specific exception
        }
    }

    public boolean addItemGame(ItemGame newItemGame) {
        try {
            String query = "INSERT INTO item_game (IDGame, nama_game, harga_game) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, newItemGame.getIDGame());
                preparedStatement.setString(2, newItemGame.getNama_game());
                preparedStatement.setLong(3, newItemGame.getHarga_game());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ItemGame> getAllItemGames() {
        List<ItemGame> itemGames = new ArrayList<>();
        String query = "SELECT * FROM item_game";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ItemGame retrievedItemGame = new ItemGame();
                retrievedItemGame.setIDGame(resultSet.getLong("IDGame"));
                retrievedItemGame.setNama_game(resultSet.getString("nama_game"));
                retrievedItemGame.setHarga_game(resultSet.getLong("harga_game"));
                itemGames.add(retrievedItemGame);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemGames;
    }

    public ItemGame getItemGameByID(long itemGameID) {
        try {
            String query = "SELECT * FROM item_game WHERE IDGame = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, itemGameID);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        ItemGame retrievedItemGame = new ItemGame();
                        retrievedItemGame.setIDGame(resultSet.getLong("IDGame"));
                        retrievedItemGame.setNama_game(resultSet.getString("nama_game"));
                        retrievedItemGame.setHarga_game(resultSet.getLong("harga_game"));
                        return retrievedItemGame;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateItemGame(ItemGame updatedItemGame) {
        try {
            String query = "UPDATE item_game SET nama_game=?, harga_game=? WHERE IDGame=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, updatedItemGame.getNama_game());
                preparedStatement.setLong(2, updatedItemGame.getHarga_game());
                preparedStatement.setLong(3, updatedItemGame.getIDGame());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteItemGameByID(long itemGameID) {
        try {
            String query = "DELETE FROM item_game WHERE IDGame=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, itemGameID);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
