package PrintBot;

/*
 * Store task name
 * Store end date (deadline)
 * Indicator is [D]
 */
public class Deadline extends Task {

    private String dueDate;

    public Deadline(String content) {
        super(content.split("/by ", 2)[0]);
        String[] parts = content.split("/by ", 2);
        this.dueDate = parts[1];
    }

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
    public String writeSave() {
        return "D" + "|" + (this.isItMarked() ? "1" : "0") + "|" + this.getContent() + "|" + this.dueDate;
    }

}
