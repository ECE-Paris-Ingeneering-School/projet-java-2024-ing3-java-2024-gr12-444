package database;

import java.sql.*;

/**
 * Classe qui permet de gérer la connexion à la base de données.
 */
public class Database {
    Connection conn = null;

    /**
     * Méthode qui crée et retourne une connexion à la base de données.
     *
     * @return La connexion à la base de données.
     */
    public Connection createConnection() {
        try {

            // Paramètres de la base de données
            String url = "jdbc:mysql://127.0.0.1:3308/projetjava";
            String user = "root";
            String password = "";

            // Créer une connexion à la base de données
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Erreur survenue " + e.toString());
        }
        return conn;
    }
}
