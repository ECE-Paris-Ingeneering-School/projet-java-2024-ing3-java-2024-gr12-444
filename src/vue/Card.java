package vue;

import controller.Controller;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qui représente une carte pour afficher les détails d'un film et permettre à l'utilisateur de réserver
 */
public class Card {
    private JButton buttonX;

    /**
     * Constructeur de la classe Card
     *
     * @param user           L'utilisateur actuel
     * @param titre          Le titre du film
     * @param time           La durée du film
     * @param genre          Le genre du film
     * @param description    La description du film
     * @param classification La classification du film
     * @param date           La date de sortie du film
     * @param imgPath        Le chemin de l'image du film
     * @param panelX         Le panneau où afficher la carte
     * @param controller     Le contrôleur de l'application
     * @param filmId         L'identifiant du film
     */
    public Card(User user, String titre, String time, String genre, String description, String classification, String date, String imgPath, JPanel panelX, Controller controller, int filmId) {
        // Mise en forme du panneau
        panelX.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        panelX.setLayout(new BorderLayout());

        // Mise en forme du texte à afficher
        String[] lines = description.split("\\.");
        StringBuilder miseEnPage = new StringBuilder("<html>");
        for (String line : lines) {
            miseEnPage.append(line).append("<br>");
        }

        String Text = "<html><h1>" + titre + "</h1><br>Date de Sortie : " + date + "<br><i>" + genre + "</i><br>Synopsis :<br>" + miseEnPage.toString() + "<br><strong>" + classification + "</strong><br>" + time;

        // Ajout du texte et de l'image au panneau
        JLabel label = new JLabel(Text);
        label.setIcon(new ImageIcon(imgPath));
        label.setVerticalAlignment(SwingConstants.TOP);
        panelX.add(label, BorderLayout.CENTER);

        // Ajout du bouton de réservation
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonX = new JButton("Reserver");

        buttonX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reservation reservation = new Reservation(user, titre, time, genre, description, classification, date, imgPath, controller, filmId);
            }
        });
        buttonPanel.add(buttonX);
        panelX.add(buttonPanel, BorderLayout.SOUTH);
    }
}