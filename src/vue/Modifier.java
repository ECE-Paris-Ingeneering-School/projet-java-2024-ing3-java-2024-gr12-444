package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.*;
import model.Film;
import org.jdesktop.swingx.JXDatePicker;

/**
 * Classe qui représente l'interface graphique pour la modification des films
 */
public class Modifier extends JFrame implements ActionListener {

    private Controller controller;
    private JLabel titre;
    private JButton quitter, ajoutFilm, ajoutReduc, ajoutSeance;

    private JTextField tfidfilm, tfTitre, tfGenre, tfClassification, tfDescription, tfPoster;

    private JTextField tfIDSeanceReduc, tfReduc, tfidreduc;
    private JTextField tfFilmID, tfSalleID, tfidseance;

    private JXDatePicker datePicker;
    private JSpinner timeSpinner;

    private JXDatePicker datePicker2;
    private JSpinner timeSpinner2;


    private ArrayList<Film> list;


    /**
     * Constructeur de la classe Ajouter
     *
     * @param controller Le contrôleur de l'application
     */
    public Modifier(Controller controller) {
        this.controller = controller;
        controller.film();
        list = controller.getListFilm();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTitre());
        }

        // Configuration de la fenêtre
        setTitle("Modifier");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 1));

        //Titre
        JPanel titrePanel = new JPanel(new GridLayout(1, 2));
        titrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        titre = new JLabel("Modifier : ");
        titre.setFont(new Font("Serif", Font.BOLD, 32));
        titre.setHorizontalAlignment(SwingConstants.LEFT);
        titrePanel.add(titre);
        add(titrePanel, BorderLayout.NORTH);

        //Sections
        JPanel sections = new JPanel(new GridLayout(1, 3));

        //Première section
        JPanel sectionFilm = new JPanel(new GridBagLayout());
        JLabel titreSection1 = new JLabel("Modification d'un film :");

        JLabel idfilm = new JLabel("ID du film à modifier :");
        tfidfilm = new JTextField(10);

        JLabel titreFilm = new JLabel("Nouveau titre du film :");
        tfTitre = new JTextField(10);

        JLabel genreFilm = new JLabel("Nouveau genre du film :");
        tfGenre = new JTextField(10);

        JLabel classificationFilm = new JLabel("Nouvelle classification du film :");
        tfClassification = new JTextField(10);

        JLabel desciptionFilm = new JLabel("Nouvelle description du film :");
        tfDescription = new JTextField(10);

        JLabel dateFilm = new JLabel("Nouvelle date de sortie :");
        datePicker = new JXDatePicker();
        sectionFilm.add(dateFilm);
        sectionFilm.add(datePicker);

        JLabel dureeFilm = new JLabel("Nouvelle durée du film :");
        SpinnerDateModel spinnerModel = new SpinnerDateModel();
        timeSpinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(dateEditor);
        sectionFilm.add(dureeFilm);
        sectionFilm.add(timeSpinner);

        JLabel posterFilm = new JLabel("Nouveau poster du film :");
        tfPoster = new JTextField(10);

        JButton boutonSection1 = new JButton("Modification du film");
        boutonSection1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                modifierFilm();
                Modifier.this.dispose();
            }

        });
        GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.insets = new Insets(0, 0, 10, 0);
        sectionFilm.add(titreSection1, gridBagConstraints1);

        gridBagConstraints1.gridy = 1;
        sectionFilm.add(idfilm, gridBagConstraints1);
        gridBagConstraints1.gridy = 2;
        sectionFilm.add(tfidfilm, gridBagConstraints1);

        gridBagConstraints1.gridy = 3;
        sectionFilm.add(titreFilm, gridBagConstraints1);
        gridBagConstraints1.gridy = 4;
        sectionFilm.add(tfTitre, gridBagConstraints1);

        gridBagConstraints1.gridy = 5;
        sectionFilm.add(genreFilm, gridBagConstraints1);
        gridBagConstraints1.gridy = 6;
        sectionFilm.add(tfGenre, gridBagConstraints1);

        gridBagConstraints1.gridy = 7;
        sectionFilm.add(classificationFilm, gridBagConstraints1);
        gridBagConstraints1.gridy = 8;
        sectionFilm.add(tfClassification, gridBagConstraints1);

        gridBagConstraints1.gridy = 9;
        sectionFilm.add(desciptionFilm, gridBagConstraints1);
        gridBagConstraints1.gridy = 10;
        sectionFilm.add(tfDescription, gridBagConstraints1);

        gridBagConstraints1.gridy = 11;
        sectionFilm.add(dateFilm, gridBagConstraints1);
        gridBagConstraints1.gridy = 12;
        sectionFilm.add(datePicker, gridBagConstraints1);

        gridBagConstraints1.gridy = 13;
        sectionFilm.add(dureeFilm, gridBagConstraints1);
        gridBagConstraints1.gridy = 14;
        sectionFilm.add(timeSpinner, gridBagConstraints1);

        gridBagConstraints1.gridy = 15;
        sectionFilm.add(posterFilm, gridBagConstraints1);
        gridBagConstraints1.gridy = 16;
        sectionFilm.add(tfPoster, gridBagConstraints1);

        gridBagConstraints1.gridy = 17;
        sectionFilm.add(boutonSection1, gridBagConstraints1);
        sections.add(sectionFilm);

        //Deuxième section
        JPanel sectionReduc = new JPanel(new GridBagLayout());
        JLabel titreSection2 = new JLabel("Modification d'une reduction");
        JButton boutonSection2 = new JButton("Modification de la réduction");
        JLabel reducIDSeance = new JLabel("Nouveau ID de la seance pour la reduction :");
        tfIDSeanceReduc = new JTextField(10);
        JLabel pourcentReduc = new JLabel("Nouuveau pourcentage de la reduction :");
        tfReduc = new JTextField(10);
        JLabel idreduc = new JLabel("ID de la réduction à modifier : ");
        tfidreduc = new JTextField(10);
        boutonSection2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                modifierReduc();
                Modifier.this.dispose();
            }

        });
        GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.insets = new Insets(0, 0, 10, 0);
        sectionReduc.add(titreSection2, gridBagConstraints2);

        gridBagConstraints2.gridy = 1;
        sectionReduc.add(idreduc, gridBagConstraints2);
        gridBagConstraints2.gridy = 2;
        sectionReduc.add(tfidreduc, gridBagConstraints2);

        gridBagConstraints2.gridy = 3;
        sectionReduc.add(reducIDSeance, gridBagConstraints2);
        gridBagConstraints2.gridy = 4;
        sectionReduc.add(tfIDSeanceReduc, gridBagConstraints2);

        gridBagConstraints2.gridy = 5;
        sectionReduc.add(pourcentReduc, gridBagConstraints2);
        gridBagConstraints2.gridy = 6;
        sectionReduc.add(tfReduc, gridBagConstraints2);

        gridBagConstraints2.gridy = 7;
        sectionReduc.add(boutonSection2, gridBagConstraints2);
        sections.add(sectionReduc);

        //Troisième section
        JPanel sectionSeance = new JPanel(new GridBagLayout());
        JLabel titreSection3 = new JLabel("Modification d'une seance");
        JButton boutonSection3 = new JButton("Modification de la seance");

        JLabel idseance = new JLabel("ID de la séance à modifier :");
        tfidseance = new JTextField(10);

        JLabel filmID = new JLabel("Nouveau ID du film pour la séance :");
        tfFilmID = new JTextField(10);

        JLabel salleID = new JLabel("Nouveau ID de la salle pour la séance :");
        tfSalleID = new JTextField(10);

        JLabel dateSeance = new JLabel("Nouvelle date de la séance :");
        datePicker2 = new JXDatePicker();
        sectionSeance.add(dateSeance);
        sectionSeance.add(datePicker2);

        JLabel heureSeance = new JLabel("Nouvelle heure de début de la séance :");
        SpinnerDateModel spinnerModel2 = new SpinnerDateModel();
        timeSpinner2 = new JSpinner(spinnerModel2);
        JSpinner.DateEditor dateEditor2 = new JSpinner.DateEditor(timeSpinner2, "HH:mm:ss");
        timeSpinner2.setEditor(dateEditor2);
        sectionSeance.add(heureSeance);
        sectionSeance.add(timeSpinner2);

        boutonSection3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                modifierSeance();
                Modifier.this.dispose();
            }

        });
        GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.insets = new Insets(0, 0, 10, 0);
        sectionSeance.add(titreSection3, gridBagConstraints3);

        gridBagConstraints3.gridy = 1;
        sectionSeance.add(idseance, gridBagConstraints3);
        gridBagConstraints3.gridy = 2;
        sectionSeance.add(tfidseance, gridBagConstraints3);

        gridBagConstraints3.gridy = 3;
        sectionSeance.add(filmID, gridBagConstraints3);
        gridBagConstraints3.gridy = 4;
        sectionSeance.add(tfFilmID, gridBagConstraints3);

        gridBagConstraints3.gridy = 5;
        sectionSeance.add(salleID, gridBagConstraints3);
        gridBagConstraints3.gridy = 6;
        sectionSeance.add(tfSalleID, gridBagConstraints3);

        gridBagConstraints3.gridy = 7;
        sectionSeance.add(dateSeance, gridBagConstraints3);
        gridBagConstraints3.gridy = 8;
        sectionSeance.add(datePicker2, gridBagConstraints3);

        gridBagConstraints3.gridy = 9;
        sectionSeance.add(heureSeance, gridBagConstraints3);
        gridBagConstraints3.gridy = 10;
        sectionSeance.add(timeSpinner2, gridBagConstraints3);

        gridBagConstraints3.gridy = 11;
        sectionSeance.add(boutonSection3, gridBagConstraints3);
        sections.add(sectionSeance);

        add(sections, BorderLayout.CENTER);


        //Bouton Quitter
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

    /**
     * Méthode appelée lors de la modification d'un film
     */
    private void modifierFilm() {
        String idFilm = tfidfilm.getText();
        String titre = tfTitre.getText();
        String genre = tfGenre.getText();
        String classification = tfClassification.getText();
        String description = tfDescription.getText();
        String poster = tfPoster.getText();

        java.util.Date selectedDate = datePicker.getDate();
        java.util.Date selectedTime = (java.util.Date) timeSpinner.getValue();

        controller.modifierFilm(idFilm, titre, genre, classification, description, poster, selectedDate, selectedTime);
    }


    /**
     * Méthode appelée lors de la modification d'une réduction
     */
    private void modifierReduc() {

        String idReduc = tfidreduc.getText();
        String idSeance = tfIDSeanceReduc.getText();
        String reduc = tfReduc.getText();

        controller.modifierReduc(idReduc, idSeance, reduc);
    }


    /**
     * Méthode appelée lors de la modification d'une séance
     */
    private void modifierSeance() {
        String idSeance = tfidseance.getText();
        String idfilm = tfFilmID.getText();
        String idsalle = tfSalleID.getText();

        java.util.Date selectedDate2 = datePicker2.getDate();
        java.util.Date selectedTime2 = (java.util.Date) timeSpinner2.getValue();

        controller.modifierSeance(idSeance, idfilm, idsalle, selectedDate2, selectedTime2);
    }


    /**
     * Méthode de gestion des actions sur les composants de l'interface graphique
     * @param e L'événement à traiter
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitter) {
            this.dispose();
        }
    }
}