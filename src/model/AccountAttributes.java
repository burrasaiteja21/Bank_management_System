package model;

public class AccountAttributes {

    /* 
     * The accountNumber field stores the unique account number for the customer.
     */
    private String accountNumber;

    /* 
     * The balance field stores the current balance available in the account.
     */
    private int balance;

    /* 
     * The overdraft field stores the overdraft limit available for the account. 
     * This allows a customer to withdraw beyond the balance if the account type supports it.
     */
    private int overdraft;

    /* 
     * Constructor to initialize the AccountAttributes object with account number, balance, and overdraft.
     * @param accountNumber: The unique account number for the customer.
     * @param balance: The current balance in the account.
     * @param overdraft: The overdraft limit for the account.
     */
    public AccountAttributes(String accountNumber, int balance, int overdraft) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.overdraft = overdraft;
    }

    /* 
     * Getter method to retrieve the account number of the customer.
     * @return The account number of the customer.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /* 
     * Getter method to retrieve the current balance in the account.
     * @return The current balance in the account.
     */
    public int getBalance() {
        return balance;
    }

    /* 
     * Getter method to retrieve the overdraft limit for the account.
     * @return The overdraft limit for the account.
     */
    public int getOverdraft() {
        return overdraft;
    }
}
