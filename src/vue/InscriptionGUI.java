package vue;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class InscriptionGUI extends JDialog {
    private JTextField tfPrenom;
    private JTextField tfNom;
    private JTextField tfAge;
    private JTextField tfMail;
    private JPasswordField pfMotDePasse;
    private JPasswordField pfConfirmerMotDePasse;
    private JButton btnConfirmer;
    private JButton btnAnnuler;
    private JPanel inscriptionPanel;
    private Controller controller;

    public InscriptionGUI(JFrame p, Controller controller) {
        super(p);
        this.controller = controller;
        setTitle("Créer un nouveau compte");
        setContentPane(inscriptionPanel);
        setMinimumSize(new Dimension(800, 600));
        setModal(true);
        setLocationRelativeTo(p);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //BOUTON CONFIRMER
        btnConfirmer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                inscriptionUser();
            }
        });
        //BOUTON ANNULER
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                MenuGUI menuGUI = new MenuGUI(null);
            }
        });

        setVisible(true);
    }

    private void inscriptionUser() {
        String prenom = tfPrenom.getText();
        String nom = tfNom.getText();
        String age = tfAge.getText();
        String mail = tfMail.getText();
        String motDePasse = String.valueOf(pfMotDePasse.getPassword());
        String confirmerMotDePasse = String.valueOf(pfConfirmerMotDePasse.getPassword());

        if (prenom.isEmpty() || nom.isEmpty() || age.isEmpty() || mail.isEmpty() || motDePasse.isEmpty() || confirmerMotDePasse.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Essayer encore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!motDePasse.equals(confirmerMotDePasse)) {
            JOptionPane.showMessageDialog(this, "Les mots de passe ne sont pas pareils", "Essayer encore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = ajouterUserToDatabase(prenom, nom, age, mail, motDePasse);
        if (user != null) {
            dispose();
            MenuGUI menuGUI = new MenuGUI(controller);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur pour s'inscrire", "Essayer encore", JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user;

    private User ajouterUserToDatabase(String prenom, String nom, String age, String mail, String motDePasse) {
        User user = null;

        try {
            String url = "jdbc:mysql://127.0.0.1:3308/projetjava";
            String username = "root";
            String password = "";

            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("connection success");

            Statement statement = conn.createStatement();
            String sql = "INSERT INTO user (Prenom, Nom, Age, Email, MotDePasse)" + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, prenom);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, age);
            preparedStatement.setString(4, mail);
            preparedStatement.setString(5, motDePasse);

            int verif = preparedStatement.executeUpdate();
            //Si les informations sont bien dans la bdd on remplit le user qui va etre return
            if (verif > 0) {
                user = new User();
                user.prenom = prenom;
                user.nom = nom;
                user.age = age;
                user.mail = mail;
                user.motDePasse = motDePasse;
            }

            //fermeture
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
