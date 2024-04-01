package controller;

import model.DAOUser;

//import model.*;
public class Controller {

    private final DAOUser daoUser;

    public Controller() {
        this.daoUser = new DAOUser();
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

}
