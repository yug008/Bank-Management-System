import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class MiniStatement extends JFrame implements ActionListener{
    String pin;
    JButton back;

    MiniStatement(String pin){
        this.pin = pin;

        setTitle("Mini Statement");
        setLocation(350,10);
        setLayout(null);
        setSize(900,900);
        getContentPane().setBackground(Color.WHITE);

        JLabel bank = new JLabel("Indian Bank");
        bank.setBackground(Color.WHITE);
        bank.setForeground(Color.BLACK);
        bank.setFont(new Font("System",Font.BOLD,25));
        bank.setBounds(380,80,500,30);
        add(bank);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFocusable(false);
        back.setBounds(700,700,150,30);
        back.addActionListener(this);
        add(back);

        JLabel cardLabel = new JLabel();
        cardLabel.setFont(new Font("System", Font.PLAIN, 16));
        cardLabel.setBounds(50, 120, 400, 20);
        add(cardLabel);

        JTextArea miniArea = new JTextArea();
        miniArea.setFont(new Font("System", Font.PLAIN, 16));
        miniArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(miniArea);
        scrollPane.setBounds(50, 150, 800, 550);
        add(scrollPane);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("System", Font.BOLD, 18));
        balanceLabel.setBounds(50, 720, 400, 20);
        add(balanceLabel);

        try (Connection conn = JDBCConnection.getConnection()) {
            // Fetch card number
            PreparedStatement psCard = conn.prepareStatement(
                    "SELECT cardnumber FROM login WHERE pin = ?"
            );
            psCard.setString(1, pin);
            ResultSet rsCard = psCard.executeQuery();
            if (rsCard.next()) {
                String cardNum = rsCard.getString("cardnumber");
                cardLabel.setText("Card Number: XXXX-XXXX-XXXX-" + cardNum.substring(cardNum.length() - 4));
            }

            // Fetch transactions
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT type, amount, date FROM bank WHERE pin = ? ORDER BY date DESC"
            );
            ps.setString(1, pin);
            ResultSet rs = ps.executeQuery();

            StringBuilder miniText = new StringBuilder();
            double balance = 0;

            while (rs.next()) {
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                String date = rs.getString("date");

                miniText.append(date)
                        .append("    ")
                        .append(type)
                        .append("    ₹")
                        .append(amount)
                        .append("\n");

                if (type.equalsIgnoreCase("Deposit")) {
                    balance += amount;
                } else if (type.equalsIgnoreCase("Withdrawal")) {
                    balance -= amount;
                }
            }
            miniArea.setText(miniText.toString());
            balanceLabel.setText("Current Balance: ₹" + balance);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        MiniStatement obj = new MiniStatement("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            setVisible(false);
            new Transactions(pin);
        }
    }
}
