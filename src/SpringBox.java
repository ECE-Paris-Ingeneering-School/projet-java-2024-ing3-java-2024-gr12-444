import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SpringBox extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private JButton loginButton;

    public SpringBox() {
        setTitle("Ecran");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);


        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new Inscription();
//            }
//        });
//    }
}
