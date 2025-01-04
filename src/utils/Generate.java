package utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Generate {

    /*
     * This method generates a unique ID by creating a random number between 1000000 and 9999999 (inclusive).
     * It uses the Random class to generate the number and returns it as a string.
     * 
     * @return The generated ID as a string.
     */
    public static String generateID() {
        Random random = new Random();
        /*
         * Generate a number between 1000000 and 9999999 (inclusive)
         */
        int id = 1000000 + random.nextInt(9000000);
        return Integer.toString(id);
    }

    /*
     * This method generates a random 20-digit account number.
     * It uses the Random class to generate each digit and appends it to a StringBuilder.
     * 
     * @return The generated 20-digit account number as a string.
     */
    public static String generateAccountNumber() {
        Random random = new Random();
        StringBuilder number = new StringBuilder();

        /*
         * Generate 20 digits
         */
        for (int i = 0; i < 20; i++) {
            int digit = random.nextInt(10); // Generate a single digit (0-9)
            number.append(digit);
        }

        return number.toString();
    }

    /*
     * This method generates a SHA-256 hash from the given input string.
     * It uses the MessageDigest class to compute the hash and returns it as a hexadecimal string.
     * 
     * @param input The string to be hashed.
     * @return The generated hash as a hexadecimal string.
     * @throws NoSuchAlgorithmException If the specified hashing algorithm is not available.
     */
    public static String generateHash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        /*
         * Perform the hash computation
         */
        byte[] hashBytes = digest.digest(input.getBytes());

        /*
         * Convert byte array to a hexadecimal string
         */
        StringBuilder hashHex = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hashHex.append('0');
            }
            hashHex.append(hex);
        }

        return hashHex.toString();
    }

    /*
     * This method generates a unique transaction ID by creating a random 10-digit number.
     * It uses the Random class to generate each digit and appends it to a StringBuilder.
     * 
     * @return The generated 10-digit transaction ID as a string.
     */
    public static String generateTransactionID() {
        Random random = new Random();
        StringBuilder TransactionID = new StringBuilder();

        /*
         * Generate 10 digits
         */
        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10); /*
                *Generate a digit between (0-9)
             */ 
            TransactionID.append(digit);
        }

        return TransactionID.toString();
    }
}
