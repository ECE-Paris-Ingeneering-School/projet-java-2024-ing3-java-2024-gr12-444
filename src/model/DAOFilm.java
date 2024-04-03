package model;

import controller.*;
import database.Database;

import java.sql.*;
import java.util.ArrayList;

public class DAOFilm {
    private Controller controller;

    private ArrayList<String> list= new ArrayList<>();

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
                    displayData(rs, list);
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

    public ArrayList<String> getList() {
        return list;
    }

    public static void displayData(ResultSet rs, ArrayList<String> list) throws SQLException {
        Integer filmId = rs.getInt(1);
        String titre = rs.getString(2);
        Time time  = rs.getTime(3);
        String genre = rs.getString(4);
        String description = rs.getString(5);
        String classification = rs.getString(6);
        Date date = rs.getDate(7);
        String poster = rs.getString(8);
        System.out.println("id:" + filmId );
        System.out.println("titre:" + titre);
        System.out.println("time:" + time);
        System.out.println("genre:" + genre);
        System.out.println("des :" + description);
        System.out.println("class:" + classification);
        System.out.println("dob:" + date);
        System.out.println("poster:" + poster);
        System.out.println("");
        list.add(String.valueOf(filmId));
        list.add(titre);
        list.add(String.valueOf(time));
        list.add(genre);
        list.add(description);
        list.add(classification);
        list.add(String.valueOf(date));
        list.add(poster);
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//
//        }


    }
}

