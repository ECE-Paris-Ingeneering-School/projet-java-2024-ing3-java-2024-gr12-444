package vue;

import controller.Controller;

import model.Seance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Reservation extends JFrame implements ActionListener{
    private JLabel titrePage, user, sectionChoix;
    private Controller controller;
    private JButton close;
    private ArrayList<Seance> list;


    public Reservation(String titre, String time, String genre, String description, String classification, String date, String imgPath, Controller controller, int filmId){
        this.controller = controller;
        controller.setFilmid(filmId);

        controller.seance();



        list = controller.getListSeance();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("seanceid");
            System.out.println(list.get(i).getSeanceId());
        }





        setTitle("Reservation");
        setSize(800, 600);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 1));

        //titre et user
        JPanel titrePanel = new JPanel(new GridLayout(1, 2));
        titrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        titrePage = new JLabel("Cinema de Grenelle");
        titrePage.setFont(new Font("Serif", Font.BOLD, 32));
        titrePage.setHorizontalAlignment(SwingConstants.LEFT);
        titrePanel.add(titrePage);
        user = new JLabel(controller.getUsername());
        user.setFont(new Font("Serif", Font.BOLD, 20));
        user.setHorizontalAlignment(SwingConstants.RIGHT);
        titrePanel.add(user);
        add(titrePanel, BorderLayout.NORTH);

        JPanel menu = new JPanel(new GridLayout(0, 3, 10, 3));
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        close = new JButton("Quitter");
        close.addActionListener(this);
        menu.add(close);
        add(menu, BorderLayout.SOUTH);

        JPanel grille = new JPanel(new GridLayout(2,0));
        grille.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        //Rappel du film
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));

        String Text="<html>"+filmId+"<br>"+titre+"<br>"+date+"<br>"+genre+"<br>"+description+"<br>"+classification+"<br>"+time;
        JLabel label=new JLabel(Text);
        label.setIcon(new ImageIcon(imgPath));
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.add(label);
        grille.add(panel);

        //Choix de la seance
        JPanel choixSeance = new JPanel(new BorderLayout());
        JPanel panel1 = new JPanel(new GridLayout(1, list.size()));
        panel1.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));

        for (int i = 0; i < list.size(); i++) {
            JPanel panelX = new JPanel();
            controller.getSalle();
            controller.setSalleid(list.get(i).getSalleID());
            System.out.println(controller.getSalleidSalle());
            controller.salle();
            System.out.println(controller.getSalle().getNom());

            CarteSeance carteSeance= new CarteSeance(controller.getSalle().getNom(),list.get(i).getSeanceId(),list.get(i).getFilmId(),titre,list.get(i).getSalleID(), list.get(i).getHeureDeDebut(), list.get(i).getDate(), list.get(i).getNbplace(), panelX, this.controller );
            panel1.add(panelX);
        }
//        String Text1=
//                "<html><b>Choix seance</b><br>ID Seance :"+this.controller.getSeance().getSeanceId()+"<br>Film ID :"+this.controller.getSeance().getFilmId()+"<br>Nom du film :"+titre+"<br>Salle ID :"+this.controller.getSeance().getSalleID()+
//                "<br>Heure de début :"+this.controller.getSeance().getHeureDeDebut()+"<br>Date :"+this.controller.getSeance().getDate()+"<br> Nombre de place:"+this.controller.getSeance().getNbplace();
//
//
//        JLabel label2=new JLabel(Text1);

        sectionChoix = new JLabel("Choix de votre Seance");
        sectionChoix.setFont(new Font("Serif", Font.BOLD, 22));
        sectionChoix.setHorizontalAlignment(SwingConstants.LEFT);
        choixSeance.add(sectionChoix, BorderLayout.NORTH);

        choixSeance.add(panel1, BorderLayout.CENTER);
        grille.add(choixSeance);
        add(grille, BorderLayout.CENTER);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == close) {
            list.clear();
            this.dispose();
        }

    }



}
