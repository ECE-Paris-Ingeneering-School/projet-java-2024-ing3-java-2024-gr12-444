package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.*;
import model.Film;

/**
 * Classe qui représente l'interface graphique pour la modification des films
 */
public class Modifier extends JFrame implements ActionListener {

    private Controller controller;
    private JLabel titre;
    private JButton quitter;

    private ArrayList<Film> list;

    /**
     * Constructeur de la classe Modifier
     *
     * @param controller Le contrôleur de l'application
     */
    public Modifier(Controller controller) {
        this.controller = controller;
        controller.film();
        list = controller.getListFilm();

        // Configuration de la fenêtre
        setTitle("Modifier");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 1));

        // Titre
        JPanel titrePanel = new JPanel(new GridLayout(1, 2));
        titrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        titre = new JLabel("Modifier : ");
        titre.setFont(new Font("Serif", Font.BOLD, 32));
        titre.setHorizontalAlignment(SwingConstants.LEFT);
        titrePanel.add(titre);
        add(titrePanel, BorderLayout.NORTH);

        // Menu
        JPanel menu = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        quitter = new JButton("Quitter");
        quitter.addActionListener(this);
        menu.add(quitter);
        add(menu, BorderLayout.SOUTH);

        // Sections
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(list.size(), 1));
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));
        JScrollPane scrollPanel = new JScrollPane(panel);
        scrollPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        add(scrollPanel, BorderLayout.CENTER);

        setVisible(true);
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