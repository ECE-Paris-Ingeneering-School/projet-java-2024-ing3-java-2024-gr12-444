package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.Controller;
import model.Film;
import model.User;

/**
 * Classe qui représente l'interface graphique de l'accueil pour les membres connectés
 */
public class AccueilMembre extends JFrame implements ActionListener {

    private Controller controller;
    private User user;
    private JLabel titre, userLabel;
    private JButton quitter, disconnect, profil;

    private ArrayList<Film> list;

    /**
     * Constructeur de la classe AccueilMembre
     *
     * @param controller Le contrôleur de l'application
     * @param user       L'utilisateur connecté
     */
    public AccueilMembre(Controller controller, User user) {
        this.controller = controller;
        this.user = user;

        controller.film();
        list = controller.getListFilm();

        // Configuration de la fenêtre
        setTitle("AccueilMembre");
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
        userLabel = new JLabel(user.nom + " " + user.prenom);
        userLabel.setFont(new Font("Serif", Font.BOLD, 20));
        userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        titrePanel.add(userLabel);
        add(titrePanel, BorderLayout.NORTH);

        // Boutons de déconnexion, quitter et profil
        JPanel menu = new JPanel(new GridLayout(0, 3, 10, 3));
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        quitter = new JButton("Quitter");
        quitter.addActionListener(this);
        menu.add(quitter);
        disconnect = new JButton("Se déconnecter");
        disconnect.addActionListener(this);
        menu.add(disconnect);
        profil = new JButton("Votre Profil");
        profil.addActionListener(this);
        menu.add(profil);
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
            Card card = new Card(user, list.get(i).getTitre(), list.get(i).getTime(), list.get(i).getGenre(), list.get(i).getDescription(), list.get(i).getClassification(), list.get(i).getDate(), list.get(i).getPoster(), panelt, this.controller, list.get(i).getFilmId());
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
        } else if (e.getSource() == profil) {
            list.clear();
            Profil profil = new Profil(controller, user);
        }
    }
}