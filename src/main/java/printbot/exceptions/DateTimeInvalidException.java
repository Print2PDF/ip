package printbot.exceptions;

/**
 * Exception thrown when date format is invalid
 */
public class DateTimeInvalidException extends Exception {

    public DateTimeInvalidException() {
        super("Invalid datetime format: Use dd/MM/yyyy HH:mm");
    }
}
