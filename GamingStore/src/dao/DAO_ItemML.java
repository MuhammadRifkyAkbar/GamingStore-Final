/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.itemML;
import connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import service.servBarangML;

public class DAO_ItemML implements servBarangML {

    @Override
    public void addItemML(itemML ItemML) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO itemML (NicknameML, IDbarang, skin, Hero, LoginAkun, Harga) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, ItemML.getNicknameML());
                preparedStatement.setLong(2, ItemML.getIDbarang());
                preparedStatement.setInt(3, ItemML.getSkin());
                preparedStatement.setInt(4, ItemML.getHero());
                preparedStatement.setString(5, ItemML.getLoginAkun());
                preparedStatement.setLong(6, ItemML.getHarga());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    @Override
    public void updateItemML(itemML ItemML) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "UPDATE itemML SET NicknameML=?, skin=?, Hero=?, LoginAkun=?, Harga=? WHERE IDbarang=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, ItemML.getNicknameML());
                preparedStatement.setInt(2, ItemML.getSkin());
                preparedStatement.setInt(3, ItemML.getHero());
                preparedStatement.setString(4, ItemML.getLoginAkun());
                preparedStatement.setLong(5, ItemML.getHarga());
                preparedStatement.setLong(6, ItemML.getIDbarang());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    @Override
    public void deleteItemML(Long id) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "DELETE FROM itemML WHERE IDbarang=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public itemML getItemMLById(Long id) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM itemML WHERE IDbarang=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        itemML ItemML = new itemML();
                        // Set properties of ItemML from the resultSet
                        // Example: ItemML.setNama_game(resultSet.getString("nama_game"));
                        // Continue for other properties
                        return ItemML;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return null;
    }

    @Override
    public List<itemML> getAllItemMLs() {
        List<itemML> itemList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM itemML";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    itemML ItemML = new itemML();
                    ItemML.setIDbarang(resultSet.getLong("IDBarang")); // Corrected variable name
                    ItemML.setHarga(resultSet.getLong("Harga"));
                    ItemML.setNicknameML(resultSet.getString("NicknameML"));
                    ItemML.setSkin(resultSet.getInt("skin"));
                    ItemML.setHero(resultSet.getInt("Hero"));
                    ItemML.setLoginAkun(resultSet.getString("LoginAkun"));
                    // Continue for other properties

                    itemList.add(ItemML);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return itemList;
    }

    public void deleteItemByNickname(String nickname) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "DELETE FROM itemML WHERE NicknameML=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nickname);
                preparedStatement.executeUpdate();  
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

}
