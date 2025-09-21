import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener{
    String pin;
    JButton back;

    BalanceEnquiry(String pin){
        this.pin = pin;
        setTitle("Balance Enquiry");
        setLocation(350,10);
        setSize(900,900);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        int balance = 0;
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT type, amount FROM bank WHERE pin = ?")) {

            ps.setString(1, pin);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");
                int amount = rs.getInt("amount");

                if ("Deposit".equalsIgnoreCase(type)) {
                    balance += amount;
                } else {
                    balance -= amount;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel currentbalance = new JLabel("Your Current Account Balance is: Rs " + balance);
        currentbalance.setBounds(190, 300, 500, 30);
        currentbalance.setFont(new Font("Raleway", Font.BOLD, 15));
        currentbalance.setForeground(Color.WHITE);
        currentbalance.setBackground(Color.BLACK);
        image.add(currentbalance);

        back = new JButton("Back");
        back.setBackground(Color.WHITE);
        back.setForeground(Color.BLACK);
        back.setFocusable(false);
        back.setFont(new Font("Raleway",Font.BOLD,15));
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            int choice = JOptionPane.showConfirmDialog(this,"Do you want to go back ?","Exit Confirmation",JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                setVisible(false);
                new Transactions(pin);
            }
        }
    }

    public static void main(String[] args) {
        BalanceEnquiry obj = new BalanceEnquiry("");

    }
}
