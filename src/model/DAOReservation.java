package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe qui gère les opérations de réservation dans la base de données
 */
public class DAOReservation {
    private static Connection conn;

    /**
     * Constructeur de la classe DAOReservation
     *
     * @param conn La connexion à la base de données
     */
    public DAOReservation(Connection conn) {
        this.conn = conn;
    }

    /**
     * Méthode qui récupère les réservations d'un utilisateur donné
     *
     * @param userId L'ID de l'utilisateur pour lequel récupérer les réservations
     * @return Une liste d'objets Reservation.
     * @throws SQLException Si une erreur SQL survient lors de l'exécution de la requête
     */
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