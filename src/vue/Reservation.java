package vue;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Reservation extends JFrame implements ActionListener{
    private JLabel titrePage, user;
    private Controller controller;
    private JButton close;


    public Reservation(String titre, String time, String genre, String description, String classification, String date, String imgPath, Controller controller, int filmId){
        this.controller = controller;



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

        JPanel frame = new JPanel(new GridLayout(2,1));
        frame.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        //Rappel du film
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));

        String Text="<html>"+filmId+"<br>"+titre+"<br>"+date+"<br>"+genre+"<br>"+description+"<br>"+classification+"<br>"+time;
        JLabel label=new JLabel(Text);
        label.setIcon(new ImageIcon(imgPath));
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.add(label);
        frame.add(panel);

        //Choix de la seance
        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        String Text1="<html>"+this.controller.getSeanceId()+"<br>"+this.controller.getfilmid()+"<br>"+this.controller.getSalleId()+
                "<br>"+this.controller.getHeure()+"<br>"+this.controller.getDate()+"<br>"+this.controller.getNbPlace();

        JLabel label1=new JLabel("Choix seance");
        JLabel label2=new JLabel(Text1);


        add(frame, BorderLayout.CENTER);

        this.controller.setFilmid(filmId);
        //controller.seance();

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == close) {
            this.dispose();
        }

    }



}
