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
 * Classe qui représente l'interface graphique de l'accueil pour les employés
 */
public class AccueilEmploye extends JFrame implements ActionListener {

    private Controller controller;
    private User user;
    private JLabel titre, userLabel;
    private JButton quitter, disconnect, profil, ajouter, supprimer, modifier;

    private ArrayList<Film> list;

    /**
     * Constructeur de la classe AccueilEmploye
     *
     * @param controller Le contrôleur de l'application
     * @param user       L'utilisateur connecté
     */
    public AccueilEmploye(Controller controller, User user) {
        this.controller = controller;
        this.user = user;
        controller.film();
        list = controller.getListFilm();

        // Configuration de la fenêtre
        setTitle("AccueilEmploye");
        setSize(1650, 800);
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

        // Boutons de déconnexion, profil et quitter
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

        // Menu pour les actions de l'employé
        JPanel menuEmploye = new JPanel(new GridLayout(3, 0, 3, 10));
        menuEmploye.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ajouter = new JButton("<html>Ajouter :<br>- Film <br>- Séance <br>- Réduction");
        ajouter.addActionListener(this);
        menuEmploye.add(ajouter);
        supprimer = new JButton("<html>Supprimer<br>Film");
        supprimer.addActionListener(this);
        menuEmploye.add(supprimer);
        modifier = new JButton("<html>Modifier :<br>- Film <br>- Séance <br>- Réduction");
        modifier.addActionListener(this);
        menuEmploye.add(modifier);
        add(menuEmploye, BorderLayout.WEST);

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
            Profil profil = new Profil(controller, user);
        } else if (e.getSource() == ajouter) {
            Ajouter ajouter = new Ajouter(controller);
        } else if (e.getSource() == modifier) {
            Modifier modifier = new Modifier(controller);
        } else if (e.getSource() == supprimer) {
            Supprimer supprimer = new Supprimer(controller);
        }
    }
}
