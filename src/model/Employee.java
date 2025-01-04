package model;

public class Employee {

    /*
     * The id field stores the unique identifier for the employee.
     */
    private String id;

    /*
     * The name field stores the employee's name.
     */
    private String name;

    /*
     * The address field stores the employee's address.
     */
    private String address;

    /*
     * The email field stores the employee's email address.
     */
    private String email;

    /*
     * The password field stores the hashed password for the employee's login.
     */
    private String password;

    /*
     * The contactNumber field stores the employee's contact number.
     */
    private String contactNumber;

    private String recoveryPhrase;

    /*
     * Getter method for the employee's unique identifier (ID).
     */
    public String getId() {
        return id;
    }

    /*
     * Getter method for the employee's name.
     */
    public String getName() {
        return name;
    }

    /*
     * Getter method for the employee's address.
     */
    public String getAddress() {
        return address;
    }

    /*
     * Getter method for the employee's email address.
     */
    public String getEmail() {
        return email;
    }

    /*
     * Getter method for the employee's password.
     */
    public String getPassword() {
        return password;
    }

    /*
     * Getter method for the employee's contact number.
     */
    public String getContactNumber() {
        return contactNumber;
    }

    public String getRecoveryPhrase() {
        return recoveryPhrase;
    }

    /*
     * Constructor to initialize an Employee object with the provided details.
     * @param name: The name of the employee.
     * @param address: The address of the employee.
     * @param email: The email address of the employee.
     * @param password: The password for the employee's login.
     * @param contactNumber: The contact number of the employee.
     * The constructor automatically generates a unique ID and hashes the password.
     */
    public Employee(String name, String address, String email, String password, String contactNumber,String recoveryPhrase) {
        this.name = name;
        this.address = address;
        this.email = email;
        try {
            this.password = utils.Generate.generateHash(password); /**Hash the password using a utility method
             */ 
            this.recoveryPhrase = utils.Generate.generateHash(recoveryPhrase);
        } catch (Exception e) {
            /**
             *This exception should never occur if password hashing works correctly  */ 
        }
        this.contactNumber = contactNumber;
        this.id = utils.Generate.generateID(); /**Automatically generate a unique ID for the employee
         */ 
    }

}
