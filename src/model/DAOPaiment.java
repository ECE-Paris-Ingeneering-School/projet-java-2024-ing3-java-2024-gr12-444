package model;

import controller.Controller;
import database.Database;

import java.sql.*;

public class DAOPaiment {
    private Controller controller;

    /**
     * @param controller
     * @param userId
     * @param seanceId
     * @param nbBillet
     * @param prix
     */
    public void reservation(Controller controller, int userId, int seanceId, int nbBillet, double prix) {
        this.controller = controller;

        Connection dbConnection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        //faire la requete
        String sql = "INSERT INTO `réservation`(`UserID`, `SeanceID`, `NbDeBillets`, `Prix`) VALUES (?,?,?,?)";


        try {
            Database database = new Database();
            dbConnection = database.createConnection();
            try {
                statement = dbConnection.createStatement();
                preparedStatement = dbConnection.prepareStatement(sql);

                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, seanceId);
                preparedStatement.setInt(3, nbBillet);
                preparedStatement.setDouble(4, prix);

                preparedStatement.executeUpdate();

                System.out.println("Ajout dans la BDD de la réservation");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } finally {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
