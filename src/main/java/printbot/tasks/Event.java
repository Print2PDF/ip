package printbot.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Store task name
 * Store start date and end date (duration of event)
 * Indicator is [E]
 */
public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;
    private boolean hasTime;

    public Event(String content) {
        super(content.split("/from ", 2)[0]);
        String[] parts = content.split("/from ", 2);
        parts = parts[1].split("/to ", 2);
        this.start = parseDateTime(parts[0]);
        this.end = parseDateTime(parts[1]);
    }

    public Event(String content, String from, String to) {
        super(content);
        this.start = parseDateTime(from);
        this.end = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String dateTimeString){
        try {
            // Format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)
            if (dateTimeString.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{4}")) {
                String[] parts = dateTimeString.split(" ");
                String[] dateParts = parts[0].split("/");
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                int hour = Integer.parseInt(parts[1].substring(0, 2));
                int minute = Integer.parseInt(parts[1].substring(2, 4));

                this.hasTime = true;
                return LocalDateTime.of(year, month, day, hour, minute);
            }

            // Format: yyyy-MM-dd HHmm (e.g. 2019-10-15 1800)
            if (dateTimeString.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}")) {
                this.hasTime = true;
                return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            }

            // Format: yyyy-MM-dd (e.g. 2019-10-15)
            if (dateTimeString.matches("\\d{4}-\\d{2}-\\d{2}")) {
                this.hasTime = false;
                LocalDate date = LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return date.atStartOfDay();
            }

            // treat as date only
            this.hasTime = false; // i.e. still false
            return LocalDate.now().atStartOfDay();
        } catch (Exception e) {
            this.hasTime = false;
            return LocalDate.now().atStartOfDay();
        }
    }

    @Override
    public String toString() {
        String formattedStart, formattedEnd;
        if (hasTime) {
            formattedStart = start.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
            formattedEnd = end.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
        } else {
            formattedStart = start.toLocalDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            formattedEnd = end.toLocalDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return "[E] " + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String writeSave() {
        return "E" + "|" + (this.isItMarked() ? "1" : "0") + "|" + this.getContent() + "|" + this.start + "|" + this.end;
    }

}