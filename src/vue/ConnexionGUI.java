package vue;

import controller.Controller;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnexionGUI extends JDialog {
    public User user;
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

                User user = controller.connect(email, motDePasse);

                if (user != null) {
                    System.out.println("Connexion réussie pour : " + user.prenom + " " + user.nom);
                    System.out.println("Age : " + user.age);
                    System.out.println("Email : " + user.mail);
                    System.out.println("Mot De Passe : " + user.motDePasse);
                    //EMPLOYE
                    if (Integer.parseInt(user.typeMembre) == 0) {
                        new AccueilEmploye(controller);
                    }
                    //MEMBRE
                    else {
                        new AccueilMembre(controller);
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
                System.out.println("Connexion annulée");
                dispose();
                MenuGUI menuGUI = new MenuGUI(controller);
            }
        });

        setVisible(true);
    }
}