package printbot.exceptions;

/**
 * Exception thrown if entered content for task is empty
 */
public class TaskNameEmptyException extends Exception {
    public TaskNameEmptyException() {
        super("Task description is empty!");
    }
}
