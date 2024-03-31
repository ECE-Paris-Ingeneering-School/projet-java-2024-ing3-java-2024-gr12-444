package controller;

import model.DAOUser;
import vue.*;
//import model.*;
public class Controller {

    LoginInterface loginInterface;
    DAOUser daoUser;

    public Controller(){

    }
    public Controller(Form form){
        Form from = new Form();
    }

    public String username(){
        return loginInterface.getUsername();
    }
    public String password(){
        return loginInterface.getPassword();
    }
    public void pass(){
        System.out.println(password());
        System.out.println(username());
    }

    public void verifUser() {
        DAOUser daoUser= new DAOUser();
        daoUser.verifUser();
        System.out.println("user exist");
    }
}
