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

        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM réservation WHERE UserID = ?")) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int seanceId = resultSet.getInt("SeanceID");
                int nbDeBillets = resultSet.getInt("NbDeBillets");
                double prix = resultSet.getDouble("Prix");
                Reservation reservation = new Reservation(userId, seanceId, nbDeBillets, prix);
                userReservations.add(reservation);
            }
        }
        return userReservations;
    }
}

