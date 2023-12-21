package dao;

import Model.BeliAkun;
import connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import service.DAO_BeliAkunService;

public class DAO_BeliAkun implements DAO_BeliAkunService {

    private static final String INSERT_QUERY = "INSERT INTO beliakunml (nick, harga, ppn, income, email) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_EMAIL_QUERY = "SELECT * FROM beliakunml WHERE email = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM beliakunml";
    private static final String UPDATE_QUERY = "UPDATE beliakunml SET nick=?, harga=?, ppn=?, income=? WHERE email=?";
    private static final String DELETE_BY_EMAIL_QUERY = "DELETE FROM beliakunml WHERE email=?";

    @Override
    public void addBeliakunML(BeliAkun beliakunML) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, beliakunML.getNick());
            preparedStatement.setString(2, beliakunML.getHarga());
            preparedStatement.setString(3, beliakunML.getPpn());
            preparedStatement.setString(4, beliakunML.getIncome());
            preparedStatement.setString(5, beliakunML.getEmail());

            preparedStatement.executeUpdate();
            System.out.println("BeliAkunML added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BeliAkun getBeliakunMLByEmail(String email) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_QUERY)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    BeliAkun beliAkunML = new BeliAkun(
                            resultSet.getString("nick"),
                            resultSet.getString("harga"),
                            resultSet.getString("ppn"),
                            resultSet.getString("income"),
                            resultSet.getString("email")
                    );
                    return beliAkunML;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BeliAkun> getAllBeliakunMLs() {
        List<BeliAkun> beliAkunMLList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                BeliAkun beliAkunML = new BeliAkun(
                        resultSet.getString("nick"),
                        resultSet.getString("harga"),
                        resultSet.getString("ppn"),
                        resultSet.getString("income"),
                        resultSet.getString("email")
                );
                beliAkunMLList.add(beliAkunML);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beliAkunMLList;
    }

    @Override
    public void updateBeliakunML(BeliAkun beliakunML) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, beliakunML.getNick());
            preparedStatement.setString(2, beliakunML.getHarga());
            preparedStatement.setString(3, beliakunML.getPpn());
            preparedStatement.setString(4, beliakunML.getIncome());
            preparedStatement.setString(5, beliakunML.getEmail());

            preparedStatement.executeUpdate();
            System.out.println("BeliAkunML updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBeliakunMLByEmail(String email) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_EMAIL_QUERY)) {

            preparedStatement.setString(1, email);

            preparedStatement.executeUpdate();
            System.out.println("BeliAkunML deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
