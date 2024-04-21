package vue;

import com.mysql.cj.Session;
import controller.Controller;
import model.Reduction;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class PaimentInterface extends JFrame implements ActionListener {
    private JTextField adultTicketsField;
    private JTextField childTicketsField;
    private JTextField seniorTicketsField;
    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;
    private JButton resetButton;
    private JButton validateButton;
    private JLabel totalLabel;

    private Controller controller;
    private User user;
    private String titre;
    private ArrayList<Reduction> reductions = new ArrayList<>();


    public PaimentInterface(User user, String titre, String nomSalle, String date, String heure, int seanceId, Controller controller) {
        this.controller = controller;
        this.user = user;
        this.titre = titre;
        reductions = controller.getReductions(seanceId);

        if (reductions != null && !reductions.isEmpty() && user != null) {
            System.out.println(reductions.get(0).getReduction());
        } else {
            reductions = null;
        }


        setTitle("Interface de paiement - Séance de cinéma");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel ecran = new JPanel(new GridLayout(2, 1));
        JPanel panelX = new JPanel();
        String Text;
        if (reductions != null && !reductions.isEmpty()) {
            Text = "<html><h2>" + titre + "</h2><h3>" + nomSalle + "</h3><br>Heure de début :" + heure + "<br>Date :" + date + "<br>Seance ID :" + seanceId + "<h3>  Réduction disponible : " + reductions.get(0).getReduction() + "%</h3></html>";
        } else {
            Text = "<html><h2>" + titre + "</h2><h3>" + nomSalle + "</h3><br>Heure de début :" + heure + "<br>Date :" + date + "<br>Seance ID :" + seanceId + "<h3>  Pas de réduction disponible</h3></html>";
        }
        JLabel label = new JLabel(Text);
        panelX.add(label);
        ecran.add(panelX);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Champs pour les billets
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Adultes (10 euros par billet):"), gbc);

        gbc.gridx = 1;
        adultTicketsField = new JTextField("0", 5);
        panel.add(adultTicketsField, gbc);

        gbc.gridx = 2;
        JButton adultMinusButton = new JButton("-");
        adultMinusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decreaseTicket(adultTicketsField);
                updateTotalPrice();
            }
        });
        panel.add(adultMinusButton, gbc);

        gbc.gridx = 3;
        JButton adultPlusButton = new JButton("+");
        adultPlusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                increaseTicket(adultTicketsField);
                updateTotalPrice();
            }
        });
        panel.add(adultPlusButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Enfants (7 euros par billet):"), gbc);

        gbc.gridx = 1;
        childTicketsField = new JTextField("0", 5);
        panel.add(childTicketsField, gbc);

        gbc.gridx = 2;
        JButton childMinusButton = new JButton("-");
        childMinusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decreaseTicket(childTicketsField);
                updateTotalPrice();
            }
        });
        panel.add(childMinusButton, gbc);

        gbc.gridx = 3;
        JButton childPlusButton = new JButton("+");
        childPlusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                increaseTicket(childTicketsField);
                updateTotalPrice();
            }
        });
        panel.add(childPlusButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Seniors (8 euros par billet):"), gbc);

        gbc.gridx = 1;
        seniorTicketsField = new JTextField("0", 5);
        panel.add(seniorTicketsField, gbc);

        gbc.gridx = 2;
        JButton seniorMinusButton = new JButton("-");
        seniorMinusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decreaseTicket(seniorTicketsField);
                updateTotalPrice();
            }
        });
        panel.add(seniorMinusButton, gbc);

        gbc.gridx = 3;
        JButton seniorPlusButton = new JButton("+");
        seniorPlusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                increaseTicket(seniorTicketsField);
                updateTotalPrice();
            }
        });
        panel.add(seniorPlusButton, gbc);

        // Champs pour les informations bancaires
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Numéro de carte:"), gbc);

        gbc.gridx = 1;
        cardNumberField = new JTextField(15);
        panel.add(cardNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Date d'expiration (MM/YY):"), gbc);

        gbc.gridx = 1;
        expiryDateField = new JTextField(7);
        panel.add(expiryDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("CVV:"), gbc);

        gbc.gridx = 1;
        cvvField = new JTextField(3);
        panel.add(cvvField, gbc);

        // Boutons pour réinitialiser et valider le paiement
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        resetButton = new JButton("Réinitialiser");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetFields();
                updateTotalPrice();
            }
        });
        panel.add(resetButton, gbc);

        gbc.gridy = 7;
        validateButton = new JButton("Valider le paiement");
        validateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!validatePaymentFields()) {
                    return;
                }
                validatePayment();
                //faire le mail
                if (user == null) {
                    controller.decreaseSeanceSeats(seanceId, nbBillet());
                    dispose();
                } else {
                    controller.reservation(user.userId, seanceId, nbBillet(), updateTotalPrice());
                    controller.decreaseSeanceSeats(seanceId, nbBillet());
                    dispose();
                }

            }
        });
        panel.add(validateButton, gbc);

        gbc.gridy = 8;
        totalLabel = new JLabel();
        panel.add(totalLabel, gbc);

        ecran.add(panel);
        add(ecran);

        if (user != null) {
            if (Integer.parseInt(user.getTypeMembre()) == 1) {
                adultTicketsField.setEnabled(false);
                adultMinusButton.setEnabled(false);
                adultPlusButton.setEnabled(false);
                seniorTicketsField.setEnabled(false);
                seniorMinusButton.setEnabled(false);
                seniorPlusButton.setEnabled(false);
            } else if (Integer.parseInt(user.getTypeMembre()) == 2) {
                adultTicketsField.setEnabled(false);
                adultMinusButton.setEnabled(false);
                adultPlusButton.setEnabled(false);
                childTicketsField.setEnabled(false);
                childMinusButton.setEnabled(false);
                childPlusButton.setEnabled(false);
            } else if (Integer.parseInt(user.getTypeMembre()) == 3) {
                seniorTicketsField.setEnabled(false);
                seniorMinusButton.setEnabled(false);
                seniorPlusButton.setEnabled(false);
                childTicketsField.setEnabled(false);
                childMinusButton.setEnabled(false);
                childPlusButton.setEnabled(false);
            }
        }

        setVisible(true);
    }

    private boolean validatePaymentFields() {
        if (cardNumberField.getText().isEmpty() || expiryDateField.getText().isEmpty() || cvvField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs !", "Erreur de paiement", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private double updateTotalPrice() {
        try {
            int adultTickets = Integer.parseInt(adultTicketsField.getText());
            int childTickets = Integer.parseInt(childTicketsField.getText());
            int seniorTickets = Integer.parseInt(seniorTicketsField.getText());

            double totalPrice = calculateTotalPrice(adultTickets, childTickets, seniorTickets);
            if (reductions != null && !reductions.isEmpty()) {
                totalPrice = totalPrice - (totalPrice * reductions.get(0).getReduction() / 100);
                String totalPrice2f = String.format("%.2f", totalPrice);
                totalLabel.setText("Total à payer : " + totalPrice2f + " euros, avec une réduction de " + reductions.get(0).getReduction() + "%");
            } else {
                String totalPrice2f = String.format("%.2f", totalPrice);
                totalLabel.setText("Total à payer : " + totalPrice2f + " euros");
            }
            return totalPrice;
        } catch (NumberFormatException ex) {
            totalLabel.setText("Veuillez saisir un nombre valide de billets.");
        }
        return 0;
    }

    private int nbBillet() {
        int adultTickets = Integer.parseInt(adultTicketsField.getText());
        int childTickets = Integer.parseInt(childTicketsField.getText());
        int seniorTickets = Integer.parseInt(seniorTicketsField.getText());

        int nbBillet = adultTickets + childTickets + seniorTickets;
        return nbBillet;
    }

    private double calculateTotalPrice(int adultTickets, int childTickets, int seniorTickets) {
        // Prix des billets
        double adultPrice = 10.0;
        double childPrice = 7.0;
        double seniorPrice = 8.0;

        // Calcul du total
        return (adultTickets * adultPrice) + (childTickets * childPrice * 0.8) + (seniorTickets * seniorPrice * 0.85);
    }

    private void increaseTicket(JTextField field) {
        int currentTickets = Integer.parseInt(field.getText());
        field.setText(String.valueOf(currentTickets + 1));
    }

    private void decreaseTicket(JTextField field) {
        int currentTickets = Integer.parseInt(field.getText());
        if (currentTickets > 0) {
            field.setText(String.valueOf(currentTickets - 1));
        }
    }

    private void resetFields() {
        adultTicketsField.setText("0");
        childTicketsField.setText("0");
        seniorTicketsField.setText("0");
        cardNumberField.setText("");
        expiryDateField.setText("");
        cvvField.setText("");
        totalLabel.setText("");
    }

    private void validatePayment() {
        // ajouter a la database  !!

        if (user != null) {
            sendConfirmationEmail(user);
        }

        JOptionPane.showMessageDialog(this, "Paiement validé !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    private void sendConfirmationEmail(User user) {
        String email = user.mail;
        String motDePasse = user.motDePasse;

        String senderEmail = "cinemadegrenelle@gmail.com";
        String senderPassword = "fxekozuylzptyedf";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        javax.mail.Session mailSession = javax.mail.Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(senderEmail, senderPassword);
            }
        });


        try {
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Confirmation de paiement");

            String filmName = titre;
            double totalPrice = updateTotalPrice();
            MimeMessage confirmationMail = draftEmail(user, filmName, totalPrice, mailSession);

            Transport.send(confirmationMail);

            System.out.println("E-mail de confirmation envoyé avec succès à " + email);
        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi de l'e-mail de confirmation : " + e.getMessage());
        }
    }

    private MimeMessage draftEmail(User user, String filmName, double totalPrice, javax.mail.Session session) throws MessagingException {
        String[] emailRecipients = {user.mail};
        String emailSubject = "Confirmation de paiement";
        String emailBody = "Bonjour " + user.prenom + ",\n\nVotre paiement a été validé avec succès.\n\n";
        emailBody += "Voici les détails de votre réservation :\n";
        emailBody += "- Film : " + filmName + "\n";
        emailBody += "- Prix total : " + totalPrice + " euros\n\n";
        emailBody += "Merci d'avoir choisi notre service de réservation en ligne. Nous sommes impatients de vous accueillir au cinéma !";

        MimeMessage mimeMessage = new MimeMessage(session);

        for (String recipient : emailRecipients) {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        }
        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "text/plain");

        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);

        mimeMessage.setContent(multiPart);
        return mimeMessage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


}
