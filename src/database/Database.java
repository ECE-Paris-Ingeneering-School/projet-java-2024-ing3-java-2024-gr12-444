package database;

import java.sql.*;


public class Database {
    Connection conn = null;

    public Connection createConnection()
    {
        try
        {

            // db parameters
            String url       = "jdbc:mysql://127.0.0.1:3308/ececine";
            String user      = "root";
            String password  = "";

            //Class.forName("com.mysql.jdbc.Driver");
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("connection success");
        }
        catch( Exception e )
        {
            System.out.println("Error Occured " + e.toString());
        }
        return conn;
    }



}