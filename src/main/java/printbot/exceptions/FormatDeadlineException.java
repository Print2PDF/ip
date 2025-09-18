package printbot.exceptions;

/**
 * Exception thrown for input with wrong deadline format
 */
public class FormatDeadlineException extends Exception {

    public FormatDeadlineException() {
        super("Wrong format, use: deadline <content> /by <datetime>");
    }
}
