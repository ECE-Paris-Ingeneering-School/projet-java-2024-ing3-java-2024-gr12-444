package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JFrame implements ActionListener {
    private JButton connexionButton, inscriptionButton, inviteButton;

    public Form() {
        // Set up the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel =new JPanel(new GridLayout(3,0,5,10));

        // Create the buttons
        connexionButton = new JButton("Connexion");
        inscriptionButton = new JButton("Inscription");
        inviteButton = new JButton("Invité");

        // Add action listeners
        connexionButton.addActionListener(this);
        inscriptionButton.addActionListener(this);
        inviteButton.addActionListener(this);

        // Add the buttons to the frame
        panel.add(connexionButton);
        panel.add(inscriptionButton);
        panel.add(inviteButton);

        add(panel, BorderLayout.CENTER);

        setResizable(false);
        // Set the frame visible
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connexionButton) {
            LoginInterface loginInterface= new LoginInterface();
            this.dispose();
            // Code for connexion form
            //JOptionPane.showMessageDialog(this, "Connexion form displayed.");
        } else if (e.getSource() == inscriptionButton) {
            Inscription inscription = new Inscription();
            this.dispose();
            // Code for inscription form
            //JOptionPane.showMessageDialog(this, "Inscription form displayed.");
        }else if (e.getSource() == inviteButton) {
            Acceuil acceuil = new Acceuil();
            this.dispose();
            // Code for inscription form
            //JOptionPane.showMessageDialog(this, "Inscription form displayed.");
        }
    }


}