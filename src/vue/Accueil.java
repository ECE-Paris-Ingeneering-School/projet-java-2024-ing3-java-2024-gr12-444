package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame implements ActionListener {

    private JLabel titre;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private JButton loginButton;


    public Accueil() {
        setTitle("Ecran");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        

        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(panel1, BorderLayout.NORTH);

        titre = new JLabel("Cinema de Grenelle");
        titre.setFont(new Font("Serif", Font.BOLD, 32));
        titre.setHorizontalAlignment(SwingConstants.LEFT);

        panel1.add(titre,BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(panel2);

        String urText="<html>You can<br>use basic HTML<br>in Swing<br> components,"
                +"Hope<br> I helped!";
        JLabel lac=new JLabel(urText);
        lac.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel2.add(lac);
        JButton button = new JButton("Reserver");
        panel2.add(button);

        JPanel panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(panel3, BorderLayout.CENTER);

        String urText2="<html>You can<br>use basic HTML<br>in Swing<br> components,"
                +"Hope<br> I helped!";
        JLabel lac2=new JLabel(urText2);
        lac2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel3.add(lac2);
        JButton button2 = new JButton("Reserver");
        panel3.add(button2);










        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new Inscription();
//            }
//        });
//    }
}
