package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.*;
import model.Film;

public class AccueilUser extends JFrame implements ActionListener {

    private Controller controller;
    private JLabel titre, user;
    private JButton buttonX, buttonY, buttonZ;
    private JButton quitter, disconnect, plus;

    private ArrayList<Film> list;


    public AccueilUser(Controller controller) {
        this.controller = controller;
        controller.film();
        list = controller.getList();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTitre());
        }


        setTitle("Ecran");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 1));

        //titre et user
        JPanel titrePanel = new JPanel(new GridLayout(1, 2));
        titrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        titre = new JLabel("Cinema de Grenelle");
        titre.setFont(new Font("Serif", Font.BOLD, 32));
        titre.setHorizontalAlignment(SwingConstants.LEFT);
        titrePanel.add(titre);
        user = new JLabel(controller.getUsername());
        user.setFont(new Font("Serif", Font.BOLD, 20));
        user.setHorizontalAlignment(SwingConstants.RIGHT);
        titrePanel.add(user);
        add(titrePanel, BorderLayout.NORTH);

        //Deconection ou quitter etc
        JPanel menu = new JPanel(new GridLayout(0, 3, 10, 3));
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        quitter = new JButton("Quitter");
        quitter.addActionListener(this);
        menu.add(quitter);
        disconnect = new JButton("Se déconnecter");
        disconnect.addActionListener(this);
        menu.add(disconnect);
        plus = new JButton("+");
        plus.addActionListener(this);
        menu.add(plus);
        add(menu, BorderLayout.SOUTH);

        //sections
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(list.size(), 1));
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));

        JScrollPane scrollPanel = new JScrollPane(panel);
        scrollPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        add(scrollPanel, BorderLayout.CENTER);


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


        for (int i = 0; i < list.size(); i++) {
            JPanel panelt = new JPanel();
            Card card1 = new Card(list.get(i).getTitre(), list.get(i).getTime(), list.get(i).getGenre(), list.get(i).getDescription(), list.get(i).getClassification(), list.get(i).getDate(), list.get(i).getPoster(), panelt);
            panel.add(panelt);

        }


        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonX) {
            JOptionPane.showMessageDialog(this, "Reserver");
        } else if (e.getSource() == buttonY) {
            JOptionPane.showMessageDialog(this, "Reserver");
        } else if (e.getSource() == buttonZ) {
            JOptionPane.showMessageDialog(this, "Reserver");
        } else if (e.getSource() == quitter) {
            this.dispose();
        } else if (e.getSource() == disconnect) {
            this.dispose();
            //ça fontionne pas encore
            Form form = new Form(controller);

        }


    }
}
