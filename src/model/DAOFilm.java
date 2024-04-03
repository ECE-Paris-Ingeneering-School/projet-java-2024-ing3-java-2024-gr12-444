package model;

import controller.*;
import database.Database;

import java.sql.*;
import java.util.ArrayList;

public class DAOFilm {
    private Controller controller;

    private ArrayList<Film> list= new ArrayList<>();

    public void film(Controller controller){
        this.controller= controller;

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

                    Film film= new Film(rs.getInt(1),
                            rs.getString(2),String.valueOf(rs.getTime(3)),
                            rs.getString(4),rs.getString(5),
                            rs.getString(6),String.valueOf(rs.getDate(7)),
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

    public ArrayList<Film> getList() {
        return list;
    }


}

