import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Date;
import java.sql.*;

public class Deposit extends JFrame implements ActionListener {
    JButton deposit,back;
    JTextField amount;
    JPanel exitconfirmation;
    JCheckBox confirm,cancel;
    String pin;
    Deposit(String pin){
        this.pin = pin;
        setSize(900, 900);
        setTitle("Deposit Page");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to deposit:");
        text.setFont(new Font("Raleway",Font.BOLD,16));
        text.setBounds(195,300,700,35);
        text.setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("System",Font.BOLD,14));
        amount.setBounds(180,350,320,20);
        image.add(amount);

        deposit = new JButton("Deposit");
        deposit.setFocusable(false);
        deposit.setBackground(Color.WHITE);
        deposit.setForeground(Color.BLACK);
        deposit.setFont(new Font("System",Font.BOLD,17));
        deposit.setBounds(355,485,150,30);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("Back");
        back.setFocusable(false);
        back.setBackground(Color.WHITE);
        back.setForeground(Color.BLACK);
        back.setFont(new Font("System",Font.BOLD,17));
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);

        //  If the user wants to go back to the previous page by clicking 'BACK' button
        exitconfirmation = new JPanel();
        exitconfirmation.setLayout(new BoxLayout(exitconfirmation,BoxLayout.Y_AXIS));
        JLabel exitext = new JLabel("Do you want to go back?");
        exitconfirmation.add(exitext);
        confirm = new JCheckBox("Confirm");
        cancel = new JCheckBox("Cancel");
        ButtonGroup exitgroup = new ButtonGroup();
        exitgroup.add(confirm);
        exitgroup.add(cancel);
        exitconfirmation.add(confirm);
        exitconfirmation.add(cancel);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==deposit){
            String depositamount = amount.getText();
            Date date = new Date();                                                //to know at what exact date and time the user deposited money (from .util.date package)
            if(depositamount.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter an amount.");
            }
            else{
                try (Connection conn = JDBCConnection.getConnection()) {
                    String query = "INSERT INTO bank(pin, date, type, amount) VALUES(?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(query);

                    pstmt.setString(1, pin);
                    pstmt.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
                    pstmt.setString(3, "Deposit");
                    pstmt.setString(4, depositamount);

                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Rs. " + depositamount + " deposited successfully!");
                    amount.setText("");

                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }

                catch(Exception ex){
                    System.out.println(ex);
                }
            }
        }
        else if(e.getSource()==back){
            JOptionPane.showConfirmDialog(this,exitconfirmation,null,JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(confirm.isSelected()){
                new Transactions(pin);
                setVisible(false);
            }
        }
    }

    public static void main(String[] args) {
        Deposit obj = new Deposit("");
    }
}


/*  JOptionPane.showMessageDialog: Just shows a message to the user. It has no "result" except that the user
clicked "OK" to close it.Use it when you just want to inform the user of something.

JOptionPane.showConfirmDialog: Asks a question / gets confirmation from the user.It shows Yes/No/Cancel or
OK/Cancel (depending on what you choose). Like here, we used panel with confirmdialogbox to confirm whether the
user wants to go back or not. It's used to make option panels/confirmation panels appear when certain events occur.

showMessageDialog-> user cant interact except 'OK' button.
showConfirmDialog-> user can interact when it appears after certain events have occured/triggered.
 */
