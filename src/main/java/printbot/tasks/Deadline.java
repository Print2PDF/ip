package printbot.tasks;

// NEW

import java.time.LocalDateTime;

import printbot.exceptions.DateTimeInvalidException;
import printbot.parser.Parser;

/**
 * Class represent Deadline task
 */
public class Deadline extends Task {

    private LocalDateTime dueDate;

    /**
     * Default constructor parses content and datetime, used in PrintBot v0.1
     * @param content as full user input
     */
    public Deadline(String content) throws DateTimeInvalidException {
        super(content.split("/by ", 2)[0]);
        String[] parts = content.split("/by ", 2);
        //this.dueDate = parts[1];
        this.dueDate = Parser.parseDateTime(parts[1]);
    }

    /**
     * Constructor if content and due date are separated
     * @param content as task description
     * @param dueDate as deadline of task
     */
    public Deadline(String content, String dueDate) throws DateTimeInvalidException {
        super(content);
        this.dueDate = Parser.parseDateTime(dueDate);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + Parser.dateToString(this.dueDate) + ")";
    }

    @Override
    public String writeSave() {
        String date = String.format("%02d", this.dueDate.getDayOfMonth()) + "/" + this.dueDate.getMonthValue()
                + "/" + this.dueDate.getYear() + " " + this.dueDate.getHour() + ":" + this.dueDate.getMinute();
        return "D | " + (this.isItMarked() ? "1" : "0") + " | " + this.getContent() + " | " + date;
    }

}
