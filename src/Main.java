import controller.*;
import model.DAOFilm;
import model.DAOSeance;
import vue.*;
// import vue.Form;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        MenuGUI menuGUI = new MenuGUI(controller);
    }
}
