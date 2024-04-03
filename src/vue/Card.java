package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Card
{
    private JButton buttonX;
    public Card(String titre, String time,String genre, String description, String classification, String date, String imgPath ,JPanel panelX){
        // JPanel panelX = new JPanel();
        panelX.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        panelX.setLayout(new FlowLayout());
        String Text="<html>"+titre+"<br>"+date+"<br>"+genre+"<br>"+description+"<br>"+classification+"<br>"+time;
        JLabel label=new JLabel(Text);
        label.setIcon(new ImageIcon(imgPath));
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelX.add(label);
        buttonX = new JButton("Reserver");
        buttonX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(label, "Reserver");
            }
        });
        panelX.add(buttonX);
    }
}
