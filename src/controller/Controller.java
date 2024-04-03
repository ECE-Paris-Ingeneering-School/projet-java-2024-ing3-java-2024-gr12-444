package controller;

import model.*;

import java.util.ArrayList;

public class Controller {

    private final DAOUser daoUser;
    private final DAOFilm daoFilm;

    public Controller() {
        this.daoUser = new DAOUser();
        this.daoFilm = new DAOFilm();
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

    public ArrayList<Film> getList(){
        return daoFilm.getList();
    }

}
