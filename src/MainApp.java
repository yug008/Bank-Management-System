import java.sql.*;

public class MainApp {
    public static void main(String[] args) {
        Login loginScreen = new Login();

        while (!loginScreen.signupRequested) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        SignupOne signupForm = new SignupOne();
        while (!signupForm.submitted) {
            try { Thread.sleep(200);
            }
            catch (InterruptedException ignored) {

            }
        }
        SignupOneData data1 = signupForm.getFormData();
        String formNo = data1.formNo;

        SignupTwo signupTwoForm = new SignupTwo(formNo);
        while (!signupTwoForm.submitted) {
            try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        }
        SignupTwoData data2 = signupTwoForm.getFormData();
        if (data2 != null) {
            insertToSignupTwo(formNo, data2);
        }

        SignupThree signupThreeForm = new SignupThree();
        while (!signupThreeForm.submitted) {
            try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        }
        SignupThreeData data3 = signupThreeForm.getFormData();
        if (data3 != null) {
            insertToSignupThree(formNo, data3);
        }

        System.out.println("Signup completed for formNo: " + formNo);
    }

    // Insert data into SignupTwo table
    private static void insertToSignupTwo(String formNo, SignupTwoData data) {
        String sql = "INSERT INTO signuptwo "
                + "(formno, religion, category, income, qualification, occupation, pan, aadhar, senior_citizen, existing_account) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, formNo);
            pstmt.setString(2, data.religion);
            pstmt.setString(3, data.category);
            pstmt.setString(4, data.income);
            pstmt.setString(5, data.qualification);
            pstmt.setString(6, data.occupation);
            pstmt.setString(7, data.pan);
            pstmt.setString(8, data.aadhar);
            pstmt.setString(9, (data.seniorCitizen == null || data.seniorCitizen.isEmpty()) ? "No" : data.seniorCitizen);
            pstmt.setString(10, (data.existingAccount == null || data.existingAccount.isEmpty()) ? "No" : data.existingAccount);

            int count = pstmt.executeUpdate();
            System.out.println("Inserted " + count + " record(s) into signuptwo for formNo: " + formNo);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL Exception in SignupTwo insert");
        }
    }

    // Insert data into SignupThree table
    private static void insertToSignupThree(String formNo, SignupThreeData data) {
        String sql = "INSERT INTO signupthree "
                + "(formno, estatement, emailsms, chequebook, mobilebanking, internetbanking, atmcard, "
                + "savingaccount, fixedepositaccount, currentaccount, recurringdepositaccount, pin) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // added pin column

        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, formNo);
            pstmt.setString(2, data.estatement);
            pstmt.setString(3, data.emailsms);
            pstmt.setString(4, data.chequebook);
            pstmt.setString(5, data.mobilebanking);
            pstmt.setString(6, data.internetbanking);
            pstmt.setString(7, data.atmcard);
            pstmt.setString(8, data.savingaccount);
            pstmt.setString(9, data.fixedepositaccount);
            pstmt.setString(10, data.currentaccount);
            pstmt.setString(11, data.recurringdepositaccount);
            pstmt.setString(12, data.pin);                               // must collect/set pin in SignupThreeData

            int count = pstmt.executeUpdate();
            System.out.println("Inserted " + count + " record(s) into signupthree for formNo: " + formNo);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL Exception in SignupThree insert");
        }
    }
}




