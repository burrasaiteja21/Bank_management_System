package model;

public class Customer {

    /*
     * The name field stores the customer's name.
     */
    private String name;

    /*
     * The address field stores the customer's address.
     */
    private String address;

    /*
     * The password field stores the customer's password.
     */
    private String password;

    /*
     * The email field stores the customer's email address.
     */
    private String email;

    /*
     * The contactNumber field stores the customer's contact number.
     */
    private String contactNumber;

    /*
     * The aadhar field stores the customer's Aadhar number.
     */
    private String aadhar;

    /*
     * The pan field stores the customer's PAN (Permanent Account Number).
     */
    private String pan;

    /*
     * The ssn field stores the customer's Social Security Number (SSN).
     */
    private String ssn;

    /*
     * The balance field stores the customer's account balance.
     */
    private int balance;

    /*
     * The accountNumber field stores the unique account number for the customer.
     */
    private String accountNumber;

    /*
     * The overdraft field stores the overdraft limit, if applicable, for the customer's account.
     */
    private int overdraft;

    /*
     * The accountType field stores the type of the customer's account (e.g., 0 for savings, 1 for current).
     */
    private int accountType;

    /*
     * The gender field stores the customer's gender.
     */
    private String gender;

    /*
     * The dob field stores the customer's date of birth.
     */
    private String dob;

    /*
     * The maritalStatus field stores the customer's marital status.
     */
    private String maritalStatus;

    private int status;

    /*
     * Constructor to initialize a Customer object with all details provided.
     * @param name: The name of the customer.
     * @param address: The address of the customer.
     * @param email: The email of the customer.
     * @param contactNumber: The contact number of the customer.
     * @param aadhar: The Aadhar number of the customer.
     * @param pan: The PAN number of the customer.
     * @param ssn: The SSN of the customer.
     * @param balance: The balance of the customer's account.
     * @param accountNumber: The unique account number of the customer.
     * @param overdraft: The overdraft limit (if any) of the customer's account.
     * @param accountType: The type of account (savings or current).
     * @param gender: The gender of the customer.
     * @param dob: The date of birth of the customer.
     * @param maritalStatus: The marital status of the customer.
     */
    public Customer(String name, String address, String email, String contactNumber, String aadhar, String pan,
            String ssn, int balance, String accountNumber, int overdraft, int accountType, String gender, String dob,
            String maritalStatus,int status) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.contactNumber = contactNumber;
        this.aadhar = aadhar;
        this.pan = pan;
        this.ssn = ssn;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.overdraft = overdraft;
        this.accountType = accountType;
        this.gender = gender;
        this.dob = dob;
        this.maritalStatus = maritalStatus;
        this.status = status;
    }

    /*
     * Default constructor.
     */
    public Customer() {
        
    }

    /*
     * Another constructor to initialize Customer with selected attributes.
     * @param name: The name of the customer.
     * @param address: The address of the customer.
     * @param email: The email of the customer.
     * @param contactNumber: The contact number of the customer.
     * @param aadhar: The Aadhar number of the customer.
     * @param pan: The PAN number of the customer.
     * @param ssn: The SSN of the customer.
     * @param balance: The balance of the customer's account.
     * @param overdraft: The overdraft limit of the customer's account.
     * @param accountType: The type of the customer's account.
     * @param gender: The gender of the customer.
     * @param dob: The date of birth of the customer.
     * @param maritalStatus: The marital status of the customer.
     */
    public Customer(String name, String address, String email, String contactNumber, String aadhar,
            String pan, String ssn, int balance, int overdraft, int accountType, String gender, String dob,
            String maritalStatus) {
        this.status = 1;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
        this.maritalStatus = maritalStatus;
        try {
            this.password = utils.Generate.generateHash(pan.toUpperCase() + name); // Generating password based on PAN and name
        } catch (Exception e) {
            /*
            *Exception handling if password generation fails
             */ 
        }
        this.email = email;
        this.contactNumber = contactNumber;
        this.aadhar = aadhar;
        this.pan = pan;
        this.ssn = ssn;
        this.balance = balance;
        this.accountNumber = utils.Generate.generateAccountNumber(); // Automatically generating account number
        this.accountType = accountType;
        if (accountType == 0) {
            this.overdraft = 0; /**No overdraft for savings account
             */ 
        } else {
            this.overdraft = overdraft; /*
            *Overdraft available for current account
            */ 
        }
    }

    public int getStatus() {
        return status;
    }

    /*
     * Getter method for the gender of the customer.
     */
    public String getGender() {
        return gender;
    }

    /*
     * Getter method for the date of birth of the customer.
     */
    public String getDob() {
        return dob;
    }

    /*
     * Getter method for the marital status of the customer.
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /*
     * Getter method for the overdraft limit of the customer.
     */
    public int getOverdraft() {
        return overdraft;
    }

    /*
     * Getter method for the type of account (savings or current).
     */
    public int getAccountType() {
        return accountType;
    }

    /*
     * Getter method for the name of the customer.
     */
    public String getName() {
        return name;
    }

    /*
     * Getter method for the address of the customer.
     */
    public String getAddress() {
        return address;
    }

    /*
     * Getter method for the password of the customer.
     */
    public String getPassword() {
        return password;
    }

    /*
     * Getter method for the email of the customer.
     */
    public String getEmail() {
        return email;
    }

    /*
     * Getter method for the contact number of the customer.
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /*
     * Getter method for the Aadhar number of the customer.
     */
    public String getAadhar() {
        return aadhar;
    }

    /*
     * Getter method for the PAN number of the customer.
     */
    public String getPan() {
        return pan;
    }

    /*
     * Getter method for the SSN of the customer.
     */
    public String getSsn() {
        return ssn;
    }

    /*
     * Getter method for the balance of the customer's account.
     */
    public int getBalance() {
        return balance;
    }

    /*
     * Getter method for the account number of the customer.
     */
    public String getAccountNumber() {
        return accountNumber;
    }
}
