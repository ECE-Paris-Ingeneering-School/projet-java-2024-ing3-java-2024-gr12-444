package model;

public class Reservation {
    private int userId;
    private int seanceId;
    private int nbDeBillets;
    private double prix;
    private String filmTitle;
    private String date;

    /**
     * @param userId
     * @param seanceId
     * @param nbDeBillets
     * @param prix
     */
    public Reservation(int userId, int seanceId, int nbDeBillets, double prix) {
        this.userId = userId;
        this.seanceId = seanceId;
        this.nbDeBillets = nbDeBillets;
        this.prix = prix;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNbDeBillets() {
        return nbDeBillets;
    }

    public double getPrix() {
        return prix;
    }

    @Override
    public String toString() {
        return "Réservation pour l'utilisateur avec l'ID : " + userId + " : " + "Séance ID : " + seanceId + ", Nombre de billets : " + nbDeBillets + ", Prix : " + prix;
    }
}


