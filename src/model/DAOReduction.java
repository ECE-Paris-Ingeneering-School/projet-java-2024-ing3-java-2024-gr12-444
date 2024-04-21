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

    /**
     * Méthode qui permet de modifier une reduction
     *
     * @param idReduc l'ID de la reduc à changer
     * @param idSeance l'ID de la séance
     * @param reduc    Le montant de la réduction
     */
    public void modifierReduc(String idReduc, String idSeance, String reduc) {
        if (idReduc.isEmpty() || idSeance.isEmpty() || reduc.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Essayer encore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String sql = "UPDATE réductions SET IDseance = ?, Réduction = ? WHERE IDRéductions = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, idSeance);
            preparedStatement.setString(2, reduc);
            preparedStatement.setString(3, idReduc);

            int resultat = preparedStatement.executeUpdate();
            if (resultat > 0) {
                JOptionPane.showMessageDialog(null, "La réduction a été modifiée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Impossible de trouver une réduction avec cet ID", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}