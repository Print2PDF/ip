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

    /*
     * @parameters
     * Task task
     *
     * @description
     * add task object to task list
     */
    public void addTask(Task task) {
        this.storage.add(task);
    }

    /**
     * Function to remove task from list and return it
     * @param index
     * @return Task object that was deleted
     */
    public Task deleteTask(int index) {
        Task removedTask = this.storage.get(index);
        this.storage.remove(index);
        return removedTask;
    }

    public void markTask(int index) {
        this.storage.get(index).mark();
    }

    public void unmarkTask(int index) {
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

    /*
     * @parameters
     * none
     *
     * @description
     * return string of task list for save
     */
    public List<String> getSaveFormat() {
        return this.storage.stream()
                .map(Task::writeSave)
                .collect(Collectors.toList());
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
