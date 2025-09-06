package PrintBot;

/*
 * Store task name
 * Store end date (deadline)
 * Indicator is [D]
 */
public class Deadline extends Task {

    private final String dueDate;

    public Deadline(String content, String dueDate) {
        super(content);
        this.dueDate = dueDate;
    }

    /*
     * @factory method
     * create new instance with corresponding mark status
     */
    public static Deadline createDeadline(String content, boolean isMarked, String dueDate) {
        Deadline newTask = new Deadline(content, dueDate);
        if (isMarked) {
            newTask.mark();
        }
        return newTask;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.dueDate + ")";
    }

    @Override
    public String saveFormat() {
        String markStatus = this.isItMarked() ? "X" : "O";
        return "D," + this.getContent() + "," + markStatus + "," + this.dueDate;
    }

}
