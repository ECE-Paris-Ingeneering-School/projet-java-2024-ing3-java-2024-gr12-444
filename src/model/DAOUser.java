package model;

import controller.*;
import database.Database;
import vue.LoginInterface;

import java.sql.*;

public class DAOUser {

    public void verifUser() {
        Connection dbConnection = null;
        Statement statement = null;

        Controller controller =new Controller();
        controller.pass();

        String sql = "SELECT * FROM user WHERE username = '" + controller.username()+ "' AND password = '" + controller.password() + "'";
        //SELECT * FROM user WHERE username = 'mama' AND password= "mama";

        try {
            Database database = new Database();
            dbConnection = database.createConnection();
            try {
                statement = dbConnection.createStatement();
                statement.executeQuery(sql);
                System.out.println("read");
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
