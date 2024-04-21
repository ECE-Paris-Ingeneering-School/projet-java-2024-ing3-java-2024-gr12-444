package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.Controller;
import model.Film;

/**
 * Classe qui représente l'interface graphique de l'accueil pour les invités
 */
public class AccueilInvite extends JFrame implements ActionListener {

    private Controller controller;
    private JLabel titre, user;
    private JButton quitter, disconnect, connect;

    private ArrayList<Film> list;

    /**
     * Constructeur de la classe AccueilInvite
     *
     * @param controller Le contrôleur de l'application
     */
    public AccueilInvite(Controller controller) {
        this.controller = controller;
        controller.film();
        list = controller.getListFilm();

        // Configuration de la fenêtre
        setTitle("Accueil");
        setSize(1600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 1));

        // Titre et nom de l'utilisateur
        JPanel titrePanel = new JPanel(new GridLayout(1, 2));
        titrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        titre = new JLabel("Cinema de Grenelle");
        titre.setFont(new Font("Serif", Font.BOLD, 32));
        titre.setHorizontalAlignment(SwingConstants.LEFT);
        titrePanel.add(titre);
        user = new JLabel("Compte invité");
        user.setFont(new Font("Serif", Font.BOLD, 20));
        user.setHorizontalAlignment(SwingConstants.RIGHT);
        titrePanel.add(user);
        add(titrePanel, BorderLayout.NORTH);

        // Boutons de déconnexion, quitter et se connecter
        JPanel menu = new JPanel(new GridLayout(0, 3, 10, 3));
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        quitter = new JButton("Quitter");
        quitter.addActionListener(this);
        menu.add(quitter);
        disconnect = new JButton("Se déconnecter du compte invité");
        disconnect.addActionListener(this);
        menu.add(disconnect);
        connect = new JButton("Se connecter");
        connect.addActionListener(this);
        menu.add(connect);
        add(menu, BorderLayout.SOUTH);

        // Sections pour afficher les films
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(list.size(), 1));
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));

        JScrollPane scrollPanel = new JScrollPane(panel);
        scrollPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        add(scrollPanel, BorderLayout.CENTER);

        // Affichage des films
        for (int i = 0; i < list.size(); i++) {
            JPanel panelt = new JPanel();
            Card card = new Card(null, list.get(i).getTitre(), list.get(i).getTime(), list.get(i).getGenre(), list.get(i).getDescription(), list.get(i).getClassification(), list.get(i).getDate(), list.get(i).getPoster(), panelt, this.controller, list.get(i).getFilmId());
            panel.add(panelt);
        }

        setVisible(true);
    }

    /**
     * Méthode de gestion des actions sur les composants de l'interface graphique
     *
     * @param e L'événement à traiter
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitter) {
            this.dispose();
        } else if (e.getSource() == disconnect) {
            this.dispose();
            list.clear();
            MenuGUI menuGUI = new MenuGUI(controller);
        } else if (e.getSource() == connect) {
            this.dispose();
            list.clear();
            ConnexionGUI connexionGUI = new ConnexionGUI(null, this.controller);
        }
    }
}