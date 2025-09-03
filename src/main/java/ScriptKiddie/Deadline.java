package ScriptKiddie;

/*
 * Contain content
 * Indicator is [D]
 * Add startDate to string representation
 */
public class Deadline extends Task {

    private final String startDate;

    public Deadline(String content, String startDate) {
        super(content);
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.startDate + ")";
    }

}
