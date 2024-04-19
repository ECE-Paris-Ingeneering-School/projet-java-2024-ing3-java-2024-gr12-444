package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOReduction {
    private static Connection conn;

    public DAOReduction(Connection conn) {
        this.conn = conn;
    }

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
}

