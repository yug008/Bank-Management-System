import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FastCash extends JFrame implements ActionListener {
    JButton hundred, fiveHundred, thousand, twoThousand, fiveThousand, tenthousand, back;
    String pin;

    FastCash(String pin) {
        this.pin = pin;

        setSize(900, 900);
        setLocation(350, 10);
        setLayout(null);
        setTitle("Fast Cash Page");
        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Please select withdrawal amount:");
        text.setBounds(220, 300, 700, 35);
        text.setFont(new Font("Raleway", Font.BOLD, 16));
        text.setForeground(Color.WHITE);
        image.add(text);

        hundred = new JButton("Rs.100");
        hundred.setBounds(170, 415, 150, 30);
        hundred.addActionListener(this);
        image.add(hundred);

        fiveHundred = new JButton("Rs.500");
        fiveHundred.setBounds(355, 415, 150, 30);
        fiveHundred.addActionListener(this);
        image.add(fiveHundred);

        thousand = new JButton("Rs.1000");
        thousand.setBounds(170, 450, 150, 30);
        thousand.addActionListener(this);
        image.add(thousand);

        twoThousand = new JButton("Rs.2000");
        twoThousand.setBounds(355, 450, 150, 30);
        twoThousand.addActionListener(this);
        image.add(twoThousand);

        fiveThousand = new JButton("Rs.5000");
        fiveThousand.setBounds(170, 485, 150, 30);
        fiveThousand.addActionListener(this);
        image.add(fiveThousand);

        tenthousand = new JButton("Rs.10000");
        tenthousand.setBounds(355, 485, 150, 30);
        tenthousand.addActionListener(this);
        image.add(tenthousand);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            int choice = JOptionPane.showConfirmDialog(this,"Do you want to go back?","Exit Confirmation",JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                setVisible(false);
                new Transactions(pin);
            }
        }
        else {
            String amountStr = ((JButton) e.getSource()).getText().substring(3);
            int amount = Integer.parseInt(amountStr);

            try (Connection con = JDBCConnection.getConnection()) {
                // Check balance
                String checkBalance = "SELECT SUM(CASE WHEN type='Deposit' THEN amount ELSE -amount END) AS balance FROM bank WHERE pin=?";
                try (PreparedStatement ps1 = con.prepareStatement(checkBalance)) {
                    ps1.setString(1, pin);
                    ResultSet rs = ps1.executeQuery();

                    int balance = 0;
                    if (rs.next()) {
                        balance = rs.getInt("balance");
                    }

                    if (balance < amount) {
                        JOptionPane.showMessageDialog(this, "Insufficient Balance! Current balance: Rs." + balance);
                        return;
                    }
                }

                // Insert withdrawal record
                String query = "INSERT INTO bank (pin, type, amount) VALUES (?, ?, ?)";
                try (PreparedStatement ps2 = con.prepareStatement(query)) {
                    ps2.setString(1, pin);
                    ps2.setString(2, "Withdrawal");
                    ps2.setInt(3, amount);
                    ps2.executeUpdate();
                }

                JOptionPane.showMessageDialog(this, "Rs." + amount + " withdrawn successfully!");


            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new FastCash("1234"); // test with pin
    }
}




/* The JOptionPane.showConfirmDialog() method in Java Swing returns an int value representing the user's
selection.
 */
