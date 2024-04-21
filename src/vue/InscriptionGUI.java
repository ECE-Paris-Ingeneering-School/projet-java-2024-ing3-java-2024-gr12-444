package vue;

import controller.Controller;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qui représente l'interface graphique qui permet à un nouvel utilisateur de créer un compte dans l'application
 */
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

    /**
     * Contrôleur de la classe InscriptionGUI
     *
     * @param p          La fenêtre parente à laquelle cette boîte de dialogue est attachée
     * @param controller Le contrôleur de l'application
     */
    public InscriptionGUI(JFrame p, Controller controller) {
        super(p);
        this.controller = controller;

        //Configuration de la fenêtre
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
                registerUser();
            }
        });

        //BOUTON ANNULER
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Inscription annulée");
                dispose();
                MenuGUI menuGUI = new MenuGUI(controller);
            }
        });

        setVisible(true);
    }

    /**
     * Méthode qui enregistre un nouvel utilisateur
     */
    private void registerUser() {
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

        User user = controller.registerUser(prenom, nom, age, mail, motDePasse);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Inscription réussie pour : " + user.prenom, "Succès", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            MenuGUI menuGUI = new MenuGUI(controller);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur pour s'inscrire", "Essayer encore", JOptionPane.ERROR_MESSAGE);
        }
    }
}