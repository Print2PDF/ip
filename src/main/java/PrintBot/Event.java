package PrintBot;

/*
 * Store task name
 * Store start date and end date (duration of event)
 * Indicator is [E]
 */
public class Event extends Task {

    private String startDate;
    private String endDate;

    public Event(String content) {
        super(content.split("/from ", 2)[0]);
        String[] parts = content.split("/from ", 2);
        parts = parts[1].split("/to ", 2);
        this.startDate = parts[0];
        this.endDate = parts[1];
    }

    public Event(String content, String startDate, String endDate) {
        super(content);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /*
     * @factory method
     * create new instance with corresponding mark status
     */
    public static Event createEvent(String content, boolean isMarked, String startDate, String endDate) {
        Event newTask = new Event(content, startDate, endDate);
        if (isMarked) {
            newTask.mark();
        }
        return newTask;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }

    @Override
    public String writeSave() {
        return "E" + "|" + (this.isItMarked() ? "1" : "0") + "|" + this.getContent() + "|" + this.startDate + "|" + this.endDate;
    }

}