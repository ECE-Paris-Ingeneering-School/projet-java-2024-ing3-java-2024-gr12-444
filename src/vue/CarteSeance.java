package vue;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarteSeance {

    private JButton buttonX;
    public CarteSeance(String nom,int seanceId, int filmId, String titre ,int salleId,String heure, String date, int nbplace, JPanel panelX , Controller controller){


        String Text=
                "<html>"+nom+"<br>ID Seance :"+seanceId+"<br>Film ID :"+filmId+"<br>Salle ID :"+salleId+
                        "<br>Heure de début :"+heure+"<br>Date :"+date+"<br> Nombre de place:"+nbplace;

        JLabel label=new JLabel(Text);
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
