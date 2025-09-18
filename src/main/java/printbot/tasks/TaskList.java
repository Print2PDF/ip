package printbot.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return IntStream.range(0, this.storage.size())
                .mapToObj(i -> String.format("%d. ", i + 1) + this.storage.get(i).toString())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Function to create string of all matching tasks in taskList
     * @param keyword search word to be found
     * @return String representation to all matching tasks
     */
    public String consolidateMatchList(String keyword) {
        return this.storage.stream()
                .filter(task -> task.hasKeyword(keyword))
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Function to get list of tasks, each task in save string format
     * @return List<String></String>
     */
    public List<String> getSaveFormat() {
        return this.storage.stream()
                .map(Task::writeSave)
                .collect(Collectors.toList());
    }

    /**
     * Function to get this.storage size, use for checking index out of bounds
     * @return int size of this.storage
     */
    public int getSize() {
        return this.storage.size();
    }

    /**
     * Function to get task at specified index
     * @param index of task to get
     * @return Task instance at index in this.storage
     */
    public Task getAtIndex(int index) {
        return this.storage.get(index);
    }
}
