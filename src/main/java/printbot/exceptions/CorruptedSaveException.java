package printbot.exceptions;

// Level-7

/**
 * Exception thrown if save file is not found or in the incorrect format
 * Use in ui, but not gui
 */
public class CorruptedSaveException extends Exception {

    public CorruptedSaveException() {
        super("Oh no! The save is corrupted!");
    }

}
