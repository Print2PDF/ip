package printbot.exceptions;

// Level-7

public class CorruptedSaveException extends Exception {

    public CorruptedSaveException() {
        super("Oh no! The save is corrupted!");
    }

}