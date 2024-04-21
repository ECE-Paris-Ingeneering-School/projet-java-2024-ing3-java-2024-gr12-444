package controller;

import database.Database;
import model.*;
import vue.Ajouter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

    private final DAOUser daoUser;
    private final DAOFilm daoFilm;
    private final DAOSeance daoSeance;
    private DAOReservation daoReservation;
    private Seance seance;
    private Salle salle;
    private User user;

    private final DAOSalle daoSalle;

    private DAOPaiment daoPaiment;

    private DAOReduction daoReduction;
    private Reduction reduction;

    public Controller() {
        this.daoUser = new DAOUser();
        this.daoFilm = new DAOFilm();
        this.daoSeance = new DAOSeance();
        this.daoSalle = new DAOSalle();
        this.daoPaiment = new DAOPaiment();

    }

    /**
     * @param email
     * @param motDePasse
     * @return
     */
    public User connect(String email, String motDePasse) {
        return DAOUser.getUser(email, motDePasse);
    }

    /**
     * @param prenom
     * @param nom
     * @param age
     * @param mail
     * @param motDePasse
     * @return
     */
    public User registerUser(String prenom, String nom, String age, String mail, String motDePasse) {
        return DAOUser.addUser(prenom, nom, age, mail, motDePasse);
    }

    /**
     * @param userId
     * @return
     */
    public ArrayList<Reservation> getReservations(int userId) {
        Connection conn = null;
        conn = new Database().createConnection();
        daoReservation = new DAOReservation(conn);

        try {
            ArrayList<Reservation> reservations = DAOReservation.getReservations(userId);
            conn.close();
            return reservations;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public void film() {
        daoFilm.film(this);
    }

    public void seance() {
        daoSeance.seance(this);
    }

    public int getfilmid() {
        return daoSeance.getFilmid();
    }

    public void setFilmid(int filmid) {
        daoSeance.setFilmid(filmid);
    }

    /**
     * @return
     */
    public ArrayList<Film> getListFilm() {
        return daoFilm.getList();
    }


    //getter seance
    public Seance getSeance() {
        return daoSeance.getSeance();
    }

    public ArrayList<Seance> getListSeance() {
        return daoSeance.getList();
    }

    public int getSeanceId() {
        return seance.getSeanceId();
    }

    public int getFilmId() {
        return seance.getFilmId();
    }

    public int getSalleIdSeance() {
        return seance.getSalleID();
    }

    public String getHeure() {
        return seance.getHeureDeDebut();
    }

    public String getDate() {
        return seance.getDate();
    }

    public int getNbPlace() {
        return seance.getNbplace();
    }

    public Salle getSalle() {
        return daoSalle.getSalle();
    }

    public int getSalleidSalle() {
        return daoSalle.getSalleid();
    }

    public void setSalleid(int salleID) {
        daoSalle.setSalleid(salleID);
    }

    public void salle() {
        daoSalle.salle(this);
    }


    /**
     * @param userId
     * @param seanceId
     * @param nbBillet
     * @param prix
     */
    public void reservation(int userId, int seanceId, int nbBillet, double prix) {
        daoPaiment.reservation(this, userId, seanceId, nbBillet, prix);
    }

    /**
     * @param seanceId
     * @return
     */
    public ArrayList<Reduction> getReductions(int seanceId) {
        Connection conn = null;
        conn = new Database().createConnection();
        daoReduction = new DAOReduction(conn);

        try {
            ArrayList<Reduction> reductions = DAOReduction.getReduction(seanceId);
            conn.close();
            return reductions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public int getReduction() {
        return reduction.getReduction();
    }

    /**
     * @param seanceId
     * @param nbPlaces
     */
    public void decreaseSeanceSeats(int seanceId, int nbPlaces) {
        daoSeance.decreaseSeanceSeats(seanceId, nbPlaces);
    }

    /**
     * @param titre
     * @param genre
     * @param classification
     * @param description
     * @param poster
     * @param datePicker
     * @param timeSpinner
     */
    public void ajoutFilm(String titre, String genre, String classification, String description, String poster, java.util.Date datePicker, java.util.Date timeSpinner) {
        daoFilm.ajoutFilm(titre, genre, classification, description, poster, datePicker, timeSpinner);
    }

    /**
     * @param idSeance
     * @param reduc
     */
    public void ajoutReduc(String idSeance, String reduc){
        if (daoReduction == null) {
            Connection conn = new Database().createConnection();
            daoReduction = new DAOReduction(conn);
        }
        daoReduction.ajoutReduc(idSeance, reduc);
    }

    /**
     * @param idfilm
     * @param idsalle
     * @param datePicker
     * @param timeSpinner
     */
    public void ajoutSeance(String idfilm, String idsalle, java.util.Date datePicker, java.util.Date timeSpinner){
        daoSeance.ajoutSeance(idfilm, idsalle, datePicker, timeSpinner);
    }


}
