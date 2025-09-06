package PrintBot;

/*
 * Store task name
 * Indicator is [T]
 */
public class ToDo extends Task {

    public ToDo(String content) {
        super(content);
    }

    /*
     * @factory method
     * create new instance with corresponding mark status
     */
    public static ToDo createToDo(String content, boolean isMarked) {
        ToDo newTask = new ToDo(content);
        if (isMarked) {
            newTask.mark();
        }
        return newTask;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String saveFormat() {
        String markStatus = this.isItMarked() ? "X" : "O";
        return "T," + this.getContent() + "," + markStatus;
    }

}