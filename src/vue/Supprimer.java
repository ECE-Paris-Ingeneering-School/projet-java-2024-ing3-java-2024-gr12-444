package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.*;
import model.Film;

/**
 * Classe qui représente l'interface graphique qui permet de supprimer des éléments
 */
public class Supprimer extends JFrame implements ActionListener {

    private Controller controller;
    private JLabel titre;
    private JButton quitter;
    private JTextField tfIDFilm;

    private ArrayList<Film> list;

    /**
     * Contrôleur de la classe Supprimer
     *
     * @param controller Le contrôleur de l'application
     */
    public Supprimer(Controller controller) {
        this.controller = controller;
        controller.film();
        list = controller.getListFilm();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTitre());
        }

        //Configuration de la fenêtre
        setTitle("Supprimer");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 1));

        //Titre et utilisateur
        JPanel titrePanel = new JPanel(new GridLayout(1, 2));
        titrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        titre = new JLabel("Supprimer : ");
        titre.setFont(new Font("Serif", Font.BOLD, 32));
        titre.setHorizontalAlignment(SwingConstants.LEFT);
        titrePanel.add(titre);
        add(titrePanel, BorderLayout.NORTH);



        JPanel sectionSupprimer = new JPanel(new GridBagLayout());
        JLabel titreSection = new JLabel("Supprimer un film : ");
        JButton boutonSection = new JButton("Suppression du film");
        JLabel idFilm = new JLabel("ID du film à supprimer :");
        tfIDFilm = new JTextField(10);
        boutonSection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                supprimerFilm();
                Supprimer.this.dispose();
            }

        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        sectionSupprimer.add(titreSection, gbc);

        gbc.gridy = 1;
        sectionSupprimer.add(idFilm, gbc);
        gbc.gridy = 2;
        sectionSupprimer.add(tfIDFilm, gbc);

        gbc.gridy = 3;
        sectionSupprimer.add(boutonSection, gbc);

        add(sectionSupprimer, BorderLayout.CENTER);

        //Deconection ou quitter etc

        JPanel menu = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        quitter = new JButton("Quitter");
        quitter.addActionListener(this);
        menu.add(quitter);

        add(menu, BorderLayout.SOUTH);



        setVisible(true);
    }

    private void supprimerFilm() {

        String idFilm = tfIDFilm.getText();

        controller.supprimerFilm(idFilm);


    }


    /**
     * Méthode appelée lorsqu'une action est effectuée
     *
     * @param e L'événement à traiter
     */

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitter) {
            this.dispose();
        }
    }
}
