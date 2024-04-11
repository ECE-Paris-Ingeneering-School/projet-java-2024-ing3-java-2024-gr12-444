package vue;

import controller.Controller;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI extends JFrame {
    private JPanel menuPanel;
    private JLabel tfMenu;
    private JButton btnConnecter;
    private JButton btnInscrire;
    private JButton btnInvite;
    private Controller controller;

    public MenuGUI(Controller controller) {
        this.controller = controller;
        setTitle("Menu");
        setContentPane(menuPanel);
        setMinimumSize(new Dimension(600, 400));
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //BOUTON SE CONNECTER
        btnConnecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                ConnexionGUI connexionGUI = new ConnexionGUI(null, controller);
                User user = connexionGUI.user;
                if (user != null) {
                    System.out.println("Connexion réussie pour : " + user.prenom + " " + user.nom);
                    System.out.println("Age : " + user.age);
                    System.out.println("Email : " + user.mail);
                    System.out.println("Mot De Passe : " + user.motDePasse);
                } else {
                    System.out.println("Connexion annulée");
                }
            }
        });
        //BOUTON S'INSCRIRE
        btnInscrire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                InscriptionGUI inscriptionGUI = new InscriptionGUI(null, controller);
                User user = inscriptionGUI.user;
                if (user != null) {
                    System.out.println("Inscription réussie pour : " + user.prenom);
                } else {
                    System.out.println("Inscription annulée");
                }
            }
        });
        //BOUTON INVITE
        btnInvite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AccueilInvite acceuil = new AccueilInvite(controller);
            }
        });
        setVisible(true);
    }
}
