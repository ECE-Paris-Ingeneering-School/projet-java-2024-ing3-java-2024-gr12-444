package vue;

import controller.Controller;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Card {
    private JButton buttonX;

    public Card(User user, String titre, String time, String genre, String description, String classification, String date, String imgPath, JPanel panelX, Controller controller, int filmId) {
        panelX.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        panelX.setLayout(new BorderLayout());

        String[] lines= description.split("\\.");
        StringBuilder miseEnPage = new StringBuilder("<html>");
        for (String line : lines) {
            miseEnPage.append(line).append("<br>");
        }
        //miseEnPage.append("</html>");

        String Text = "<html><h1>" + titre + "</h1><br>Date de Sortie : " + date + "<br><i>" + genre + "</i><br>Synopsis :<br>" + miseEnPage.toString() + "<br><strong>" + classification + "</strong><br>" + time;
        JLabel label = new JLabel(Text);
        label.setIcon(new ImageIcon(imgPath));
        label.setVerticalAlignment(SwingConstants.TOP);
        panelX.add(label, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonX = new JButton("Reserver");
        buttonX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reservation reservation = new Reservation(user,titre, time, genre, description, classification, date, imgPath, controller, filmId);
            }
        });
        buttonPanel.add(buttonX);
        panelX.add(buttonPanel, BorderLayout.SOUTH);
    }
}

