package printbot.exceptions;

/**
 * Exception thrown for input with wrong event format
 */
public class FormatEventException extends Exception {

    public FormatEventException() {
        super("Wrong format, use: event <content> /from <start_date> /to <end_date>");
    }
}
