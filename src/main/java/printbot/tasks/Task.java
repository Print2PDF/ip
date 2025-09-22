package printbot.tasks;

import printbot.exceptions.CorruptedSaveException;

/**
 * Class contain mark status and content (description) of task
 */
public class Task {

    private boolean isMarked;
    private final String content;

    /**
     * Constructor for Task object
     * @param content task description
     */
    public Task(String content) {
        this.isMarked = false;
        this.content = content;
    }

    /**
     * Function to get task description
     * @return String task description
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Function to check if task is marked, used in writing saves
     * @return boolean, true if mark as done, false if mark as not done
     */
    public boolean isItMarked() {
        return this.isMarked;
    }

    /**
     * Function to mark task as done
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Function to mark task as not done
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * Base string format for a task
     */
    public String toString() {
        String markStatus = this.isMarked ? "X" : " ";
        return String.format("[%s] %s", markStatus, this.content);
        // example: "[X] return book"
    }

    /**
     * Function to write task to save format String
     * @return String save format of task
     */
    public String writeSave() {
        return "task"; // stub
    }

    /**
     * Function to translate save format String to new Task object
     * @param data save format String
     * @return Task new task object
     */
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
            default: throw new CorruptedSaveException();
            }
        } catch (Exception e) {
            throw new CorruptedSaveException();
        }

        if (itIsMarked) {
            task.mark();
        }

        return task;

    }

    /**
     * Function to find keyword in description
     * @param keyword to be search
     * @return boolean, true if keyword in description, else false
     */
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
