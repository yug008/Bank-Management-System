import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class Pinchange extends JFrame implements ActionListener {
    String cardnumber;
    JButton change,back;
    JPasswordField newPintext,renewPintext;

    Pinchange(String cardnumber){
        this.cardnumber = cardnumber;
        setSize(900,900);
        setLocation(350,10);
        setLayout(null);
        setTitle("PIN Change");
        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setFont(new Font("Raleway",Font.BOLD,16));
        text.setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        text.setBounds(260,300,150,22);
        image.add(text);

        JLabel newPin = new JLabel("New PIN:");
        newPin.setBackground(Color.BLACK);
        newPin.setForeground(Color.WHITE);
        newPin.setFont(new Font("System",Font.BOLD,16));
        newPin.setBounds(170,420,150,22);
        image.add(newPin);

        JLabel renewPin = new JLabel("Re-enter new PIN:");
        renewPin.setFont(new Font("System",Font.BOLD,16));
        renewPin.setBackground(Color.BLACK);
        renewPin.setForeground(Color.WHITE);
        renewPin.setBounds(170,440,200,22);
        image.add(renewPin);

        newPintext = new JPasswordField();
        newPintext.setFont(new Font("System",Font.BOLD,20));
        newPintext.setBounds(350,420,160,18);
        image.add(newPintext);

        renewPintext = new JPasswordField();
        renewPintext.setFont(new Font("System",Font.BOLD,20));
        renewPintext.setBounds(350,440,160,18);
        image.add(renewPintext);

        change = new JButton("Change");
        change.setBackground(Color.WHITE);
        change.setForeground(Color.BLACK);
        change.setFont(new Font("System",Font.BOLD,15));
        change.setBounds(400,490,110,20);
        change.setFocusable(false);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("Back");
        back.setBackground(Color.WHITE);
        back.setForeground(Color.BLACK);
        back.setFont(new Font("System",Font.BOLD,15));
        back.setBounds(400,510,110,20);
        back.setFocusable(false);
        back.addActionListener(this);
        image.add(back);


        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == change) {
            char[] pin1 = newPintext.getPassword();
            char[] pin2 = renewPintext.getPassword();

            if (!Arrays.equals(pin1, pin2)) {
                JOptionPane.showMessageDialog(this, "Your new PIN and re-entered new PIN aren't same");
                newPintext.setText("");
                renewPintext.setText("");
                return;
            }
             else {
                JOptionPane.showMessageDialog(this, "Your PIN has been reset successfully!");

                String newPin = new String(pin1);

                try (Connection conn = JDBCConnection.getConnection()) {
                    String query = "UPDATE login SET pin=? WHERE cardnumber=?";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, newPin);
                    pst.setString(2, cardnumber);

                    int rows = pst.executeUpdate();
                    if (rows > 0) {
                        JOptionPane.showMessageDialog(this, "PIN updated successfully in database!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Card number not found in database!");
                    }


                    pst.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
                }
            }
        }
    }



    public static void main(String[] args) {
        Pinchange obj  = new Pinchange("87654321");
    }
}




/*  Its more better to store password of JPasswordField in an char[] array rather than string.
You should use 'Arrays.equals()' whenever you want to compare the contents of two arrays (like char[],
 int[], byte[], etc.) element by element.
 */