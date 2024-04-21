import controller.*;
import vue.*;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        MenuGUI menuGUI = new MenuGUI(controller);
    }
}
