package vue;

import controller.Controller;
import model.User;

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
            }
        });

        //BOUTON S'INSCRIRE
        btnInscrire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                InscriptionGUI inscriptionGUI = new InscriptionGUI(null, controller);
            }
        });
        //BOUTON INVITE
        btnInvite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AccueilInvite accueil = new AccueilInvite(controller);
            }
        });
        setVisible(true);
    }
}
