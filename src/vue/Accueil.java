package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame implements ActionListener {

    private JLabel titre;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private JButton loginButton;


    public Accueil() {
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
        titrePanel.add(titre,BorderLayout.CENTER);
        add(titrePanel, BorderLayout.NORTH);

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
        JButton button2 = new JButton("Reserver");
        panelX.add(button2);


        //Panel 2 : 2eme film
        JPanel panelY = new JPanel();
        panelY.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        String urTextY="<html>PANEL Y You can<br>use basic HTML<br>in Swing<br> components,"
                +"Hope<br> I helped!";
        JLabel lacY=new JLabel(urTextY);
        lacY.setIcon(new ImageIcon("images/test.jpeg"));
        lacY.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelY.add(lacY);
        JButton buttonY = new JButton("Reserver");
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
        JButton buttonZ = new JButton("Reserver");
        panelZ.add(buttonZ);



        //ajout des panels de fiche de film
        panel.add(panelX);
        panel.add(panelY);
        panel.add(panelZ);


        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
