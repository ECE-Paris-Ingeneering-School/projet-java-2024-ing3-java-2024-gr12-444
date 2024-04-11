package vue;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ConnexionGUI extends JDialog {
    private JPanel panel1;
    private JTextField tfMail;
    private JPasswordField pfMotDePasse;
    private JButton btnConnecter;
    private JButton btnAnnuler;
    private JPanel connexionPanel;
    private Controller controller;

    public ConnexionGUI(JFrame p, Controller controller) {
        super(p);
        this.controller = controller;
        setTitle("Connexion");
        setContentPane(connexionPanel);
        setMinimumSize(new Dimension(800, 600));
        setModal(true);
        setLocationRelativeTo(p);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //BOUTON CONNECTER
        btnConnecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String email = tfMail.getText();
                String motDePasse = String.valueOf(pfMotDePasse.getPassword());

                user = getValidUser(email, motDePasse);

                if (user != null) {
                    //EMPLOYE
                    //faut changer le type jsp c quoi le bon
                    if (Integer.parseInt(user.typeMembre) == 0) {
                        new AccueilEmploye(controller);
                    }
                    //ENFANT
                    else if (Integer.parseInt(user.age) > 0 || Integer.parseInt(user.age) <= 14) {
                        new AccueilMembre(controller); //à changer
                    }
                    //REGULAR
                    else if (Integer.parseInt(user.age) >= 15 || Integer.parseInt(user.age) < 60) {
                        new AccueilMembre(controller); //à changer
                    }
                    //SENIOR
                    else {
                        new AccueilMembre(controller); //à changer
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(ConnexionGUI.this, "Email ou mot de passe invalide", "Essayer encore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //BOUTON ANNULER
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                MenuGUI menuGUI = new MenuGUI(null);
            }
        });

        setVisible(true);
    }

    public User user;

    private User getValidUser(String email, String motDePasse) {
        User user = null;

        try {
            String url = "jdbc:mysql://127.0.0.1:3308/projetjava";
            String username = "root";
            String password = "";

            Connection conn = DriverManager.getConnection(url, username, password);

            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM user WHERE Email=? AND MotDePasse=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, motDePasse);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.prenom = resultSet.getString("Prenom");
                user.nom = resultSet.getString("Nom");
                user.age = resultSet.getString("Age");
                user.mail = resultSet.getString("Email");
                user.motDePasse = resultSet.getString("motDePasse");
                user.typeMembre = resultSet.getString("typeMembre");
            }

            //fermeture
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
