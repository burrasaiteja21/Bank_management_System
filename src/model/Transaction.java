package model;

public class Transaction {
    
    /*
     * TransactionId represents the unique identifier for the transaction.
     * It is used to track and reference a specific transaction in the system.
     */
    private String TransactionId;

    /*
     * FromAccount stores the account number from which the funds are withdrawn.
     * This is the sender's account in the transaction.
     */
    private String FromAccount;

    /*
     * ToAccount stores the account number where the funds are deposited.
     * This is the recipient's account in the transaction.
     */
    private String ToAccount;

    /*
     * Amount represents the value of the transaction in the relevant currency.
     * It indicates how much money is transferred in the transaction.
     */
    private int Amount;

    /*
     * DateTime stores the timestamp of when the transaction occurred.
     * It is typically represented in milliseconds since the Unix epoch.
     */
    private String DateTime;

    /*
     * Default constructor for the Transaction class.
     * It initializes the transaction with default values.
     */
    public Transaction() {
    }

    /*
     * Parameterized constructor to create a Transaction object with the provided
     * values for transaction ID, sender's account, recipient's account, amount, and timestamp.
     * @param TransactionId: The unique identifier for the transaction.
     * @param FromAccount: The account number from which the funds are transferred.
     * @param ToAccount: The account number to which the funds are transferred.
     * @param Amount: The amount of money transferred in the transaction.
     * @param DateTime: The timestamp when the transaction took place.
     */
    public Transaction(String TransactionId, String FromAccount, String ToAccount, int Amount, String DateTime) {
        this.TransactionId = TransactionId;
        this.FromAccount = FromAccount;
        this.ToAccount = ToAccount;
        this.Amount = Amount;
        this.DateTime = DateTime;
    }

    /*
     * Constructor to create a Transaction object with a generated transaction ID 
     * and the provided values for sender's account, recipient's account, and amount.
     * The DateTime is set to the current time in milliseconds.
     * @param FromAccount: The account number from which the funds are transferred.
     * @param ToAccount: The account number to which the funds are transferred.
     * @param Amount: The amount of money transferred in the transaction.
     */
    public Transaction(String FromAccount, String ToAccount, int Amount) {
        this.TransactionId = utils.Generate.generateTransactionID(); // Generates a unique transaction ID
        this.FromAccount = FromAccount;
        this.ToAccount = ToAccount;
        this.Amount = Amount;
        this.DateTime = Long.toString(System.currentTimeMillis()); // Set the current time as DateTime
    }

    /*
     * Getter method for the transaction ID.
     * @return TransactionId: The unique identifier of the transaction.
     */
    public String getTransactionId() {
        return TransactionId;
    }

    /*
     * Getter method for the sender's account number.
     * @return FromAccount: The account number from which funds are transferred.
     */
    public String getFromAccount() {
        return FromAccount;
    }

    /*
     * Getter method for the recipient's account number.
     * @return ToAccount: The account number to which funds are transferred.
     */
    public String getToAccount() {
        return ToAccount;
    }

    /*
     * Getter method for the transaction amount.
     * @return Amount: The amount of money transferred in the transaction.
     */
    public int getAmount() {
        return Amount;
    }

    /*
     * Getter method for the transaction timestamp.
     * @return DateTime: The timestamp when the transaction occurred.
     */
    public String getDateTime() {
        return DateTime;
    }
}
