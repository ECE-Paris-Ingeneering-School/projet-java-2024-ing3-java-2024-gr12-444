package model;

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
     * @param filmId
     * @param titre
     * @param time
     * @param genre
     * @param description
     * @param classification
     * @param date
     * @param poster
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

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

}
