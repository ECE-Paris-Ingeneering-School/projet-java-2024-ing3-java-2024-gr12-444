package model;

/**
 * Classe qui représente une salle de cinéma
 */
public class Salle {
    private int salleId;
    private String nom;
    private int nbPlaceSalle;

    /**
     * Constructeur de la classe Salle
     *
     * @param salleId      L'identifiant de la salle
     * @param nom          Le nom de la salle
     * @param nbPlaceSalle Le nombre de places disponibles dans la salle
     */
    public Salle(int salleId, String nom, int nbPlaceSalle) {
        this.salleId = salleId;
        this.nom = nom;
        this.nbPlaceSalle = nbPlaceSalle;
    }

    /**
     * Méthode qui obtient l'identifiant de la salle
     *
     * @return L'identifiant de la salle
     */
    public int getSalleId() {
        return salleId;
    }

    /**
     * Méthode qui obtient le nom de la salle
     *
     * @return Le nom de la salle
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode qui obtient le nombre de places disponibles dans la salle
     *
     * @return Le nombre de places disponibles dans la salle
     */
    public int getNbPlaceSalle() {
        return nbPlaceSalle;
    }
}