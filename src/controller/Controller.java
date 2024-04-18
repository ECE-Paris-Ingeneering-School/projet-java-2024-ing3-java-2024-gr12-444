package controller;

import model.*;

import java.util.ArrayList;

public class Controller {

    private final DAOUser daoUser;
    private final DAOFilm daoFilm;
    private final DAOSeance daoSeance;
    private Seance seance;
    private Salle salle;
    private User user;

    private final DOASalle daoSalle;

    private DAOPaiment daoPaiment;

    public Controller() {
        this.daoUser = new DAOUser();
        this.daoFilm = new DAOFilm();
        this.daoSeance= new DAOSeance();
        this.daoSalle = new DOASalle();
        this.daoPaiment =new DAOPaiment();


    }

    public void setUsername(String username) {
        daoUser.setUsername(username);
    }

    public void setPassword(String password) {
        daoUser.setPassword(password);
    }

    public String getUsername() {
        return daoUser.getUsername();
    }

    public String getPassword() {
        return daoUser.getPassword();
    }


    public User connect(String email, String motDePasse) {
        return DAOUser.getUser(email, motDePasse);
    }

    public User registerUser(String prenom, String nom, String age, String mail, String motDePasse) {
        return DAOUser.addUser(prenom, nom, age, mail, motDePasse);
    }
/*
    public Boolean verifUser() {
        return daoUser.verifUser(this);
    }
*/
    /*public int getTypemembre(){return daoUser.getTypemembre();}*/

    public void film(){
        daoFilm.film(this);
    }
    public void seance(){
        daoSeance.seance(this);
    }

    public int getfilmid() {
        return daoSeance.getFilmid();
    }
    public void setFilmid(int filmid) {
        daoSeance.setFilmid(filmid);
    }

    public ArrayList<Film> getListFilm(){
        return daoFilm.getList();
    }


    //getter seance
    public Seance getSeance(){return daoSeance.getSeance();}
    public ArrayList<Seance> getListSeance(){
        return daoSeance.getList();
    }
    public int getSeanceId(){
        return seance.getSeanceId();
    }
    public int getFilmId(){
        return seance.getFilmId();
    }
    public int getSalleIdSeance(){
        return seance.getSalleID();
    }
    public String getHeure(){
        return seance.getHeureDeDebut();
    }
    public String getDate(){
        return seance.getDate();
    }
    public int getNbPlace(){
        return seance.getNbplace();
    }

    public Salle getSalle(){
        return daoSalle.getSalle();
    }
    public int getSalleidSalle() {
        return daoSalle.getSalleid();
    }
    public void setSalleid(int salleID) {
        daoSalle.setSalleid(salleID);

    }
    public void salle(){
        daoSalle.salle(this);
    }


    public void reservation(int userId, int seanceId, int nbBillet, double prix){
        daoPaiment.reservation(this, userId,seanceId,nbBillet, prix);
    }
}
