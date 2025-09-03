package ScriptKiddie;

public class Event extends Task {

    private final String startDate;
    private final String endDate;

    public Event(String content, String startDate, String endDate) {
        super(content);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }

}
