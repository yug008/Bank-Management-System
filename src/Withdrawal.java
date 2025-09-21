import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Withdrawal extends JFrame implements ActionListener {
    JButton withdrawal, back;
    JTextField amount;
    String pin;

    Withdrawal(String pin) {
        this.pin = pin;
        setSize(900, 900);
        setTitle("Withdrawal Page");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to withdraw:");
        text.setFont(new Font("Raleway", Font.BOLD, 16));
        text.setBounds(195, 300, 700, 35);
        text.setForeground(Color.WHITE);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("System", Font.BOLD, 14));
        amount.setBounds(180, 350, 320, 20);
        image.add(amount);

        withdrawal = new JButton("Withdraw");
        withdrawal.setFocusable(false);
        withdrawal.setBackground(Color.WHITE);
        withdrawal.setForeground(Color.BLACK);
        withdrawal.setFont(new Font("System", Font.BOLD, 17));
        withdrawal.setBounds(355, 485, 150, 30);
        withdrawal.addActionListener(this);
        image.add(withdrawal);

        back = new JButton("Back");
        back.setFocusable(false);
        back.setBackground(Color.WHITE);
        back.setForeground(Color.BLACK);
        back.setFont(new Font("System", Font.BOLD, 17));
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawal) {
            String withdrawalamount = amount.getText().trim();
            if (withdrawalamount.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter an amount to withdraw.");
                return;
            }

            int withdrawAmt;
            try {
                withdrawAmt = Integer.parseInt(withdrawalamount);
                if (withdrawAmt <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid positive amount.");
                    return;
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter numbers only.");
                return;
            }

            try (Connection conn = JDBCConnection.getConnection()) {
                // Step 1: Check balance
                String balanceQuery = "SELECT SUM(CASE WHEN type='Deposit' THEN amount ELSE -amount END) AS balance FROM bank WHERE pin=?";
                PreparedStatement pstmt1 = conn.prepareStatement(balanceQuery);
                pstmt1.setString(1, pin);
                ResultSet rs = pstmt1.executeQuery();

                int balance = 0;
                if (rs.next()) {
                    balance = rs.getInt("balance");
                }

                // Step 2: Check if enough balance
                if (withdrawAmt > balance) {
                    JOptionPane.showMessageDialog(null, "Insufficient balance. Current Balance: Rs. " + balance);
                    return;
                }

                // Step 3: Insert withdrawal transaction
                String query = "INSERT INTO bank(pin, date, type, amount) VALUES(?, ?, ?, ?)";
                PreparedStatement pstmt2 = conn.prepareStatement(query);
                pstmt2.setString(1, pin);
                pstmt2.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
                pstmt2.setString(3, "Withdrawal");
                pstmt2.setInt(4, withdrawAmt);

                pstmt2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Rs. " + withdrawAmt + " withdrawn successfully!");

                setVisible(false);
                new Transactions(pin).setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }

        else if (e.getSource() == back) {
            int choice = JOptionPane.showConfirmDialog(this,"Do you want to go back?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        }
    }

    public static void main(String[] args) {

    }
}


/* JOptionPane.showConfirmDialog() returns an int value based on user's prefrences.
*/
 
