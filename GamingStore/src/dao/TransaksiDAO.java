/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.Transaksi;
import connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {
    // Menambah transaksi baru
    public void tambahTransaksi(Transaksi transaksi) {
        String query = "INSERT INTO transaksi (nicknameML, idItem, jumlahItem, harga, tanggalTransaksi) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, transaksi.getNicknameML());
            preparedStatement.setLong(2, transaksi.getIdItem());
            preparedStatement.setInt(3, transaksi.getJumlahItem());
            preparedStatement.setLong(4, transaksi.getHarga());
            preparedStatement.setTimestamp(5, new Timestamp(transaksi.getTanggalTransaksi().getTime()));

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    transaksi.setIdTransaksi(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menghapus transaksi berdasarkan ID transaksi
    public void hapusTransaksi(Long idTransaksi) {
        String query = "DELETE FROM transaksi WHERE idTransaksi = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, idTransaksi);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mendapatkan semua transaksi
    public List<Transaksi> semuaTransaksi() {
        List<Transaksi> transaksiList = new ArrayList<>();
        String query = "SELECT * FROM transaksi";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Transaksi transaksi = extractTransaksiFromResultSet(resultSet);
                transaksiList.add(transaksi);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transaksiList;
    }

    // Menemukan transaksi berdasarkan ID transaksi
    public Transaksi temukanTransaksi(Long idTransaksi) {
        Transaksi transaksi = null;
        String query = "SELECT * FROM transaksi WHERE idTransaksi = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, idTransaksi);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    transaksi = extractTransaksiFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transaksi;
    }

    private Transaksi extractTransaksiFromResultSet(ResultSet resultSet) throws SQLException {
        Transaksi transaksi = new Transaksi();
        transaksi.setIdTransaksi(resultSet.getLong("idTransaksi"));
        transaksi.setNicknameML(resultSet.getString("nicknameML"));
        transaksi.setIdItem(resultSet.getLong("idItem"));
        transaksi.setJumlahItem(resultSet.getInt("jumlahItem"));
        transaksi.setHarga(resultSet.getLong("harga"));
        transaksi.setTanggalTransaksi(resultSet.getTimestamp("tanggalTransaksi"));

        return transaksi;
    }
}
