package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.*;
import model.Film;
import model.Reservation;
import model.User;

/**
 * Classe qui représente l'interface graphique qui permet à l'utilisateur de consulter son profil
 */
public class Profil extends JFrame implements ActionListener {

    private Controller controller;
    private User user;
    private JLabel titre;
    private JLabel prenom, nom, email, age;
    private JButton quitter;
    private ArrayList<Film> list;
    private ArrayList<Reservation> reservations;
    private JPanel reservationsPanel;

    /**
     * Contrôleur de la classe Profil
     *
     * @param controller Le contrôleur de l'application
     * @param user L'utilisateur dont le profil est affiché
     */
    public Profil(Controller controller, User user) {
        this.controller = controller;
        this.user = user;
        controller.film();
        list = controller.getListFilm();

        setTitle("Profil");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 1));

        //Titre et username
        JPanel titrePanel = new JPanel(new GridLayout(1, 2));
        titrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        titre = new JLabel("Votre Profil :");
        titre.setFont(new Font("Serif", Font.BOLD, 25));
        titre.setHorizontalAlignment(SwingConstants.LEFT);
        titrePanel.add(titre);
        add(titrePanel, BorderLayout.NORTH);

        //Affichage du prénom, nom, email et âge de l'utilisateur
        prenom = new JLabel("Prénom : " + user.getPrenom());
        prenom.setFont(new Font("Serif", Font.BOLD, 20));
        nom = new JLabel("Nom : " + user.getNom());
        nom.setFont(new Font("Serif", Font.BOLD, 20));
        email = new JLabel("Email : " + user.getMail());
        email.setFont(new Font("Serif", Font.BOLD, 10));
        age = new JLabel("Age : " + user.getAge());
        age.setFont(new Font("Serif", Font.BOLD, 20));
        titrePanel.add(prenom);
        titrePanel.add(nom);
        titrePanel.add(email);
        titrePanel.add(age);
        add(titrePanel, BorderLayout.NORTH);

        //Déconnexion
        JPanel menu = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        quitter = new JButton("Quitter votre profil");
        quitter.addActionListener(this);
        menu.add(quitter);
        add(menu, BorderLayout.SOUTH);

        //Réservations
        JPanel reservationsPanel = new JPanel();
        reservationsPanel.setLayout(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(reservationsPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Vos réservations"));
        add(scrollPane, BorderLayout.CENTER);
        this.reservationsPanel = reservationsPanel;

        chargerReservations();

        setVisible(true);
    }

    /**
     * Méthode qui charge les réservations de l'utilisateur
     */
    private void chargerReservations() {
        reservations = controller.getReservations(user.getUserId());
        for (Reservation reservation : reservations) {
            JPanel reservationPanel = createReservationPanel(reservation);
            reservationsPanel.add(reservationPanel);
        }
    }

    /**
     * Méthode qui crée un panneau représentant une réservation
     *
     * @param reservation La réservation à afficher
     * @return Le panneau représentant la réservation
     */
    private JPanel createReservationPanel(Reservation reservation) {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.setBorder(BorderFactory.createEtchedBorder());

        JLabel titreLabel = new JLabel("Titre du film : ");
        titreLabel.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel titre = new JLabel(reservation.getFilmTitle());
        titre.setFont(new Font("Serif", Font.PLAIN, 16));

        JLabel dateLabel = new JLabel("Date de la séance : ");
        dateLabel.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel date = new JLabel(reservation.getDate());
        date.setFont(new Font("Serif", Font.PLAIN, 16));

        JLabel billetsLabel = new JLabel("Nombre de billets : ");
        billetsLabel.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel billets = new JLabel(Integer.toString(reservation.getNbDeBillets()));
        billets.setFont(new Font("Serif", Font.PLAIN, 16));

        JLabel prixLabel = new JLabel("Prix : ");
        prixLabel.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel prix = new JLabel(String.format("%.2f", reservation.getPrix()) + " €");
        prix.setFont(new Font("Serif", Font.PLAIN, 16));

        panel.add(titreLabel);
        panel.add(titre);
        panel.add(dateLabel);
        panel.add(date);
        panel.add(billetsLabel);
        panel.add(billets);
        panel.add(prixLabel);
        panel.add(prix);

        return panel;
    }

    /**
     * Méthode appelée lorsqu'une action est effectuée
     *
     * @param e L'événement à traiter
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitter) {
            this.dispose();
        }
    }
}
