package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import controller.*;

public class LoginInterface extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private JButton loginButton;

    private Controller controller;

    public LoginInterface(Controller controller) {
        this.controller = controller;
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 30));
        add(panel);

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);

        usernameField = new JTextField();
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.addActionListener(this);
        panel.add(showPasswordCheckBox);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton);

        setResizable(false);
        setVisible(true);
    }

    private String username;
    private String password;

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {

            this.controller.setUsername(usernameField.getText());
            this.controller.setPassword(String.valueOf(passwordField.getPassword()));

            System.out.println("Username: " + this.controller.getUsername());
            System.out.println("Password: " + this.controller.getPassword());

            if (this.controller.verifUser()) {
                System.out.println("User found");
                redirectToHome();
            } else {
                System.out.println("User not found");
                retirectToForm();
            }
            this.dispose();
        } else if (e.getSource() == showPasswordCheckBox) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            if (checkBox.isSelected()) {
                passwordField.setEchoChar((char) 0); // Afficher le mot de passe
            } else {
                passwordField.setEchoChar('*'); // Masquer le mot de passe
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void redirectToHome() {
        Accueil acceuil = new Accueil(this.controller);
    }

    public void retirectToForm(){ Form form = new Form(controller);
    }
}
