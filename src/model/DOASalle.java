package model;

import controller.Controller;
import database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DOASalle {

    private Controller controller;
    private Salle salle;
    private int salleid;

    public int getSalleid() {
        return salleid;
    }

    public void setSalleid(int salleid) {
        this.salleid = salleid;
    }

    public void salle(Controller controller){
        this.controller = controller;
        Connection dbConnection = null;
        Statement statement = null;

        String sql ="SELECT * FROM salle WHERE SalleID = '"+this.controller.getSalleidSalle()+ "'";
        try {
            Database database = new Database();
            dbConnection = database.createConnection();
            try {
                statement = dbConnection.createStatement();
                statement.executeQuery(sql);
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {
                    salle= new Salle(rs.getInt(1),rs.getString("Nom"), rs.getInt(3));
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

    public Salle getSalle() {
        return salle;
    }

}
