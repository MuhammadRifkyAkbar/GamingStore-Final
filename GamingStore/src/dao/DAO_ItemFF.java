package dao;

import Model.itemFF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_ItemFF {
    private Connection connection;

    public DAO_ItemFF(Connection connection) {
        this.connection = connection;
    }

    public boolean addItem(itemFF newItem) {
        try {
            String query = "INSERT INTO itemff (IDbarang, NicknameFF, SenjataFF, VaultFF, characFF, Harga) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, newItem.getIDbarang());
                preparedStatement.setString(2, newItem.getNicknameFF());
                preparedStatement.setString(3, newItem.getSenjataFF());
                preparedStatement.setInt(4, newItem.getVaultFF());
                preparedStatement.setInt(5, newItem.getCharacFF());
                preparedStatement.setLong(6, newItem.getHarga());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            // Log or throw an exception
            e.printStackTrace();
            return false;
        }
    }

    public List<itemFF> getAllItems() {
        List<itemFF> items = new ArrayList<>();
        String query = "SELECT * FROM itemff";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                itemFF retrievedItem = new itemFF();
                retrievedItem.setIDbarang(resultSet.getLong("IDbarang"));
                retrievedItem.setNicknameFF(resultSet.getString("NicknameFF"));
                retrievedItem.setSenjataFF(resultSet.getString("SenjataFF"));
                retrievedItem.setVaultFF(resultSet.getInt("VaultFF"));
                retrievedItem.setCharacFF(resultSet.getInt("characFF"));
                retrievedItem.setHarga(resultSet.getLong("Harga"));
                items.add(retrievedItem);
            }
        } catch (SQLException e) {
            // Log or throw an exception
            e.printStackTrace();
        }

        return items;
    }

    public itemFF getItemByID(Long itemID) {
        try {
            String query = "SELECT * FROM itemff WHERE IDbarang = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, itemID);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        itemFF retrievedItem = new itemFF();
                        retrievedItem.setIDbarang(resultSet.getLong("IDbarang"));
                        retrievedItem.setNicknameFF(resultSet.getString("NicknameFF"));
                        retrievedItem.setSenjataFF(resultSet.getString("SenjataFF"));
                        retrievedItem.setVaultFF(resultSet.getInt("VaultFF"));
                        retrievedItem.setCharacFF(resultSet.getInt("characFF"));
                        retrievedItem.setHarga(resultSet.getLong("Harga"));
                        return retrievedItem;
                    }
                }
            }
        } catch (SQLException e) {
            // Log or throw an exception
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateItem(itemFF updatedItem) {
        try {
            String query = "UPDATE itemff SET NicknameFF=?, SenjataFF=?, VaultFF=?, characFF=?, Harga=? WHERE IDbarang=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, updatedItem.getNicknameFF());
                preparedStatement.setString(2, updatedItem.getSenjataFF());
                preparedStatement.setInt(3, updatedItem.getVaultFF());
                preparedStatement.setInt(4, updatedItem.getCharacFF());
                preparedStatement.setLong(5, updatedItem.getHarga());
                preparedStatement.setLong(6, updatedItem.getIDbarang());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            // Log or throw an exception
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteItemByNickname(String nickname) {
        try {
            String query = "DELETE FROM itemff WHERE NicknameFF=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nickname);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            // Log or throw an exception
            e.printStackTrace();
            return false;
        }
    }
}
