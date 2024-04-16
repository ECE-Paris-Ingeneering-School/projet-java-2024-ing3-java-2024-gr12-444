package vue;

import com.mysql.cj.util.StringInspector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public PaimentInterface(String titre, String nomSalle, String date, String heure, int seanceId) {
        setTitle("Interface de paiement - Séance de cinéma");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel ecran = new JPanel(new GridLayout(2, 1));
        JPanel panelX = new JPanel();
        String Text=
                "<html><h2>"+titre+"</h2><h3>"+nomSalle+"</h3><br>Heure de début :"+heure+"<br>Date :"+date+"<br>Seance ID :"+seanceId;

        JLabel label=new JLabel(Text);
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
                validatePayment();
            }
        });
        panel.add(validateButton, gbc);

        gbc.gridy = 8;
        totalLabel = new JLabel();
        panel.add(totalLabel, gbc);

        ecran.add(panel);
        add(ecran);

        setVisible(true);
    }

    private void updateTotalPrice() {
        try {
            int adultTickets = Integer.parseInt(adultTicketsField.getText());
            int childTickets = Integer.parseInt(childTicketsField.getText());
            int seniorTickets = Integer.parseInt(seniorTicketsField.getText());

            double totalPrice = calculateTotalPrice(adultTickets, childTickets, seniorTickets);
            totalLabel.setText("Total à payer : " + totalPrice + " euros");
        } catch (NumberFormatException ex) {
            totalLabel.setText("Veuillez saisir un nombre valide de billets.");
        }
    }

    private double calculateTotalPrice(int adultTickets, int childTickets, int seniorTickets) {
        // Prix des billets
        double adultPrice = 10.0;
        double childPrice = 7.0;
        double seniorPrice = 8.0;

        // Calcul du total
        return (adultTickets * adultPrice) + (childTickets * childPrice) + (seniorTickets * seniorPrice);
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
        JOptionPane.showMessageDialog(this, "Paiement validé !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


}
