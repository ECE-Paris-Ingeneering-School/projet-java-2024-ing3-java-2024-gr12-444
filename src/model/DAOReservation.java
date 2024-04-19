package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOReservation {
    private static Connection conn;

    public DAOReservation(Connection conn) {
        this.conn = conn;
    }

    public static ArrayList<Reservation> getReservations(int userId) throws SQLException {
        ArrayList<Reservation> userReservations = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT r.*, s.Date, f.Titre " +
                        "FROM réservation r " +
                        "JOIN séance s ON r.SeanceID = s.SeanceID " +
                        "JOIN films f ON s.FilmID = f.FilmID " +
                        "WHERE r.UserID = ?")) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int seanceId = resultSet.getInt("SeanceID");
                int nbDeBillets = resultSet.getInt("NbDeBillets");
                double prix = resultSet.getDouble("Prix");
                String filmTitle = resultSet.getString("Titre");
                String seanceDate = resultSet.getString("Date");
                Reservation reservation = new Reservation(userId, seanceId, nbDeBillets, prix);
                reservation.setFilmTitle(filmTitle);
                reservation.setDate(seanceDate);
                userReservations.add(reservation);
            }
        }
        return userReservations;
    }
}

