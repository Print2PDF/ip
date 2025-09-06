package PrintBot;

/*
 * Store task name
 * Store start date and end date (duration of event)
 * Indicator is [E]
 */
public class Event extends Task {

    private final String startDate;
    private final String endDate;

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
    public String saveFormat() {
        String markStatus = this.isItMarked() ? "X" : "O";
        return "E," + this.getContent() + "," + markStatus + "," + this.startDate + "," + this.endDate;
    }

}