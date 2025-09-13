package printbot.tasks;

import java.util.ArrayList;
import java.util.List;

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

    /*
     * @parameters
     * int index
     *
     * @description
     * remove task at specified index in task list, return removed task
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

    /*
     * @parameters
     * none
     *
     * @description
     * return task list as single string in order of initial storage
     */
    public String consolidateTaskList() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < this.storage.size(); i++) {
            Task currTask = this.storage.get(i);
            output.append("\n").append(String.format("%d.", i + 1)).append(currTask.toString());
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
