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

    /**
     * @param controller
     */
    public MenuGUI(Controller controller) {
        this.controller = controller;
        setTitle("Menu");
        setResizable(false);
        setSize(1024, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImageIcon imageIcon = new ImageIcon("images\\menu_background.jpg");
        JLabel background = new JLabel(imageIcon);
        background.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());

        menuPanel = new JPanel();
        menuPanel.setLayout(null);


        btnConnecter = new JButton("Se connecter");
        btnConnecter.setBounds(400, 300, 200, 50);
        addIconButton(btnConnecter, "images\\cle.png");
        btnConnecter.setFocusPainted(false);
        menuPanel.add(btnConnecter);

        btnInscrire = new JButton("S'inscrire");
        btnInscrire.setBounds(400, 400, 200, 50);
        addIconButton(btnInscrire, "images\\contrat.png");
        menuPanel.add(btnInscrire);

        btnInvite = new JButton("Invité");
        btnInvite.setBounds(400, 500, 200, 50);
        addIconButton(btnInvite, "images\\anonyme.png");
        menuPanel.add(btnInvite);

        menuPanel.add(background);
        background.setBounds(0, 0, getWidth(), getHeight());

        setContentPane(menuPanel);

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
                dispose();
                AccueilInvite accueil = new AccueilInvite(controller);
            }
        });
        setVisible(true);
    }

    private void addIconButton(JButton btn, String path) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        btn.setIcon(scaledIcon);
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);
    }
}
