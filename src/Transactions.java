import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener {
    JButton deposit,cashwithdrawal,fastcash,ministatement,pinchange,balanceenquiry,exit;
    String pin;

    Transactions(String pin){
        this.pin = pin;
        setSize(900,900);
        setLocation(350,10);
        setLayout(null);
        setTitle("Transactions Page");
        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("Please select your transaction:");
        text.setBounds(220,300,700,35);
        text.setFont(new Font("Raleway",Font.BOLD,16));
        text.setForeground(Color.WHITE);
        text.setBackground(Color.BLACK);
        image.add(text);                                                               //here we did image.add(text) as we wanted to add/insert the text label on top of the atm image, not in between the image and the frame otherwise it wouldn't be visible.

        deposit = new JButton("Deposit");
        deposit.setBounds(170,415,150,30);
        deposit.setForeground(Color.BLACK);
        deposit.setBackground(Color.WHITE);
        deposit.setFont(new Font("System",Font.BOLD,16));
        deposit.setFocusable(false);
        deposit.addActionListener(this);
        image.add(deposit);

        cashwithdrawal = new JButton("Cash Withdrawal");
        cashwithdrawal.setBounds(355,415,150,30);
        cashwithdrawal.setForeground(Color.BLACK);
        cashwithdrawal.setBackground(Color.WHITE);
        cashwithdrawal.setFont(new Font("System",Font.BOLD,13));
        cashwithdrawal.setFocusable(false);
        cashwithdrawal.addActionListener(this);
        image.add(cashwithdrawal);

        fastcash = new JButton("Fast Cash");
        fastcash.setBounds(170,450,150,30);
        fastcash.setForeground(Color.BLACK);
        fastcash.setBackground(Color.WHITE);
        fastcash.setFont(new Font("System",Font.BOLD,16));
        fastcash.setFocusable(false);
        fastcash.addActionListener(this);
        image.add(fastcash);

        ministatement = new JButton("Mini Statement");
        ministatement.setBounds(355,450,150,30);
        ministatement.setForeground(Color.BLACK);
        ministatement.setBackground(Color.WHITE);
        ministatement.setFont(new Font("System",Font.BOLD,15));
        ministatement.setFocusable(false);
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange = new JButton("PIN Change");
        pinchange.setBounds(170,485,150,30);
        pinchange.setForeground(Color.BLACK);
        pinchange.setBackground(Color.WHITE);
        pinchange.setFont(new Font("System",Font.BOLD,16));
        pinchange.setFocusable(false);
        pinchange.addActionListener(this);
        image.add(pinchange);

        balanceenquiry = new JButton("Balance Enquiry");
        balanceenquiry.setBounds(355,485,150,30);
        balanceenquiry.setForeground(Color.BLACK);
        balanceenquiry.setBackground(Color.WHITE);
        balanceenquiry.setFont(new Font("System",Font.BOLD,13));
        balanceenquiry.setFocusable(false);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);

        exit = new JButton("Exit");
        exit.setBounds(355,520,150,30);
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.WHITE);
        exit.setFont(new Font("System",Font.BOLD,16));
        exit.setFocusable(false);
        exit.addActionListener(this);
        image.add(exit);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exit){
            setVisible(false);
        }
        else if(e.getSource()==deposit){
            setVisible(false);
            new Deposit(pin);
        }
        else if(e.getSource()==cashwithdrawal){
            setVisible(false);
            new Withdrawal(pin);
        }
        else if (e.getSource()==fastcash){
            setVisible(false);
            new FastCash(pin);
        }
        else if(e.getSource()==pinchange){
            setVisible(false);
            new Pinchange(pin);
        }
        else if(e.getSource()==balanceenquiry){
            setVisible(false);
            new BalanceEnquiry(pin);
        }
        else if(e.getSource()==ministatement){
            setVisible(false);
            new MiniStatement(pin);
        }
    }

    public static void main(String[] args) {

    }
}
