package model;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DAOReduction {
    private static Connection conn;

    public DAOReduction(Connection conn) {
        this.conn = conn;
    }

    public static ArrayList<Reduction> getReduction(int seanceId) throws SQLException {
        ArrayList<Reduction> reductions = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM réductions WHERE IDSeance = ?")) {
            preparedStatement.setInt(1, seanceId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int reductionId = resultSet.getInt(1);
                int reduction = resultSet.getInt(3);
                Reduction reduction1 = new Reduction(reductionId, seanceId, reduction);
                reductions.add(reduction1);
            }
        }
        return reductions;
    }

    public void ajoutReduc(String idSeance, String reduc) {
        if (idSeance.isEmpty() || reduc.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Essayer encore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String sql = "INSERT INTO réductions (IDseance, Réduction) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, idSeance);
            preparedStatement.setString(2, reduc);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

