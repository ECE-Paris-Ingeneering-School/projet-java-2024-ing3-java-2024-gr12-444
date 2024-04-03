package model;

import controller.*;
import database.Database;

import java.sql.*;

public class DAOUser {
    private String username;
    private String password;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    private Controller controller;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean verifUser(Controller controller) {
        this.controller = controller;
        Connection dbConnection = null;
        Statement statement = null;

        // controller.pass();

        String sql = "SELECT * FROM user WHERE Email = '" + this.controller.getUsername() + "' AND MotDePasse = '"
                + this.controller.getPassword() + "'";
        // SELECT * FROM user WHERE username = 'mama' AND password= "mama";

        try {
            Database database = new Database();
            dbConnection = database.createConnection();
            try {
                statement = dbConnection.createStatement();
                statement.executeQuery(sql);
                ResultSet rs = statement.getResultSet();
                if (rs.next()) {
                    System.out.println(rs.getMetaData());
                    System.out.println("ID :"+rs.getInt("UserID"));
                    System.out.println("Statut :"+rs.getInt("Statut"));
                    System.out.println("User found");
                    return true;
                } else {
                    System.out.println("User not found");
                    return false;
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
}
