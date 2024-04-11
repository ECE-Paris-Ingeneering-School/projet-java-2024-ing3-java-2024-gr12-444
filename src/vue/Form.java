package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.*;

public class Form extends JFrame implements ActionListener {
    private JButton connexionButton, inscriptionButton, inviteButton;
    private Controller controller;

    public Form(Controller controller) {
        this.controller = controller;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 0, 5, 10));

        connexionButton = new JButton("Connexion");
        inscriptionButton = new JButton("Inscription");
        inviteButton = new JButton("Invité");

        connexionButton.addActionListener(this);
        inscriptionButton.addActionListener(this);
        inviteButton.addActionListener(this);

        panel.add(connexionButton);
        panel.add(inscriptionButton);
        panel.add(inviteButton);

        add(panel, BorderLayout.CENTER);

        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connexionButton) {
            //LoginInterface loginInterface = new LoginInterface(this.controller);
            ConnexionGUI connexionGUI = new ConnexionGUI(null, controller);
            User user = connexionGUI.user;
            if (user != null){
                System.out.println("Connexion réussie pour : " + user.prenom + " " + user.nom);
                System.out.println("Age : " + user.age);
                System.out.println("Email : " + user.mail);
                System.out.println("Mot De Passe : " + user.motDePasse);
            }
            else {
                System.out.println("Connexion annulée");
            }
            this.dispose();
        } else if (e.getSource() == inscriptionButton) {
            InscriptionGUI inscription = new InscriptionGUI(null, controller);
            User user = inscription.user;
            if (user != null) {
                System.out.println("Inscription réussie pour : " + user.prenom);
            }
            else {
                System.out.println("Inscription annulée");
            }
            this.dispose();
        } else if (e.getSource() == inviteButton) {
            AccueilInvite acceuil = new AccueilInvite(this.controller);
            this.dispose();
        }
    }

}