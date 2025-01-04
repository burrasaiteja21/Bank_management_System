package model;

public class Status {

    /*
     * The message field stores the message associated with the status.
     * This message can be used to communicate success or failure details.
     */
    private String message;

    /*
     * The isError field indicates whether the status represents an error.
     * If true, it signifies an error; otherwise, it indicates success or a non-error status.
     */
    private boolean isError;

    /*
     * Getter method for the message field.
     * Returns the status message.
     */
    public String getMessage() {
        return message;
    }

    /*
     * Getter method for the isError field.
     * Returns true if the status represents an error; false otherwise.
     */
    public boolean getIsError() {
        return isError;
    }

    /*
     * Constructor to initialize the Status object with a message and an error flag.
     * @param message: The message describing the status.
     * @param isError: A boolean indicating whether the status represents an error (true) or not (false).
     */
    public Status(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }
}
