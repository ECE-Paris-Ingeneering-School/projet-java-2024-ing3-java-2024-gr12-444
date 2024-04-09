package vue;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Card {
    private JButton buttonX;

    public Card(String titre, String time, String genre, String description, String classification, String date, String imgPath, JPanel panelX, Controller controller, int filmId) {
        panelX.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        panelX.setLayout(new BorderLayout());

        String Text = "<html>" + titre + "<br>" + date + "<br>" + genre + "<br>" + description + "<br>" + classification + "<br>" + time;
        JLabel label = new JLabel(Text);
        label.setIcon(new ImageIcon(imgPath));
        panelX.add(label, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonX = new JButton("Reserver");
        buttonX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reservation reservation = new Reservation(titre, time, genre, description, classification, date, imgPath, controller, filmId);
            }
        });
        buttonPanel.add(buttonX);
        panelX.add(buttonPanel, BorderLayout.SOUTH); // Changer BorderLayout.EAST à BorderLayout.SOUTH
    }
}

