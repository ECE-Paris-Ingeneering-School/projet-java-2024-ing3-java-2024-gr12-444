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
    private JButton quitter;
    private ArrayList<Film> list;
    private ArrayList<Reservation> reservations;
    private JList<String> reservationsList;

    public Profil(Controller controller, User user) {
        this.controller = controller;
        this.user = user;
        controller.film();
        list = controller.getListFilm();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTitre());
        }


        setTitle("Profil");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 1));

        //titre et user
        JPanel titrePanel = new JPanel(new GridLayout(1, 2));
        titrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        titre = new JLabel("Votre Profil : ");
        titre.setFont(new Font("Serif", Font.BOLD, 32));
        titre.setHorizontalAlignment(SwingConstants.LEFT);
        titrePanel.add(titre);
        username = new JLabel(controller.getUsername());
        System.out.println(user);
        username.setFont(new Font("Serif", Font.BOLD, 20));
        username.setHorizontalAlignment(SwingConstants.RIGHT);
        titrePanel.add(username);
        add(titrePanel, BorderLayout.NORTH);

        //Deconection ou quitter etc
        JPanel menu = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        quitter = new JButton("Quitter votre profil");
        quitter.addActionListener(this);
        menu.add(quitter);
        add(menu, BorderLayout.SOUTH);
/*
        //sections
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(list.size(), 1));
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));
*/
        JPanel reservationsPanel = new JPanel(new BorderLayout());
        reservationsPanel.setBorder(BorderFactory.createTitledBorder("Vos réservations"));
        reservationsList = new JList<>();
        JScrollPane scrollPanel = new JScrollPane(reservationsList);
        reservationsPanel.add(scrollPanel, BorderLayout.CENTER);
        add(reservationsPanel, BorderLayout.CENTER);

        reservations = controller.getReservations(user.userId);
        updateReservationList();

        //Panel 1 : premier film
        // JPanel panelX = new JPanel();
        // panelX.setBorder(BorderFactory.createLineBorder(Color.GREEN));
//
        // String urText2="<html>PANEL X You can<br>use basic HTML<br>in Swing<br> components,"
        //         +"Hope<br> I helped!";
        // JLabel lac2=new JLabel(urText2);
        // lac2.setIcon(new ImageIcon("images/test.jpeg"));
        // lac2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        // panelX.add(lac2);
        // buttonX = new JButton("Reserver");
        // buttonX.addActionListener(this);
        // panelX.add(buttonX);


        //ajout des panels de fiche de film
        // panel.add(panelX);


        // Card card1 = new Card("a", "a");


        /*for (int i = 0; i < list.size(); i++) {
            JPanel panelt = new JPanel();
            Card card = new Card(list.get(i).getTitre(), list.get(i).getTime(), list.get(i).getGenre(), list.get(i).getDescription(), list.get(i).getClassification(), list.get(i).getDate(), list.get(i).getPoster(), panelt, this.controller, list.get(i).getFilmId());
            panel.add(panelt);

        }*/


        setVisible(true);
    }

    private void updateReservationList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (model.Reservation reservation : reservations) {
            model.addElement(reservation.toString());
        }
        reservationsList.setModel(model);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitter) {
            this.dispose();
        }

    }
}
