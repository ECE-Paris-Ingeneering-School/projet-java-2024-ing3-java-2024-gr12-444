package model;

public class Salle {
    private int salleId;
    private String nom;
    private int nbPlaceSalle;

    public Salle(int salleId, String nom, int nbPlaceSalle) {
        this.salleId = salleId;
        this.nom = nom;
        this.nbPlaceSalle = nbPlaceSalle;
    }

    public int getSalleId() {
        return salleId;
    }

    public String getNom() {
        return nom;
    }

    public int getNbPlaceSalle() {
        return nbPlaceSalle;
    }
}
