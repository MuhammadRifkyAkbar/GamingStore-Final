/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.itemPUBG;
import connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_ItemPUBG {
    private Connection connection;

    public DAO_ItemPUBG() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            // Log or throw an exception
            e.printStackTrace();
        }
    }

    public boolean addItem(itemPUBG newItem) {
        try {
            String query = "INSERT INTO itempubg (IDbar, Nickname, Senjata, Vault, charac, Harga) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, newItem.getIDbar());
                preparedStatement.setString(2, newItem.getNickname());
                preparedStatement.setInt(3, newItem.getSenjata());
                preparedStatement.setInt(4, newItem.getVault());
                preparedStatement.setString(5, newItem.getCharac());
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

    public List<itemPUBG> getAllItems() {
        List<itemPUBG> items = new ArrayList<>();
        String query = "SELECT * FROM itempubg";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                itemPUBG retrievedItem = new itemPUBG();
                retrievedItem.setIDbar(resultSet.getLong("IDbar"));
                retrievedItem.setNickname(resultSet.getString("Nickname"));
                retrievedItem.setSenjata(resultSet.getInt("Senjata"));
                retrievedItem.setVault(resultSet.getInt("Vault"));
                retrievedItem.setCharac(resultSet.getString("charac"));
                retrievedItem.setHarga(resultSet.getLong("Harga"));
                items.add(retrievedItem);
            }
        } catch (SQLException e) {
            // Log or throw an exception
            e.printStackTrace();
        }

        return items;
    }

    public itemPUBG getItemByID(Long itemID) {
        try {
            String query = "SELECT * FROM itempubg WHERE IDbar = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, itemID);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        itemPUBG retrievedItem = new itemPUBG();
                        retrievedItem.setIDbar(resultSet.getLong("IDbar"));
                        retrievedItem.setNickname(resultSet.getString("Nickname"));
                        retrievedItem.setSenjata(resultSet.getInt("Senjata"));
                        retrievedItem.setVault(resultSet.getInt("Vault"));
                        retrievedItem.setCharac(resultSet.getString("charac"));
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

    public boolean updateItem(itemPUBG updatedItem) {
        try {
            String query = "UPDATE itempubg SET Nickname=?, Senjata=?, Vault=?, charac=?, Harga=? WHERE IDbar=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, updatedItem.getNickname());
                preparedStatement.setInt(2, updatedItem.getSenjata());
                preparedStatement.setInt(3, updatedItem.getVault());
                preparedStatement.setString(4, updatedItem.getCharac());
                preparedStatement.setLong(5, updatedItem.getHarga());
                preparedStatement.setLong(6, updatedItem.getIDbar());

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
            String query = "DELETE FROM itempubg WHERE Nickname=?";
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
