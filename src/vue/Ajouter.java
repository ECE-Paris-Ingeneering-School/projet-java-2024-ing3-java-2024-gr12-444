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
    private JLabel titre, user;
    private JButton quitter, ajoutFilm, ajoutReduc, ajoutSeance;

    private JTextField tfTitre, tfGenre, tfClassification, tfDescription, tfPoster;

    private JXDatePicker datePicker;
    private JSpinner timeSpinner;


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
        user = new JLabel(controller.getUsername());
        user.setFont(new Font("Serif", Font.BOLD, 20));
        user.setHorizontalAlignment(SwingConstants.LEFT);
        titrePanel.add(user);
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
        boutonSection2.addActionListener(this);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.insets = new Insets(0, 0, 10, 0);
        sectionReduc.add(titreSection2, gbc2);
        gbc2.gridy = 1;
        sectionReduc.add(boutonSection2, gbc2);
        sections.add(sectionReduc);

        //troisième section
        JPanel sectionSeance = new JPanel(new GridBagLayout());
        JLabel titreSection3 = new JLabel("Ajout d'une seance");
        JButton boutonSection3 = new JButton("Ajout de la seance");
        boutonSection3.addActionListener(this);
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        gbc3.insets = new Insets(0, 0, 10, 0);
        sectionSeance.add(titreSection3, gbc3);
        gbc3.gridy = 1;
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

        Date date = new Date(selectedDate.getTime());
        Time duree = new Time(selectedTime.getTime());

        if (titre.isEmpty() || genre.isEmpty() || classification.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Essayer encore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (poster.isEmpty()){
            poster = "images/poster.jpg";
        }

        ajouterFilmToDatabase(titre, genre, classification, description, duree, date, poster);

    }
    public Film film;


    private void ajouterFilmToDatabase(String titre, String genre, String classification, String description, Time duree, Date date, String poster) {
        Film film = null;

        try {
            String url = "jdbc:mysql://127.0.0.1:3308/projetjava";
            String username = "root";
            String password = "";

            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("connection success");

            Statement statement = conn.createStatement();
            String sql = "INSERT INTO films (Titre, Genre, Classification, Description, Durée, DateDeSortie, Poster)" + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, titre);
            preparedStatement.setString(2, genre);
            preparedStatement.setString(3, classification);
            preparedStatement.setString(4, description);
            preparedStatement.setTime(5, duree);
            preparedStatement.setDate(6, date);
            preparedStatement.setString(7, poster);

            preparedStatement.executeUpdate();


            //fermeture
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitter) {
            this.dispose();
        }

    }
}



