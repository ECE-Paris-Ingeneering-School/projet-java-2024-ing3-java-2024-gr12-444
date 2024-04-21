package model;

import controller.*;
import database.Database;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe qui gère les opérations liées aux films dans la base de données
 */
public class DAOFilm {
    private Controller controller;

    private ArrayList<Film> list = new ArrayList<>();

    /**
     * Récupère les données des films depuis la base de données et les stocke dans une liste
     *
     * @param controller Le contrôleur associé
     */
    public void film(Controller controller) {
        this.controller = controller;

        Connection dbConnection = null;
        Statement statement = null;

        String sql = "SELECT * FROM films WHERE 1";

        try {
            Database database = new Database();
            dbConnection = database.createConnection();
            try {
                statement = dbConnection.createStatement();
                statement.executeQuery(sql);
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {

                    Film film = new Film(rs.getInt(1),
                            rs.getString(2), String.valueOf(rs.getTime(3)),
                            rs.getString(4), rs.getString(5),
                            rs.getString(6), String.valueOf(rs.getDate(7)),
                            rs.getString(8));
                    list.add(film);
                }
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
     * Méthode qui ajoute un nouveau film à la base de données
     *
     * @param titre          Le titre du film
     * @param genre          Le genre du film
     * @param classification La classification du film
     * @param description    La description du film
     * @param poster         Le chemin vers le poster du film
     * @param datePicker     La date de sortie du film
     * @param timeSpinner    La durée du film
     */
    public void ajoutFilm(String titre, String genre, String classification, String description, String poster, java.util.Date datePicker, java.util.Date timeSpinner) {


        Date date = new Date(datePicker.getTime());
        Time duree = new Time(timeSpinner.getTime());

        if (titre.isEmpty() || genre.isEmpty() || classification.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Essayer encore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (poster.isEmpty()) {
            poster = "images/poster.jpg";
        }

        try {
            Database database = new Database();
            Connection conn = database.createConnection();
            System.out.println("connection success");

            Statement statement = conn.createStatement();
            String sql = "INSERT INTO films (Titre, Genre, Classification, Description, Durée, DateDeSortie, Poster)" + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, titre);
            preparedStatement.setString(2, genre);
            preparedStatement.setString(3, classification);
            preparedStatement.setString(4, description);
            preparedStatement.setTime(5, duree);
            preparedStatement.setDate(6, date);
            preparedStatement.setString(7, poster);

            preparedStatement.executeUpdate();

            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Méthode qui permet de supprimer un film
     *
     * @param idfilm      L'ID du film à supprimer
     */
    public void supprimerFilm(String idfilm) {


        if (idfilm.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Essayer encore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Database database = new Database();
            Connection conn = database.createConnection();
            System.out.println("connection success");

            Statement statement = conn.createStatement();
            String sql = "DELETE FROM films WHERE FilmID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, idfilm);
            int resultat = preparedStatement.executeUpdate();

            if (resultat > 0) {
                JOptionPane.showMessageDialog(null, "Le film a été supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Impossible de trouver un film avec cet ID", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Méthode qui récupère la liste des films
     *
     * @return La liste des films
     */
    public ArrayList<Film> getList() {
        return list;
    }
}