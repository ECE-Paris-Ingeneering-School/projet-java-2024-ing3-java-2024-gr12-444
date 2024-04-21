package model;

public class Seance {
    private int seanceId;
    private int filmId;
    private int salleID;
    private String heureDeDebut;
    private String date;
    private int nbplace;

    /**
     * @param seanceId
     * @param filmId
     * @param salleID
     * @param heureDeDebut
     * @param date
     * @param nbplace
     */
    public Seance(int seanceId, int filmId, int salleID, String heureDeDebut, String date, int nbplace) {
        this.seanceId = seanceId;
        this.filmId = filmId;
        this.salleID = salleID;
        this.heureDeDebut = heureDeDebut;
        this.date = date;
        this.nbplace = nbplace;

    }

    public int getSeanceId() {
        return seanceId;
    }

    public int getFilmId() {
        return filmId;
    }

    public int getSalleID() {
        return salleID;
    }

    public String getHeureDeDebut() {
        return heureDeDebut;
    }

    public String getDate() {
        return date;
    }

    public int getNbplace() {
        return nbplace;
    }
}
