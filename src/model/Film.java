package model;

/**
 * Classe qui représente un film
 */
public class Film {
    private int filmId;
    private String titre;
    private String time;
    private String genre;
    private String description;
    private String classification;
    private String date;
    private String poster;

    /**
     * Constructeur de la classe Film
     *
     * @param filmId         L'identifiant du film
     * @param titre          Le titre du film
     * @param time           La durée du film
     * @param genre          Le genre du film
     * @param description    La description du film
     * @param classification La classification du film
     * @param date           La date de sortie du film
     * @param poster         Le lien vers l'affiche du film
     */
    public Film(int filmId, String titre, String time, String genre, String description, String classification, String date, String poster) {
        this.filmId = filmId;
        this.titre = titre;
        this.time = time;
        this.genre = genre;
        this.description = description;
        this.classification = classification;
        this.date = date;
        this.poster = poster;
    }

    /**
     * Méthode qui obtient l'identifiant du film
     *
     * @return L'identifiant du film
     */
    public int getFilmId() {
        return filmId;
    }

    /**
     * Méthode qui définit l'identifiant du film
     *
     * @param filmId L'identifiant du film à définir
     */
    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    /**
     * Méthode qui obtient le titre du film
     *
     * @return Le titre du film
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Méthode qui définit le titre du film
     *
     * @param titre Le titre du film à définir
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Méthode qui obtient la durée du film
     *
     * @return La durée du film
     */
    public String getTime() {
        return time;
    }

    /**
     * Méthode qui définit la durée du film
     *
     * @param time La durée du film à définir
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Méthode qui obtient le genre du film
     *
     * @return Le genre du film
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Méthode qui définit le genre du film
     *
     * @param genre Le genre du film à définir
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Méthode qui obtient la description du film
     *
     * @return La description du film
     */
    public String getDescription() {
        return description;
    }

    /**
     * Méthode qui définit la description du film
     *
     * @param description La description du film à définir
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Méthode qui obtient la classification du film
     *
     * @return La classification du film
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Méthode qui définit la classification du film
     *
     * @param classification La classification du film à définir
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }

    /**
     * Méthode qui obtient la date de sortie du film
     *
     * @return La date de sortie du film
     */
    public String getDate() {
        return date;
    }

    /**
     * Méthode qui définit la date de sortie du film
     *
     * @param date La date de sortie du film à définir
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Méthode qui obtient le lien vers l'affiche du film
     *
     * @return Le lien vers l'affiche du film
     */
    public String getPoster() {
        return poster;
    }

    /**
     * Méthode qui définit le lien vers l'affiche du film
     *
     * @param poster Le lien vers l'affiche du film à définir
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }
}