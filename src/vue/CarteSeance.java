package vue;

import controller.Controller;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qui représente une carte pour afficher les détails d'une séance et permettre à l'utilisateur de réserver
 */
public class CarteSeance {

    private JButton buttonX;

    /**
     * Constructeur de la classe CarteSeance
     *
     * @param user       L'utilisateur actuel
     * @param nom        Le nom de la séance
     * @param seanceId   L'identifiant de la séance
     * @param filmId     L'identifiant du film associé à la séance
     * @param titre      Le titre du film associé à la séance
     * @param salleId    L'identifiant de la salle où se déroule la séance
     * @param heure      L'heure de début de la séance
     * @param date       La date de la séance
     * @param nbplace    Le nombre de places disponibles pour la séance
     * @param panelX     Le panneau où afficher la carte de séance
     * @param controller Le contrôleur de l'application
     */
    public CarteSeance(User user, String nom, int seanceId, int filmId, String titre, int salleId, String heure, String date, int nbplace, JPanel panelX, Controller controller) {

        // Création du texte à afficher
        String Text =
                "<html><h2>" + nom + "</h2>ID Seance :" + seanceId + "<br>Film ID :" + filmId + "<br>Salle ID :" + salleId +
                        "<br>Heure de début :" + heure + "<br>Date :" + date + "<br> Nombre de place:" + nbplace;

        JLabel label = new JLabel(Text);
        panelX.add(label);

        // Ajout du bouton de réservation
        buttonX = new JButton("Reserver");
        buttonX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PaimentInterface paimentInterface = new PaimentInterface(user, titre, nom, date, heure, seanceId, controller);
            }
        });
        panelX.add(buttonX);
    }
}