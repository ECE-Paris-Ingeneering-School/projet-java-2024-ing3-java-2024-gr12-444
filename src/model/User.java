package model;

/**
 * Classe qui représente un utilisateur du système
 */
public class User {
    public int userId;
    public String prenom;
    public String nom;
    public String age;
    public String mail;
    public String motDePasse;
    public String typeMembre;

    /**
     * Méthode qui obtient le prénom de l'utilisateur
     *
     * @return Le prénom de l'utilisateur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Méthode qui obtient le nom de l'utilisateur
     *
     * @return Le nom de l'utilisateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode qui obtient l'adresse e-mail de l'utilisateur
     *
     * @return L'adresse e-mail de l'utilisateur
     */
    public String getMail() {
        return mail;
    }

    /**
     * Méthode qui obtient l'âge de l'utilisateur
     *
     * @return L'âge de l'utilisateur
     */
    public String getAge() {
        return age;
    }

    /**
     * Méthode qui obtient l'identifiant de l'utilisateur
     *
     * @return L'identifiant de l'utilisateur
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Méthode qui obtient le type de membre de l'utilisateur
     *
     * @return Le type de membre de l'utilisateur
     */
    public String getTypeMembre() {
        return typeMembre;
    }
}