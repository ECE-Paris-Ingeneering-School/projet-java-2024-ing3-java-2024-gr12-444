package model;

import controller.Controller;
import database.Database;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DAOSeance {
    private Controller controller;
    private int filmid;
    private Seance seance;
    private ArrayList<Seance> list = new ArrayList<>();

    /**
     * @return filmid
     */
    public int getFilmid() {
        return filmid;
    }

    /**
     * @param filmid
     */
    public void setFilmid(int filmid) {
        this.filmid = filmid;
    }

    /**
     * @param controller
     */
    public void seance(Controller controller) {
        this.controller = controller;
        Connection dbConnection = null;
        Statement statement = null;

        String sql = "SELECT * FROM séance WHERE FilmID = '" + this.controller.getfilmid() + "'";
        try {
            Database database = new Database();
            dbConnection = database.createConnection();
            try {
                statement = dbConnection.createStatement();
                statement.executeQuery(sql);
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {

                    seance = new Seance(rs.getInt(1),
                            rs.getInt(2), (rs.getInt(3)),
                            String.valueOf(rs.getTime(4)), String.valueOf(rs.getDate(5)),
                            rs.getInt(6));
                    list.add(seance);
                }

                System.out.println("readSeance");
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
     * @return seance
     */
    public Seance getSeance() {
        return seance;
    }

    /**
     * @return list
     */
    public ArrayList<Seance> getList() {
        return list;
    }

    /**
     * @param seanceId
     * @param nbPlaces
     */
    public void decreaseSeanceSeats(int seanceId, int nbPlaces) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;


        String sql = "UPDATE séance SET NbPlacesRestantes = NbPlacesRestantes - ? WHERE séance.SeanceID = " + seanceId;
        try {
            Database database = new Database();
            dbConnection = database.createConnection();
            try {
                preparedStatement = dbConnection.prepareStatement(sql);
                preparedStatement.setInt(1, nbPlaces);
                int resultSet = preparedStatement.executeUpdate();
                System.out.println("decreaseSeanceSeats");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
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
     * @param idfilm
     * @param idsalle
     * @param selectedDate2
     * @param selectedTime2
     */
    public void ajoutSeance(String idfilm, String idsalle, java.util.Date selectedDate2, java.util.Date selectedTime2) {

        Date date2 = new Date(selectedDate2.getTime());
        Time debut = new Time(selectedTime2.getTime());
        String nbplaces = "";

        if (idfilm.isEmpty() || idsalle.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Essayer encore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ("1".equals(idsalle)) {
            nbplaces = "150";
        } else if ("2".equals(idsalle)) {
            nbplaces = "100";
        } else if ("3".equals(idsalle)) {
            nbplaces = "2800";
        } else if ("4".equals(idsalle)) {
            nbplaces = "300";
        } else if ("5".equals(idsalle)) {
            nbplaces = "430";
        } else if ("6".equals(idsalle)) {
            nbplaces = "10";
        }

        try {
            Database database = new Database();
            Connection conn = database.createConnection();
            System.out.println("connection success");

            Statement statement = conn.createStatement();
            String sql = "INSERT INTO séance (FilmID, SalleID, HeureDeDebut, Date, NbPlacesRestantes)" + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, idfilm);
            preparedStatement.setString(2, idsalle);
            preparedStatement.setTime(3, debut);
            preparedStatement.setDate(4, date2);
            preparedStatement.setString(5, nbplaces);

            preparedStatement.executeUpdate();


            //fermeture
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
