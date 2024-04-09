package controller;

import model.*;

import java.util.ArrayList;

public class Controller {

    private final DAOUser daoUser;
    private final DAOFilm daoFilm;
    private final DAOSeance daoSeance;
    private Seance seance;

    public Controller() {
        this.daoUser = new DAOUser();
        this.daoFilm = new DAOFilm();
        this.daoSeance= new DAOSeance();

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

    public Boolean verifUser() {
        return daoUser.verifUser(this);
    }

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
    public int getSalleId(){
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

}
