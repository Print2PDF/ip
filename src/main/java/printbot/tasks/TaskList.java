package printbot.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Class as container containing all stored Task objects
 */
public class TaskList {

    private final List<Task> storage;

    public TaskList() {
        this.storage = new ArrayList<>();
    }

    /**
     * Function to add task into list
     * @param task to be added
     */
    public void addTask(Task task) {
        assert task != null : "Cannot add a null task";
        this.storage.add(task);
    }

    /**
     * Function to remove task from list and return it
     * @param index of task to be deleted
     * @return Task object that was deleted
     */
    public Task deleteTask(int index) {
        assert index >= 0 : "Index cannot be negative";
        assert index < this.storage.size() : "Index cannot be out of bounds";
        Task removedTask = this.storage.get(index);
        this.storage.remove(index);
        return removedTask;
    }

    /**
     * Function to mark task at specified index
     * @param index of task to be marked
     */
    public void markTask(int index) {
        assert index >= 0 : "Index cannot be negative";
        assert index < this.storage.size() : "Index cannot be out of bounds";
        this.storage.get(index).mark();
    }

    /**
     * Function to unmark task at specified index
     * @param index of task to be unmarked
     */
    public void unmarkTask(int index) {
        assert index >= 0 : "Index cannot be negative";
        assert index < this.storage.size() : "Index cannot be out of bounds";
        this.storage.get(index).unmark();
    }

    /**
     * Function to create string representation of taskList
     * @return String representation of taskList
     */
    public String consolidateTaskList() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < this.storage.size(); i++) {
            Task currTask = this.storage.get(i);
            output.append("\n").append(String.format("%d.", i + 1)).append(currTask.toString());
        }

        return output.toString();
    }

    /**
     * Function to create string of all matching tasks in taskList
     * @param keyword search word to be found
     * @return String representation to all matching tasks
     */
    public String consolidateMatchList(String keyword) {
        StringBuilder output = new StringBuilder();
        int i = 0;

        for (Task currTask : this.storage) {
            if (currTask.hasKeyword(keyword)) {
                i++;
                output.append("\n").append(String.format("%d.", i)).append(currTask.toString());
            }
        }

        return output.toString();
    }

    /*
     * @parameters
     * none
     *
     * @description
     * return string of task list for save
     */
    public List<String> getSaveFormat() {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < this.storage.size(); i++) {
            output.add(this.storage.get(i).writeSave());
        }
        return output;
    }

    /*
     * @parameters
     * none
     *
     * @description
     * return this.storage.size()
     */
    public int getSize() {
        return this.storage.size();
    }

    /*
     * temporary getter
     */
    public Task getAtIndex(int index) {
        return this.storage.get(index);
    }

}
