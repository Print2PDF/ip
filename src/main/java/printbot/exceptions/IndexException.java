package printbot.exceptions;

/**
 * Exception thrown if index is out of bounds of taskList
 */
public class IndexException extends Exception {

    public IndexException() {
        super("Invalid task number!");
    }
}
