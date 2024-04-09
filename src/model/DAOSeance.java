package model;

import controller.Controller;
import database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOSeance {
    private Controller controller;
    private int filmid;
    private Seance seance;
    private ArrayList<Seance> list= new ArrayList<>();

    public int getFilmid() {
        return filmid;
    }

    public void setFilmid(int filmid) {
        this.filmid = filmid;
    }

    public void seance(Controller controller){
        this.controller = controller;
        Connection dbConnection = null;
        Statement statement = null;

        String sql ="SELECT * FROM séance WHERE FilmID = '"+this.controller.getfilmid()+ "'";
        try {
            Database database = new Database();
            dbConnection = database.createConnection();
            try {
                statement = dbConnection.createStatement();
                statement.executeQuery(sql);
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {

                    seance= new Seance(rs.getInt(1),
                            rs.getInt(2),(rs.getInt(3)),
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

    public Seance getSeance() {
        return seance;
    }
    public ArrayList<Seance> getList() {
        return list;
    }
}
