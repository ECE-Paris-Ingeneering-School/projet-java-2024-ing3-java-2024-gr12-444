package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.*;

public class Accueil extends JFrame implements ActionListener {

    private Controller controller;
    private JLabel titre;
    private JButton buttonX,buttonY,buttonZ;
    private JButton quitter ,disconnect , plus;
    

    public Accueil(Controller controller) {
        this.controller=controller;
        setTitle("Ecran");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0,1));

        //titre
        JPanel titrePanel = new JPanel();
        titrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        titre = new JLabel("Cinema de Grenelle");
        titre.setFont(new Font("Serif", Font.BOLD, 32));
        titre.setHorizontalAlignment(SwingConstants.LEFT);
        titrePanel.add(titre);
        add(titrePanel, BorderLayout.NORTH);

        //Deconection ou quitter etc
        JPanel menu = new JPanel(new GridLayout(0,3, 10,3));
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        quitter = new JButton("Quitter");
        quitter.addActionListener(this);
        menu.add(quitter);
        disconnect= new JButton("Se déconnecter");
        disconnect.addActionListener(this);
        menu.add(disconnect);
        plus= new JButton("+");
        plus.addActionListener(this);
        menu.add(plus);
        add(menu, BorderLayout.SOUTH);

        //sections
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));

        JScrollPane scrollPanel = new JScrollPane(panel);
        scrollPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        add(scrollPanel, BorderLayout.CENTER);


        //Panel 1 : premier film
        JPanel panelX = new JPanel();
        panelX.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        String urText2="<html>PANEL X You can<br>use basic HTML<br>in Swing<br> components,"
                +"Hope<br> I helped!";
        JLabel lac2=new JLabel(urText2);
        lac2.setIcon(new ImageIcon("images/test.jpeg"));
        lac2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelX.add(lac2);
        buttonX = new JButton("Reserver");
        buttonX.addActionListener(this);
        panelX.add(buttonX);


        //Panel 2 : 2eme film
        JPanel panelY = new JPanel();
        panelY.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        String urTextY="<html>PANEL Y You can<br>use basic HTML<br>in Swing<br> components,"
                +"Hope<br> I helped!";
        JLabel lacY=new JLabel(urTextY);
        lacY.setIcon(new ImageIcon("images/test.jpeg"));
        lacY.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelY.add(lacY);
        buttonY = new JButton("Reserver");
        buttonY.addActionListener(this);
        panelY.add(buttonY);

        //Panel 3 : 3eme film
        JPanel panelZ = new JPanel();
        panelZ.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        String urTextZ="<html>PANEL Z You can<br>use basic HTML<br>in Swing<br> components,"
                +"Hope<br> I helped!";
        JLabel lacZ=new JLabel(urTextZ);
        lacZ.setIcon(new ImageIcon("images/test.jpeg"));
        lacZ.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelZ.add(lacZ);
        buttonZ = new JButton("Reserver");
        buttonZ.addActionListener(this);
        panelZ.add(buttonZ);



        //ajout des panels de fiche de film
        panel.add(panelX);
        panel.add(panelY);
        panel.add(panelZ);


        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonX) {
            JOptionPane.showMessageDialog(this, "Reserver");
        }
        else if (e.getSource() == buttonY) {
            JOptionPane.showMessageDialog(this, "Reserver");
        }
        else if (e.getSource() == buttonZ) {
            JOptionPane.showMessageDialog(this, "Reserver");
        }
        else if (e.getSource() == quitter) {
            this.dispose();
        }
        else if (e.getSource() == disconnect) {
            this.dispose();
            //ça fontionne pas encore
            Form form = new Form(controller);

        }


    }
}
