package model;

import controller.*;
import database.Database;

import java.sql.*;

/**
 * Classe qui gère les opérations liées aux utilisateurs dans la base de données
 */
public class DAOUser {
    private String username;
    private String password;
    private int typemembre;

    /**
     * Méthode qui obtient le nom d'utilisateur
     *
     * @return Le nom d'utilisateur
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Méthode qui obtient le mot de passe
     *
     * @return Le mot de passe
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Méthode qui définit le nom d'utilisateur
     *
     * @param username Le nom d'utilisateur à définir
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Méthode qui définit le mot de passe
     *
     * @param password Le mot de passe à définir
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Méthode qui récupère un utilisateur à partir de son adresse e-mail et de son mot de passe
     *
     * @param email      L'adresse e-mail de l'utilisateur
     * @param motDePasse Le mot de passe de l'utilisateur
     * @return L'utilisateur correspondant aux informations fournies
     */
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

    /**
     * Méthode qui ajoute un nouvel utilisateur à la base de données
     *
     * @param prenom     Le prénom de l'utilisateur
     * @param nom        Le nom de l'utilisateur
     * @param age        L'âge de l'utilisateur
     * @param mail       L'adresse e-mail de l'utilisateur
     * @param motDePasse Le mot de passe de l'utilisateur
     * @return L'utilisateur ajouté à la base de données
     */
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
            } else if (Integer.parseInt(age) >= 18 && Integer.parseInt(age) < 60) {
                preparedStatement.setInt(6, 3);
            } else if (Integer.parseInt(age) >= 60) {
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