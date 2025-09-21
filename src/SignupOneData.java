public class SignupOneData {
    public String formNo;
    public String name;
    public String fatherName;
    public String dob;
    public String gender;
    public String email;
    public String maritalStatus;
    public String address;
    public String city;
    public String state;
    public String pin;

    public SignupOneData(String formNo, String name, String fatherName, String dob,
                      String gender, String email, String maritalStatus,
                      String address, String city, String state, String pin) {
        this.formNo = formNo;
        this.name = name;
        this.fatherName = fatherName;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.maritalStatus = maritalStatus;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pin = pin;
    }

    public static void main(String[] args) {

    }
}






/* We want to take the user's info after the details are filled and the 'NEXT' is clicked. Those info is stored
in the 'actionperformed' method. But the data is stored in local variables present in 'actionperformed' method
 meaning other classes can't access them. Thus, we will create another class 'SignupData' and create a method
 'getFormData()' which takes all the values inside 'actionPerformed()'.

 This constructor will be called when an instance of this class is returned (created) in getFormData() method.
 */
