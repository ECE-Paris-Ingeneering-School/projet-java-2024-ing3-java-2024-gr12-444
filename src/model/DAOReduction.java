package model;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe qui gère les opérations de réduction dans la base de données
 */
public class DAOReduction {
    private static Connection conn;

    /**
     * Constructeur de la classe DAOReduction
     *
     * @param conn La connexion à la base de données
     */
    public DAOReduction(Connection conn) {
        this.conn = conn;
    }

    /**
     * Méthode qui récupère les réductions associées à une séance donnée
     *
     * @param seanceId L'ID de la séance pour laquelle récupérer les réductions
     * @return Une liste d'objets Reduction.
     * @throws SQLException Si une erreur SQL survient lors de l'exécution de la requête
     */
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

    /**
     * Méthode qui ajoute une nouvelle réduction dans la base de données
     *
     * @param idSeance L'ID de la séance associée à la réduction
     * @param reduc    Le montant de la réduction à ajouter
     */
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