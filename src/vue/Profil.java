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

public class Profil extends JFrame implements ActionListener {

    private Controller controller;
    private User user;
    private JLabel titre, username;
    private JLabel prenom, nom, email, age;
    private JButton quitter;
    private ArrayList<Film> list;
    private ArrayList<Reservation> reservations;
    private JPanel reservationsPanel;

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
        username = new JLabel(controller.getUsername());
        username.setFont(new Font("Serif", Font.BOLD, 20));
        username.setHorizontalAlignment(SwingConstants.RIGHT);
        titrePanel.add(username);
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

    private void chargerReservations() {
        reservations = controller.getReservations(user.getUserId());
        for (Reservation reservation : reservations) {
            JPanel reservationPanel = createReservationPanel(reservation);
            reservationsPanel.add(reservationPanel);
        }
    }

    private JPanel createReservationPanel(Reservation reservation) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEtchedBorder());

        JLabel titre = new JLabel("Titre du film : " + reservation.getFilmTitle());
        JLabel date = new JLabel("Date de la séance : " + reservation.getDate());
        JLabel billets = new JLabel("Nombre de billets : " + reservation.getNbDeBillets());

        panel.add(titre, BorderLayout.NORTH);
        panel.add(date, BorderLayout.CENTER);
        panel.add(billets, BorderLayout.SOUTH);

        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitter) {
            this.dispose();
        }
    }
}
