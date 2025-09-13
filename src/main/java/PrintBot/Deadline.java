package PrintBot;

// NEW
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Store task name
 * Store end date (deadline)
 * Indicator is [D]
 */
public class Deadline extends Task {

    private LocalDateTime dueDate;
    private boolean hasTime;

    public Deadline(String content) {
        super(content.split("/by ", 2)[0]);
        String[] parts = content.split("/by ", 2);
        //this.dueDate = parts[1];
        this.dueDate = parseDateTime(parts[1]);
    }

    public Deadline(String content, String dueDate) {
        super(content);
        //this.dueDate = dueDate;
        this.dueDate = parseDateTime(dueDate);
    }

    // LEVEL-8: parse datetime
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
        String formattedDate;
        if (hasTime) {
            formattedDate = dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
        } else {
            formattedDate = dueDate.toLocalDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return "[D] " + super.toString() + " (by: " + formattedDate + ")";
    }

    @Override
    public String writeSave() {
        return "D" + "|" + (this.isItMarked() ? "1" : "0") + "|" + this.getContent() + "|" + this.dueDate;
    }

}