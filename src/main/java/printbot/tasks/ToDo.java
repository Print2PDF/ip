package printbot.tasks;

/**
 * Class represent Todo task
 */
public class ToDo extends Task {

    public ToDo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String writeSave() {
        return "T" + "|" + (this.isItMarked() ? "1" : "0") + "|" + this.getContent();
    }

}
