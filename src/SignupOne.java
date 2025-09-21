import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.sql.*;

public class SignupOne extends JFrame implements ActionListener {
    JTextField nametextfield, fnametextfield, emailtextfield, addresstextfield, citytextfield, statetextfield, pintextfield;
    JRadioButton male, female, other, married, unmarried, Other;
    JDateChooser dateChooser;
    JButton next;
    Long randomNo;
    public boolean submitted = false;
    private SignupOneData formData;
    SignupOneData data;

    SignupOne() {
        setSize(800, 850);
        setLocation(350, 10);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        Random random = new Random();                                //random class for random number
        randomNo = 1000 + random.nextLong(9000);                   // this will create a random number between 0 to 9999

        JLabel formNo = new JLabel("Application Form No. " + randomNo);
        formNo.setFont(new Font("Raleway", Font.BOLD, 38));
        formNo.setBounds(140, 20, 600, 40);
        add(formNo);

        JLabel personalDetails = new JLabel("Page 1 - Personal Details:");
        personalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personalDetails.setBounds(270, 80, 400, 30);
        add(personalDetails);

        JLabel name = new JLabel("Name: ");
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        name.setBounds(100, 140, 100, 30);
        add(name);

        nametextfield = new JTextField();
        nametextfield.setFont(new Font("Raleway", Font.BOLD, 20));
        nametextfield.setBounds(300, 140, 400, 30);
        add(nametextfield);

        JLabel fname = new JLabel("Father's name:");
        fname.setFont(new Font("Raleway", Font.BOLD, 20));
        fname.setBounds(100, 190, 200, 30);
        add(fname);

        fnametextfield = new JTextField();
        fnametextfield.setFont(new Font("Raleway", Font.BOLD, 20));
        fnametextfield.setBounds(300, 190, 400, 30);
        add(fnametextfield);

        JLabel dob = new JLabel("Date of birth:");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100, 240, 150, 30);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 240, 400, 30);
        ((JTextField) dateChooser.getDateEditor().getUiComponent()).setForeground(Color.BLACK);        // Set text color to black
        add(dateChooser);

        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        gender.setBounds(100, 290, 150, 30);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(300, 290, 60, 30);
        male.setBackground(Color.WHITE);
        add(male);
        female = new JRadioButton("Female");
        female.setBounds(450, 290, 70, 30);
        female.setBackground(Color.WHITE);
        add(female);
        other = new JRadioButton("Other");
        other.setBounds(600, 290, 60, 30);
        other.setBackground(Color.WHITE);
        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);
        gendergroup.add(other);
        add(other);

        JLabel email = new JLabel("Email address:");
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        email.setBounds(100, 340, 150, 30);
        add(email);

        emailtextfield = new JTextField();
        emailtextfield.setFont(new Font("Raleway", Font.BOLD, 20));
        emailtextfield.setBounds(300, 340, 400, 30);
        add(emailtextfield);

        JLabel marital = new JLabel("Marital status:");
        marital.setFont(new Font("Raleway", Font.BOLD, 20));
        marital.setBounds(100, 390, 150, 30);
        add(marital);

        married = new JRadioButton("Married");
        married.setBounds(300, 390, 100, 30);
        married.setBackground(Color.WHITE);
        add(married);
        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(450, 390, 100, 30);
        unmarried.setBackground(Color.WHITE);
        add(unmarried);
        Other = new JRadioButton("Other");
        Other.setBounds(630, 390, 100, 30);
        Other.setBackground(Color.WHITE);
        add(Other);

        ButtonGroup maritalgroup = new ButtonGroup();
        maritalgroup.add(married);
        maritalgroup.add(unmarried);
        maritalgroup.add(Other);


        JLabel address = new JLabel("Address:");
        address.setFont(new Font("Raleway", Font.BOLD, 20));
        address.setBounds(100, 440, 150, 30);
        add(address);

        addresstextfield = new JTextField();
        addresstextfield.setFont(new Font("Raleway", Font.BOLD, 20));
        addresstextfield.setBounds(300, 440, 400, 30);
        add(addresstextfield);

        JLabel city = new JLabel("City:");
        city.setFont(new Font("Raleway", Font.BOLD, 20));
        city.setBounds(100, 490, 150, 30);
        add(city);

        citytextfield = new JTextField();
        citytextfield.setFont(new Font("Raleway", Font.BOLD, 20));
        citytextfield.setBounds(300, 490, 400, 30);
        add(citytextfield);

        JLabel state = new JLabel("State:");
        state.setFont(new Font("Raleway", Font.BOLD, 20));
        state.setBounds(100, 540, 150, 30);
        add(state);

        statetextfield = new JTextField();
        statetextfield.setFont(new Font("Raleway", Font.BOLD, 20));
        statetextfield.setBounds(300, 540, 400, 30);
        add(statetextfield);

        JLabel pincode = new JLabel("Pin Code:");
        pincode.setFont(new Font("Raleway", Font.BOLD, 20));
        pincode.setBounds(100, 590, 150, 30);
        add(pincode);

        pintextfield = new JTextField();
        pintextfield.setFont(new Font("Raleway", Font.BOLD, 20));
        pintextfield.setBounds(300, 590, 400, 30);
        add(pintextfield);

        next = new JButton("NEXT");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFocusable(false);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);                            //'actionPerformed()'method is called everytime when 'NEXT' button is clicked.
        add(next);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {                               //if user clicks on next then perform the below operations/actions
            String formno = String.valueOf(randomNo);                  //converts the random application no. into a string.
            String name = nametextfield.getText();                     //stores the data input in the nametextfield in a string variable
            String fname = fnametextfield.getText();
            String email = emailtextfield.getText();
            String address = addresstextfield.getText();
            String city = citytextfield.getText();
            String state = statetextfield.getText();
            String pin = pintextfield.getText();
            String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();          //stores the date selected by the user in the string variable.

            //For radiobuttons
            String gender = null;
            if (male.isSelected()) {                                           //If the radiobutton 'male' is selected, then the value 'Male' will be stored in the string variable
                gender = "Male";
            } else if (female.isSelected()) {                                    //If the radiobutton 'female' is selected, then the value 'Female' will be stored in the string variable
                gender = "Female";
            } else if (other.isSelected()) {                                           //If the radiobutton 'other' is selected, then the value 'Other' will be stored in the string variable
                gender = "Other";
            }


            String marital = null;                                           //doing the same process of 'isSelected()' for radiobuttons in case of marital status.
            if (married.isSelected()) {
                marital = "Married";
            } else if (unmarried.isSelected()) {
                marital = "Unmarried";
            } else if (Other.isSelected()) {
                marital = "Other";
            }

            //For requirements during application filling / validations
            if (name.equals("")) {                                                 //if the 'Name' field is kept empty, then we can use a popup by jOptionPane to ask the user to input it. We can use this same process for all the fields. But here we will keep it for name and email only.
                JOptionPane.showMessageDialog(null, "Name is required !");
                return;
            }
            if (email.equals("")) {
                JOptionPane.showMessageDialog(null, "Email ID is required !");
                return;
            }

            data = new SignupOneData(formno, name, fname, dob, gender, email, marital, address, city, state, pin);
            submitted = true;

            try {
                Connection conn = JDBCConnection.getConnection();
                String query = "INSERT INTO signup (formno, name, father_name, dob, gender, email, marital_status, address, city, state, pin) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, formno);
                ps.setString(2, name);
                ps.setString(3, fname);
                ps.setString(4, dob);
                ps.setString(5, gender);
                ps.setString(6, email);
                ps.setString(7, marital);
                ps.setString(8, address);
                ps.setString(9, city);
                ps.setString(10, state);
                ps.setString(11, pin);

                ps.executeUpdate();  // <--- This actually saves the record in DB
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
            }


            //Transition from Signupone -> Signuptwo
            setVisible(false);                       // after clicking "Next," it hides the Signupone form from the user without completely destroying it.
            new SignupTwo(formno);                         //calls the constructor of signuptwo, setting up its UI components and showing the new window (because setVisible(true) is called inside the constructor.
        }
    }

    public SignupOneData getFormData() {
        return data;
    }

    public static void main(String[] args) {
        SignupOne obj = new SignupOne();
    }
}





/*We are importing a 3rd party library called 'DateChooser' and adding it to the External libraries to use it.
Its respective class is JDateChooser. Its will be used to select dates for date of birth.

ButtonGroup in Java Swing is used to group radio buttons together so that only one can be selected at a time.

To store the data into database, we will make all the elements which contain data as 'global'.
We want the data which is stored in the textField by user to get stored in the database. Thus, we will make all
the textFields which contain data as 'global'.We will do the similar for the buttons and radiobuttons which contain
data and make them as 'global'.

The '.isSelected()' method in Java is used to check whether a button-like component is currently selected or not.

JOptionPane is a Swing class in Java used to create simple popup dialogs for showing messages, asking questions, getting
user input,etc.

In this, we will not proceed further until the 'Name' field is filled. If its kept empty, then JOptionPane will
create a popup / message showing that name is required.

After filling all the details in the application, when we will click on 'NEXT', the 'actionPerformed' method will
get called and all the details from textFields, radio buttons, etc. will get stored in the variables we manually
created.
*/