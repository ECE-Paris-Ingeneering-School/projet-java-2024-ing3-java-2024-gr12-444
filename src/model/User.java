package model;

public class User {
    public int userId;
    public String prenom;
    public String nom;
    public String age;
    public String mail;
    public String motDePasse;
    public String typeMembre;

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getMail() {
        return mail;
    }

    public String getAge() {
        return age;
    }

    public int getUserId() {
        return userId;
    }
}
