import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignupThree extends JFrame implements ActionListener {
    JCheckBox tnc;
    JRadioButton estatement, emailsms, chequebook, mobilebanking, internetbanking, atmcard;
    JRadioButton savingaccount, fixedepositaccount, currentaccount, recurringdepositaccount;
    JLabel accountedetails, accountype, cardnumber, cardnumberdescrip, pin, pindescrip, servicesrequired;
    JPasswordField cardnumbertextfield, pintextfield;
    JButton submit, cancel;

    public boolean submitted = false;
    private SignupThreeData formData;

    SignupThree() {
        setSize(800, 850);
        setLocation(350, 10);
        setTitle("Account Details");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        accountedetails = new JLabel("Page 3 - Account Details");
        accountedetails.setFont(new Font("Raleway", Font.BOLD, 30));
        accountedetails.setBounds(250, 80, 400, 40);
        add(accountedetails);

        accountype = new JLabel("Account Type:");
        accountype.setFont(new Font("Raleway", Font.BOLD, 20));
        accountype.setBounds(100, 150, 300, 25);
        add(accountype);

        savingaccount = new JRadioButton("Saving Account");
        savingaccount.setBackground(Color.WHITE);
        savingaccount.setForeground(Color.BLACK);
        savingaccount.setBounds(100, 185, 200, 20);
        savingaccount.setFocusable(false);
        add(savingaccount);

        fixedepositaccount = new JRadioButton("Fixed Deposit Account");
        fixedepositaccount.setBackground(Color.WHITE);
        fixedepositaccount.setForeground(Color.BLACK);
        fixedepositaccount.setBounds(310, 185, 200, 20);
        fixedepositaccount.setFocusable(false);
        add(fixedepositaccount);

        currentaccount = new JRadioButton("Current Account");
        currentaccount.setBackground(Color.WHITE);
        currentaccount.setForeground(Color.BLACK);
        currentaccount.setBounds(100, 220, 200, 20);
        currentaccount.setFocusable(false);
        add(currentaccount);

        recurringdepositaccount = new JRadioButton("Recurring Deposit Account");
        recurringdepositaccount.setBackground(Color.WHITE);
        recurringdepositaccount.setForeground(Color.BLACK);
        recurringdepositaccount.setBounds(310, 220, 200, 20);
        recurringdepositaccount.setFocusable(false);
        add(recurringdepositaccount);

        cardnumber = new JLabel("Card Number:");
        cardnumber.setFont(new Font("Raleway", Font.BOLD, 20));
        cardnumber.setBounds(100, 280, 300, 25);
        add(cardnumber);

        cardnumberdescrip = new JLabel("Your 14 digit card number ");
        cardnumberdescrip.setFont(new Font("Raleway", Font.BOLD, 12));
        cardnumberdescrip.setBounds(100, 310, 250, 20);
        add(cardnumberdescrip);

        cardnumbertextfield = new JPasswordField();
        cardnumbertextfield.setBounds(400, 280, 300, 25);
        cardnumbertextfield.setFont(new Font("Raleway", Font.BOLD, 20));
        add(cardnumbertextfield);

        pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 20));
        pin.setBounds(100, 350, 300, 25);
        add(pin);

        pindescrip = new JLabel("Your 4 digit PIN number");
        pindescrip.setBounds(100, 380, 250, 20);
        pindescrip.setFont(new Font("Raleway", Font.BOLD, 12));
        add(pindescrip);

        pintextfield = new JPasswordField();
        pintextfield.setBounds(400, 350, 300, 25);
        pintextfield.setFont(new Font("Raleway", Font.BOLD, 20));
        add(pintextfield);

        servicesrequired = new JLabel("Services required:");
        servicesrequired.setBounds(100, 420, 300, 25);
        servicesrequired.setFont(new Font("Raleway", Font.BOLD, 20));
        add(servicesrequired);

        atmcard = new JRadioButton("ATM Card");
        atmcard.setBounds(100, 450, 200, 20);
        atmcard.setFont(new Font("Raleway", Font.BOLD, 15));
        atmcard.setBackground(Color.WHITE);
        add(atmcard);

        internetbanking = new JRadioButton("Internet Banking");
        internetbanking.setBounds(300, 450, 200, 20);
        internetbanking.setFont(new Font("Raleway", Font.BOLD, 15));
        internetbanking.setBackground(Color.WHITE);
        add(internetbanking);

        mobilebanking = new JRadioButton("Mobile Banking");
        mobilebanking.setBounds(100, 485, 200, 20);
        mobilebanking.setFont(new Font("Raleway", Font.BOLD, 15));
        mobilebanking.setBackground(Color.WHITE);
        add(mobilebanking);

        chequebook = new JRadioButton("Cheque Book");
        chequebook.setBounds(100, 520, 200, 20);
        chequebook.setFont(new Font("Raleway", Font.BOLD, 15));
        chequebook.setBackground(Color.WHITE);
        add(chequebook);

        emailsms = new JRadioButton("Email & SMS Alerts");
        emailsms.setBounds(300, 485, 200, 20);
        emailsms.setFont(new Font("Raleway", Font.BOLD, 15));
        emailsms.setBackground(Color.WHITE);
        add(emailsms);

        estatement = new JRadioButton("E Statement");
        estatement.setBounds(300, 520, 200, 20);
        estatement.setFont(new Font("Raleway", Font.BOLD, 15));
        estatement.setBackground(Color.WHITE);
        add(estatement);

        ButtonGroup services = new ButtonGroup();
        services.add(atmcard);
        services.add(internetbanking);
        services.add(mobilebanking);
        services.add(emailsms);
        services.add(chequebook);
        services.add(estatement);

        tnc = new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge.");
        tnc.setBounds(100, 600, 520, 20);
        tnc.setFont(new Font("Raleway", Font.PLAIN, 12));
        tnc.setBackground(Color.WHITE);
        add(tnc);
        tnc.addActionListener(this);

        submit = new JButton("Submit");
        submit.setBounds(300, 650, 100, 40);
        submit.setFont(new Font("Raleway", Font.BOLD, 18));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFocusable(false);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(420, 650, 100, 40);
        cancel.setFont(new Font("Raleway", Font.BOLD, 18));
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFocusable(false);
        cancel.addActionListener(this);
        add(cancel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            // Read selections
            String service = null;
            if (atmcard.isSelected()) service = "ATM Card";
            else if (internetbanking.isSelected()) {
                service = "Internet Banking";
            } else if (mobilebanking.isSelected()) {
                service = "Mobile Banking";
            } else if (emailsms.isSelected()) {
                service = "Email & SMS Alerts";
            } else if (chequebook.isSelected()) {
                service = "Cheque Book";
            } else if (estatement.isSelected()) {
                service = "E Statement";
            }


            String accounttype = null;
            if (savingaccount.isSelected()) {
                accounttype = "Saving Account";
            } else if (fixedepositaccount.isSelected()) {
                accounttype = "Fixed Deposit Account";
            } else if (recurringdepositaccount.isSelected()) {
                accounttype = "Recurring Deposit Account";
            } else if (currentaccount.isSelected()) {
                accounttype = "Current Account";
            }

            String cardNumber = new String(cardnumbertextfield.getPassword()).trim();
            String pin = new String(pintextfield.getPassword()).trim();

            // Validations
            if (accounttype == null) {
                JOptionPane.showMessageDialog(this, "Please select an account type!");
                return;
            }
            if (service == null) {
                JOptionPane.showMessageDialog(this, "Please select a service!");
                return;
            }
            if (cardNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter card number!");
                return;
            }
            if (pin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter PIN!");
                return;
            }
            if (!tnc.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please accept terms and conditions!");
                return;
            }
            formData = new SignupThreeData(
                    estatement.isSelected() ? "Yes" : "No",
                    emailsms.isSelected() ? "Yes" : "No",
                    chequebook.isSelected() ? "Yes" : "No",
                    mobilebanking.isSelected() ? "Yes" : "No",
                    internetbanking.isSelected() ? "Yes" : "No",
                    atmcard.isSelected() ? "Yes" : "No",
                    savingaccount.isSelected() ? "Yes" : "No",
                    fixedepositaccount.isSelected() ? "Yes" : "No",
                    currentaccount.isSelected() ? "Yes" : "No",
                    recurringdepositaccount.isSelected() ? "Yes" : "No",
                    new String(pintextfield.getPassword()).trim()
            );

            submitted = true;
            dispose();
            new Transactions(pin);
            setVisible(false);
        }
        else if (e.getSource() == cancel) {
                // Reset all fields
                estatement.setSelected(false);
                emailsms.setSelected(false);
                chequebook.setSelected(false);
                mobilebanking.setSelected(false);
                internetbanking.setSelected(false);
                atmcard.setSelected(false);
                savingaccount.setSelected(false);
                fixedepositaccount.setSelected(false);
                currentaccount.setSelected(false);
                recurringdepositaccount.setSelected(false);

                cardnumbertextfield.setText("");
                pintextfield.setText("");
                tnc.setSelected(false);
            }
        }

        public SignupThreeData getFormData () {
            return formData;
        }

        public static void main (String[]args){

        new SignupThree();
        }
    }




/* 'trim()' is a Java String method that removes any leading (at the start) and trailing (at the end) whitespace from a
string. It does not remove spaces in the middle of the string.


 */

