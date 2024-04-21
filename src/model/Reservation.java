package model;

/**
 * Classe qui représente une réservation pour une séance
 */
public class Reservation {
    private int userId;
    private int seanceId;
    private int nbDeBillets;
    private double prix;
    private String filmTitle;
    private String date;

    /**
     * Constructeur de la classe Reservation
     *
     * @param userId      L'identifiant de l'utilisateur effectuant la réservation
     * @param seanceId    L'identifiant de la séance réservée
     * @param nbDeBillets Le nombre de billets réservés
     * @param prix        Le prix total de la réservation
     */
    public Reservation(int userId, int seanceId, int nbDeBillets, double prix) {
        this.userId = userId;
        this.seanceId = seanceId;
        this.nbDeBillets = nbDeBillets;
        this.prix = prix;
    }

    /**
     * Méthode qui obtient le titre du film associé à la réservation
     *
     * @return Le titre du film
     */
    public String getFilmTitle() {
        return filmTitle;
    }

    /**
     * Méthode qui définit le titre du film associé à la réservation
     *
     * @param filmTitle Le titre du film
     */
    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    /**
     * Méthode qui obtient la date de la séance associée à la réservation
     *
     * @return La date de la séance
     */
    public String getDate() {
        return date;
    }

    /**
     * Méthode qui définit la date de la séance associée à la réservation
     *
     * @param date La date de la séance
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Méthode qui obtient le nombre de billets réservés
     *
     * @return Le nombre de billets réservés
     */
    public int getNbDeBillets() {
        return nbDeBillets;
    }

    /**
     * Méthode qui obtient le prix total de la réservation
     *
     * @return Le prix total de la réservation
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Méthode qui retourne une représentation textuelle de la réservation
     *
     * @return Une chaîne de caractères représentant la réservation
     */
    @Override
    public String toString() {
        return "Réservation pour l'utilisateur avec l'ID : " + userId + " : " + "Séance ID : " + seanceId + ", Nombre de billets : " + nbDeBillets + ", Prix : " + prix;
    }
}