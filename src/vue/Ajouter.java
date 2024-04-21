package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;

import org.jdesktop.swingx.JXDatePicker;


import controller.*;
import model.Film;

public class Ajouter extends JFrame implements ActionListener {

    private Controller controller;
    private JLabel titre;
    private JButton quitter, ajoutFilm, ajoutReduc, ajoutSeance;

    private JTextField tfTitre, tfGenre, tfClassification, tfDescription, tfPoster;

    private JTextField tfIDSeanceReduc, tfReduc;
    private JTextField tfFilmID, tfSalleID;

    private JXDatePicker datePicker;
    private JSpinner timeSpinner;

    private JXDatePicker datePicker2;
    private JSpinner timeSpinner2;


    private ArrayList<Film> list;


    public Ajouter(Controller controller) {
        this.controller = controller;
        controller.film();
        list = controller.getListFilm();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTitre());
        }


        setTitle("Ajouter");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 1));

        //titre et user
        JPanel titrePanel = new JPanel(new GridLayout(1, 2));
        titrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        titre = new JLabel("Ajouter : ");
        titre.setFont(new Font("Serif", Font.BOLD, 32));
        titre.setHorizontalAlignment(SwingConstants.LEFT);
        titrePanel.add(titre);

        add(titrePanel, BorderLayout.NORTH);

        //les sections
        JPanel sections = new JPanel(new GridLayout(1, 3));

        //première section
        JPanel sectionFilm = new JPanel(new GridBagLayout());
        JLabel titreSection1 = new JLabel("Ajout d'un film :");

        JLabel titreFilm = new JLabel("Titre du film :");
        tfTitre = new JTextField(10);

        JLabel genreFilm = new JLabel("Genre du film :");
        tfGenre = new JTextField(10);

        JLabel classificationFilm = new JLabel("Classification du film :");
        tfClassification = new JTextField(10);

        JLabel desciptionFilm = new JLabel("Description du film :");
        tfDescription = new JTextField(10);

        JLabel dateFilm = new JLabel("Date de sortie :");
        datePicker = new JXDatePicker();
        sectionFilm.add(dateFilm);
        sectionFilm.add(datePicker);

        JLabel dureeFilm = new JLabel("Durée du film :");
        SpinnerDateModel spinnerModel = new SpinnerDateModel();
        timeSpinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(dateEditor);
        sectionFilm.add(dureeFilm);
        sectionFilm.add(timeSpinner);

        JLabel posterFilm = new JLabel("Poster du film :");
        tfPoster = new JTextField(10);

        JButton boutonSection1 = new JButton("Ajout du film");
        boutonSection1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ajoutFilm();
                Ajouter.this.dispose();
            }

        });
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(0, 0, 10, 0);
        sectionFilm.add(titreSection1, gbc1);

        gbc1.gridy = 1;
        sectionFilm.add(titreFilm, gbc1);
        gbc1.gridy = 2;
        sectionFilm.add(tfTitre, gbc1);

        gbc1.gridy = 3;
        sectionFilm.add(genreFilm, gbc1);
        gbc1.gridy = 4;
        sectionFilm.add(tfGenre, gbc1);

        gbc1.gridy = 5;
        sectionFilm.add(classificationFilm, gbc1);
        gbc1.gridy = 6;
        sectionFilm.add(tfClassification, gbc1);

        gbc1.gridy = 7;
        sectionFilm.add(desciptionFilm, gbc1);
        gbc1.gridy = 8;
        sectionFilm.add(tfDescription, gbc1);

        gbc1.gridy = 9;
        sectionFilm.add(dateFilm, gbc1);
        gbc1.gridy = 10;
        sectionFilm.add(datePicker, gbc1);

        gbc1.gridy = 11;
        sectionFilm.add(dureeFilm, gbc1);
        gbc1.gridy = 12;
        sectionFilm.add(timeSpinner, gbc1);

        gbc1.gridy = 13;
        sectionFilm.add(posterFilm, gbc1);
        gbc1.gridy = 14;
        sectionFilm.add(tfPoster, gbc1);

        gbc1.gridy = 15;
        sectionFilm.add(boutonSection1, gbc1);
        sections.add(sectionFilm);

        //deuxième section
        JPanel sectionReduc = new JPanel(new GridBagLayout());
        JLabel titreSection2 = new JLabel("Ajout d'une reduction");
        JButton boutonSection2 = new JButton("Ajout de la réduction");
        JLabel reducIDSeance = new JLabel("ID de la seance pour la reduction :");
        tfIDSeanceReduc = new JTextField(10);
        JLabel pourcentReduc = new JLabel("Pourcentage de la reduction :");
        tfReduc = new JTextField(10);
        boutonSection2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ajoutReduc();
                Ajouter.this.dispose();
            }

        });
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.insets = new Insets(0, 0, 10, 0);
        sectionReduc.add(titreSection2, gbc2);

        gbc2.gridy = 1;
        sectionReduc.add(reducIDSeance, gbc2);
        gbc2.gridy = 2;
        sectionReduc.add(tfIDSeanceReduc, gbc2);

        gbc2.gridy = 3;
        sectionReduc.add(pourcentReduc, gbc2);
        gbc2.gridy = 4;
        sectionReduc.add(tfReduc, gbc2);

        gbc2.gridy = 5;
        sectionReduc.add(boutonSection2, gbc2);
        sections.add(sectionReduc);

        //troisième section
        JPanel sectionSeance = new JPanel(new GridBagLayout());
        JLabel titreSection3 = new JLabel("Ajout d'une seance");
        JButton boutonSection3 = new JButton("Ajout de la seance");

        JLabel filmID = new JLabel("ID du film pour la séance :");
        tfFilmID = new JTextField(10);

        JLabel salleID = new JLabel("ID de la salle pour la séance :");
        tfSalleID = new JTextField(10);

        JLabel dateSeance = new JLabel("Date de la séance :");
        datePicker2 = new JXDatePicker();
        sectionSeance.add(dateSeance);
        sectionSeance.add(datePicker2);

        JLabel heureSeance = new JLabel("Heure de début de la séance :");
        SpinnerDateModel spinnerModel2 = new SpinnerDateModel();
        timeSpinner2 = new JSpinner(spinnerModel2);
        JSpinner.DateEditor dateEditor2 = new JSpinner.DateEditor(timeSpinner2, "HH:mm:ss");
        timeSpinner2.setEditor(dateEditor2);
        sectionSeance.add(heureSeance);
        sectionSeance.add(timeSpinner2);

        boutonSection3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ajoutSeance();
                Ajouter.this.dispose();
            }

        });
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        gbc3.insets = new Insets(0, 0, 10, 0);
        sectionSeance.add(titreSection3, gbc3);

        gbc3.gridy = 1;
        sectionSeance.add(filmID, gbc3);
        gbc3.gridy = 2;
        sectionSeance.add(tfFilmID, gbc3);

        gbc3.gridy = 3;
        sectionSeance.add(salleID, gbc3);
        gbc3.gridy = 4;
        sectionSeance.add(tfSalleID, gbc3);

        gbc3.gridy = 5;
        sectionSeance.add(dateSeance, gbc3);
        gbc3.gridy = 6;
        sectionSeance.add(datePicker2, gbc3);

        gbc3.gridy = 7;
        sectionSeance.add(heureSeance, gbc3);
        gbc3.gridy = 8;
        sectionSeance.add(timeSpinner2, gbc3);

        gbc3.gridy = 9;
        sectionSeance.add(boutonSection3, gbc3);
        sections.add(sectionSeance);

        add(sections, BorderLayout.CENTER);


        //Deconection ou quitter etc
        JPanel menu = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        quitter = new JButton("Quitter");
        quitter.addActionListener(this);
        menu.add(quitter);

        add(menu, BorderLayout.SOUTH);

        //sections
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(list.size(), 1));
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));


        setVisible(true);
    }


    private void ajoutFilm() {
        String titre = tfTitre.getText();
        String genre = tfGenre.getText();
        String classification = tfClassification.getText();
        String description = tfDescription.getText();
        String poster = tfPoster.getText();

        java.util.Date selectedDate = datePicker.getDate();
        java.util.Date selectedTime = (java.util.Date) timeSpinner.getValue();

        controller.ajoutFilm(titre, genre, classification, description, poster, selectedDate, selectedTime);



    }


    private void ajoutReduc() {

        String idSeance = tfIDSeanceReduc.getText();
        String reduc = tfReduc.getText();

        controller.ajoutReduc(idSeance, reduc);


    }


    private void ajoutSeance() {
        String idfilm = tfFilmID.getText();
        String idsalle = tfSalleID.getText();

        java.util.Date selectedDate2 = datePicker2.getDate();
        java.util.Date selectedTime2 = (java.util.Date) timeSpinner2.getValue();

        controller.ajoutSeance(idfilm, idsalle, selectedDate2, selectedTime2);

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitter) {
            this.dispose();
        }

    }
}



