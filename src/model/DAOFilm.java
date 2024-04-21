package model;

import controller.*;
import database.Database;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DAOFilm {
    private Controller controller;

    private ArrayList<Film> list = new ArrayList<>();

    /**
     * @param controller
     */
    public void film(Controller controller) {
        this.controller = controller;

        Connection dbConnection = null;
        Statement statement = null;

        //faire la requete
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

                // System.out.println("read");
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


            //fermeture
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Film> getList() {
        return list;
    }


}

