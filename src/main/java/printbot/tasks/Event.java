package printbot.tasks;

import java.time.LocalDateTime;

import printbot.exceptions.DateTimeInvalidException;
import printbot.parser.Parser;

/**
 * Class represent Event task
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Default constructor parses content and datetime
     * @param content as full user input
     */
    public Event(String content) throws DateTimeInvalidException {
        super(content.split("/from ", 2)[0]);
        String[] parts = content.split("/from ", 2);
        parts = parts[1].split("/to ", 2);
        this.start = Parser.parseDateTime(parts[0]);
        this.end = Parser.parseDateTime(parts[1]);
    }

    /**
     * Constructor if content and dates are separated
     * @param content as task description
     * @param from as start date of task
     * @param to as end date of task
     */
    public Event(String content, String from, String to) throws DateTimeInvalidException {
        super(content);
        this.start = Parser.parseDateTime(from);
        this.end = Parser.parseDateTime(to);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + Parser.dateToString(start)
                + " to: " + Parser.dateToString(end) + ")";
    }

    @Override
    public String writeSave() {
        String startDate = String.format("%02d", this.start.getDayOfMonth()) + "/" + this.start.getMonthValue()
                + "/" + this.start.getYear() + " " + this.start.getHour() + ":" + this.start.getMinute();
        String endDate = String.format("%02d", this.end.getDayOfMonth()) + "/" + this.end.getMonthValue()
                + "/" + this.end.getYear() + " " + this.end.getHour() + ":" + this.end.getMinute();
        return "D | " + (this.isItMarked() ? "1" : "0") + " | " + this.getContent()
                + " | " + startDate + " | " + endDate;
    }

}
