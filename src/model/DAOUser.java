package model;

import controller.*;
import database.Database;

import java.sql.*;

public class DAOUser {
    private String username;
    private String password;
    private int typemembre;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getUser(String email, String motDePasse) {
        User user = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = new Database().createConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM user WHERE Email=? AND MotDePasse=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, motDePasse);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.userId = resultSet.getInt("UserID");
                user.prenom = resultSet.getString("Prenom");
                user.nom = resultSet.getString("Nom");
                user.age = resultSet.getString("Age");
                user.mail = resultSet.getString("Email");
                user.motDePasse = resultSet.getString("motDePasse");
                user.typeMembre = resultSet.getString("typeMembre");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public static User addUser(String prenom, String nom, String age, String mail, String motDePasse) {
        User user = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = new Database().createConnection();
            preparedStatement = conn.prepareStatement("INSERT INTO user (Prenom, Nom, Age, Email, MotDePasse, TypeMembre) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, prenom);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, age);
            preparedStatement.setString(4, mail);
            preparedStatement.setString(5, motDePasse);
            if (Integer.parseInt(age) > 0 && Integer.parseInt(age) < 18) {
                preparedStatement.setInt(6, 1);
            }
            else if (Integer.parseInt(age) >= 18 && Integer.parseInt(age) < 60) {
                preparedStatement.setInt(6, 3);
            }
            else if (Integer.parseInt(age) >= 60){
                preparedStatement.setInt(6, 2);
            }

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                user = new User();
                user.prenom = prenom;
                user.nom = nom;
                user.age = age;
                user.mail = mail;
                user.motDePasse = motDePasse;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}


    /*public Boolean verifUser(Controller controller) {
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
                    System.out.println("Type membre :"+rs.getInt("TypeMembre"));
                    typemembre = rs.getInt("TypeMembre");

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


public int getTypemembre() {
    return typemembre;
}
*/
