package ScriptKiddie;

// Level-3

/*
 * Contain mark status and content of task
 */
public class Task {

    private boolean isMarked;
    private final String content;

    /*
     * Constructor
     * Store content
     * Mark as unmarked (false)
     */
    public Task(String content) {
        this.isMarked = false;
        this.content = content;
    }

    /*
     * Getter
     * Return stored content
     */
    public String getContent() {
        return this.content;
    }

    /*
     * Getter
     * Return mark status
     */
    public boolean isItMarked() {
        return this.isMarked;
    }

    /*
     * Setter
     * Mark task as done (true)
     */
    public void mark() {
        this.isMarked = true;
    }

    /*
     * Setter
     * Unmark task as done (false)
     */
    public void unmark() {
        this.isMarked = false;
    }

    public String toString() {
        String markStatus = this.isMarked ? "X" : " ";
        return String.format("[%s] %s", markStatus, this.content);
        // example: "[X] return book"
    }

}
