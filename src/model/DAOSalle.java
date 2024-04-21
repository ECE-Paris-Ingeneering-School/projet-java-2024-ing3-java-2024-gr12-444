package model;

import controller.Controller;
import database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe qui gère les opérations liées aux salles dans la base de données
 */
public class DAOSalle {

    private Controller controller;
    private Salle salle;
    private int salleid;

    /**
     * Méthode qui obtient l'ID de la salle
     *
     * @return L'ID de la salle
     */
    public int getSalleid() {
        return salleid;
    }

    /**
     * Méthode qui définit l'ID de la salle
     *
     * @param salleid L'ID de la salle à définir
     */
    public void setSalleid(int salleid) {
        this.salleid = salleid;
    }

    /**
     * Méthode qui initialise la salle en fonction de l'ID fourni
     *
     * @param controller Le contrôleur utilisé pour récupérer l'ID de la salle
     */
    public void salle(Controller controller) {
        this.controller = controller;
        Connection dbConnection = null;
        Statement statement = null;

        String sql = "SELECT * FROM salle WHERE SalleID = '" + this.controller.getSalleidSalle() + "'";
        try {
            Database database = new Database();
            dbConnection = database.createConnection();
            try {
                statement = dbConnection.createStatement();
                statement.executeQuery(sql);
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {
                    salle = new Salle(rs.getInt(1), rs.getString("Nom"), rs.getInt(3));
                }

                System.out.println("read salle");
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

    /**
     * Méthode qui obtient l'objet Salle
     *
     * @return L'objet Salle
     */
    public Salle getSalle() {
        return salle;
    }

}