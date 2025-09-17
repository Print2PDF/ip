package printbot.tasks;

import printbot.exceptions.CorruptedSaveException;

/*
 * Contain mark status and content of task
 */
public class Task {

    private boolean isMarked;
    private final String content;

    /*
     * Constructor
     * Store content
     * Mark as unmarked (false)
     */
    public Task(String content) {
        this.isMarked = false;
        this.content = content;
    }

    /*
     * Getter
     * Return stored content
     */
    public String getContent() {
        return this.content;
    }

    /*
     * Getter
     * Return mark status
     */
    public boolean isItMarked() {
        return this.isMarked;
    }

    /*
     * Setter
     * Mark task as done (true)
     */
    public void mark() {
        this.isMarked = true;
    }

    /*
     * Setter
     * Unmark task as done (false)
     */
    public void unmark() {
        this.isMarked = false;
    }

    public String toString() {
        String markStatus = this.isMarked ? "X" : " ";
        return String.format("[%s] %s", markStatus, this.content);
        // example: "[X] return book"
    }

    /*
     * @parameters
     * none
     *
     * @description
     * return string of task for save
     */
    public String writeSave() {
        return "task"; // stub
    }

    public static Task readSave(String data) throws CorruptedSaveException {
        String[] parts = data.split("\\|");
        String taskType = parts[0].trim();
        boolean itIsMarked = parts[1].trim().equals("1");

        Task task;

        try {
            switch (taskType) {
                case "T":
                    task = new ToDo(parts[2].trim());
                    break;
                case "D":
                    task = new Deadline(parts[2].trim(), parts[3].trim());
                    break;
                case "E":
                    task = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
                    break;
                default:
                    throw new CorruptedSaveException();
            }
        } catch (Exception e) {
            throw new CorruptedSaveException();
        }

        if (itIsMarked) {
            task.mark();
        }

        return task;

    }

    public boolean hasKeyword(String keyword) {
        String[] words = this.content.split(" ");
        for (String word : words) {
            if (word.trim().equals(keyword)) {
                return true;
            }
        }
        return false;
    }



}