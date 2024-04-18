package model;

public class Reservation {
    private int userId;
    private int seanceId;
    private int nbDeBillets;
    private double prix;

    public Reservation (int userId, int seanceId, int nbDeBillets, double prix) {
        this.userId = userId;
        this.seanceId = seanceId;
        this.nbDeBillets = nbDeBillets;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Réservation pour l'utilisateur avec l'ID : " + userId + " : " + "Séance ID : " + seanceId + ", Nombre de billets : " + nbDeBillets + ", Prix : " + prix;
    }
}


