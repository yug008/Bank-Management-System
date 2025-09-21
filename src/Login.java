import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton signIn, signUp, clear;
    JTextField cardNotextfield;
    JPasswordField pintextfield;
    public boolean signupRequested = false;

    public Login() {
        setSize(800, 450);
        setTitle("Automated Teller Machine");
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel cardNo = new JLabel("Card No.");
        cardNo.setFont(new Font("Raleway", Font.BOLD, 30));
        cardNo.setBounds(120, 150, 150, 40);
        add(cardNo);

        cardNotextfield = new JTextField();
        cardNotextfield.setBounds(300, 150, 250, 30);
        cardNotextfield.setFont(new Font("Arial",Font.BOLD,14));
        add(cardNotextfield);

        JLabel pin = new JLabel("PIN");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        add(pin);

        pintextfield = new JPasswordField();
        pintextfield.setBounds(300, 220, 250, 30);
        pintextfield.setFont(new Font("Arial",Font.BOLD,14));
        add(pintextfield);

        signIn = new JButton("Sign in");
        signIn.setBounds(300, 300, 100, 30);
        signIn.setBackground(Color.BLACK);
        signIn.setForeground(Color.WHITE);
        signIn.setFocusable(false);
        signIn.addActionListener(this);
        add(signIn);

        clear = new JButton("Clear");
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.setBounds(430, 300, 100, 30);
        clear.setFocusable(false);
        clear.addActionListener(this);
        add(clear);

        signUp = new JButton("Sign up");
        signUp.setBackground(Color.BLACK);
        signUp.setForeground(Color.WHITE);
        signUp.setBounds(300, 350, 230, 30);
        signUp.setFocusable(false);
        signUp.addActionListener(this);
        add(signUp);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cardNo = cardNotextfield.getText().trim();
        String pin = new String(pintextfield.getPassword()).trim();

        if(e.getSource() == signIn) {
            if(cardNo.isEmpty() || pin.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Enter Card Number and PIN");
                return;
            }

            String query = "SELECT * FROM login WHERE cardnumber=? AND pin=?";
            try (Connection conn = JDBCConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, cardNo);
                ps.setString(2, pin);

                try (ResultSet rs = ps.executeQuery()) {
                    if(rs.next()) {
                        JOptionPane.showMessageDialog(null,"Login Successful!");
                        this.setVisible(false);
                        new Transactions(pin).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null,"Invalid Card Number or PIN");
                    }
                }

            } catch(Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"Database error!");
            }
        }

        else if(e.getSource() == signUp){
            signupRequested = true;
            new SignupOne();
            setVisible(false);
        }
        else if(e.getSource() == clear){
            cardNotextfield.setText("");
            pintextfield.setText("");
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}

