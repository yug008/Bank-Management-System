import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class SignupTwo extends JFrame implements ActionListener {
    private JComboBox<String> religionDrop, categoryDrop, incomeDrop, qualificationDrop, occupationDrop;
    private JTextField panTextField, aadharTextField;
    private JRadioButton seniorYes, seniorNo, existingYes, existingNo;
    private ButtonGroup seniorGroup, existingGroup;
    private JButton nextButton;
    public boolean submitted = false;
    private final String formNo;
    private SignupTwoData data = null;

    public SignupTwo(String formNo) {
        this.formNo = formNo;

        setTitle("New Account Application Form - Page 2");
        setSize(800, 850);
        setLocation(350, 10);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Header
        JLabel header = new JLabel("Page 2 – Additional Details");
        header.setFont(new Font("Raleway", Font.BOLD, 22));
        header.setBounds(270, 80, 400, 30);
        add(header);

        // Religion
        addLabel("Religion:", 100, 140);
        religionDrop = createComboBox(new String[]{"Hindu","Buddhism","Sikh","Christianity","Muslim"}, 300, 140);

        // Category
        addLabel("Category:", 100, 190);
        categoryDrop = createComboBox(new String[]{"General","OBC","SC","ST"}, 300, 190);

        // Income
        addLabel("Income:", 100, 240);
        incomeDrop = createComboBox(new String[]{"Poor","Lower Middle Class","Middle Class","Higher Middle Class","Rich","Super Rich"}, 300, 240);

        // Education
        addLabel("Educational Qualification:", 100, 290);
        qualificationDrop = createComboBox(new String[]{"10th pass","12th pass","Degree passout","No formal education"}, 360, 290, 290);

        // Occupation
        addLabel("Occupation:", 100, 340);
        occupationDrop = createComboBox(new String[]{"Job","Business","Government work"}, 250, 340, 400);

        // PAN
        addLabel("PAN Number:", 100, 390);
        panTextField = createTextField(250, 390, 400);

        // Aadhar
        addLabel("Aadhar Number:", 100, 440);
        aadharTextField = createTextField(270, 440, 380);

        // Senior Citizen
        addLabel("Senior Citizen:", 100, 490);
        seniorYes = createRadioButton("Yes", 300, 490);
        seniorNo = createRadioButton("No", 380, 490);
        seniorGroup = new ButtonGroup();
        seniorGroup.add(seniorYes);
        seniorGroup.add(seniorNo);

        // Existing Account
        addLabel("Existing Account:", 100, 540);
        existingYes = createRadioButton("Yes", 300, 540);
        existingNo = createRadioButton("No", 380, 540);
        existingGroup = new ButtonGroup();
        existingGroup.add(existingYes);
        existingGroup.add(existingNo);

        // Next Button
        nextButton = new JButton("Next");
        nextButton.setBounds(620, 630, 80, 30);
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusable(false);
        nextButton.addActionListener(this);
        add(nextButton);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Raleway", Font.BOLD, 20));
        label.setBounds(x, y, 250, 30);
        add(label);
    }

    private JComboBox<String> createComboBox(String[] items, int x, int y) {
        return createComboBox(items, x, y, 350);
    }

    private JComboBox<String> createComboBox(String[] items, int x, int y, int width) {
        JComboBox<String> combo = new JComboBox<>();
        for (String item : items) combo.addItem(item);
        combo.setBounds(x, y, width, 30);
        combo.setBackground(Color.WHITE);
        add(combo);
        return combo;
    }

    private JTextField createTextField(int x, int y, int width) {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Raleway", Font.BOLD, 20));
        tf.setBounds(x, y, width, 30);
        add(tf);
        return tf;
    }

    private JRadioButton createRadioButton(String text, int x, int y) {
        JRadioButton rb = new JRadioButton(text);
        rb.setBounds(x, y, 60, 30);
        rb.setBackground(Color.WHITE);
        add(rb);
        return rb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != nextButton) return;

        // Gather inputs
        String religion    = (String) religionDrop.getSelectedItem();
        String category    = (String) categoryDrop.getSelectedItem();
        String income      = (String) incomeDrop.getSelectedItem();
        String qualification = (String) qualificationDrop.getSelectedItem();
        String occupation  = (String) occupationDrop.getSelectedItem();
        String pan         = panTextField.getText().trim();
        String aadhar      = aadharTextField.getText().trim();

        String senior = seniorYes.isSelected() ? "Yes" : seniorNo.isSelected() ? "No" : null;
        String existing = existingYes.isSelected() ? "Yes" : existingNo.isSelected() ? "No" : null;

        // Validate
        if (pan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "PAN is required!");
            return;
        }
        if (aadhar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aadhar is required!");
            return;
        }
        if (senior == null) {
            JOptionPane.showMessageDialog(this, "Please select Senior Citizen status!");
            return;
        }
        if (existing == null) {
            JOptionPane.showMessageDialog(this, "Please select Existing Account status!");
            return;
        }

        // Prepare data
        data = new SignupTwoData(religion, category, income, qualification, occupation, pan, aadhar, senior, existing);

        // Insert into DB
        String sql = "INSERT INTO signuptwo "
                + "(formno, religion, category, income, qualification, occupation, pan, aadhar, senior_citizen, existing_account) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = JDBCConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, formNo);
            pst.setString(2, religion);
            pst.setString(3, category);
            pst.setString(4, income);
            pst.setString(5, qualification);
            pst.setString(6, occupation);
            pst.setString(7, pan);
            pst.setString(8, aadhar);
            pst.setString(9, senior);
            pst.setString(10, existing);

            int result = pst.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Data saved successfully!");
                submitted = true;
                dispose();
                new SignupThree();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save data.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    public synchronized SignupTwoData getFormData()
    {
        return data;
    }
}

