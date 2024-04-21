package model;

/**
 * Classe qui représente une séance de cinéma
 */
public class Seance {
    private int seanceId;
    private int filmId;
    private int salleID;
    private String heureDeDebut;
    private String date;
    private int nbplace;

    /**
     * Constructeur de la classe Seance
     *
     * @param seanceId     L'identifiant de la séance
     * @param filmId       L'identifiant du film projeté lors de la séance
     * @param salleID      L'identifiant de la salle où se déroule la séance
     * @param heureDeDebut L'heure de début de la séance
     * @param date         La date de la séance
     * @param nbplace      Le nombre de places disponibles pour la séance
     */
    public Seance(int seanceId, int filmId, int salleID, String heureDeDebut, String date, int nbplace) {
        this.seanceId = seanceId;
        this.filmId = filmId;
        this.salleID = salleID;
        this.heureDeDebut = heureDeDebut;
        this.date = date;
        this.nbplace = nbplace;
    }

    /**
     * Méthode qui obtient l'identifiant de la séance
     *
     * @return L'identifiant de la séance
     */
    public int getSeanceId() {
        return seanceId;
    }

    /**
     * Méthode qui obtient l'identifiant du film projeté lors de la séance
     *
     * @return L'identifiant du film
     */
    public int getFilmId() {
        return filmId;
    }

    /**
     * Méthode qui obtient l'identifiant de la salle où se déroule la séance
     *
     * @return L'identifiant de la salle
     */
    public int getSalleID() {
        return salleID;
    }

    /**
     * Méthode qui obtient l'heure de début de la séance
     *
     * @return L'heure de début de la séance
     */
    public String getHeureDeDebut() {
        return heureDeDebut;
    }

    /**
     * Méthode qui obtient la date de la séance
     *
     * @return La date de la séance
     */
    public String getDate() {
        return date;
    }

    /**
     * Méthode qui obtient le nombre de places disponibles pour la séance
     *
     * @return Le nombre de places disponibles
     */
    public int getNbplace() {
        return nbplace;
    }
}