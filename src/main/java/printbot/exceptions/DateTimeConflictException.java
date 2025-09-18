package printbot.exceptions;

/**
 * Exception thrown if end date is before start date
 */
public class DateTimeConflictException extends Exception {

    public DateTimeConflictException() {
        super("End date cannot be earlier then start date.");
    }
}
