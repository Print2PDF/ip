package PrintBot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskList {

    private final List<Task> storage;

    public TaskList() {
        this.storage = new ArrayList<>();
    }

    public TaskList(String save) throws CorruptedSaveException {
        this.storage = new ArrayList<>();

        String[] taskStrings = save.split("/");

        for (String taskString : taskStrings) {
            String[] parts = taskString.split(",");
            if (parts.length == 3) {
                if (!parts[0].equals("T")) {
                    throw new CorruptedSaveException();
                }
                boolean isMarked = Objects.equals(parts[2], "X");
                this.storage.add(ToDo.createToDo(parts[1], isMarked));
            } else if (parts.length == 4) {
                if (!parts[0].equals("D")) {
                    throw new CorruptedSaveException();
                }
                boolean isMarked = Objects.equals(parts[2], "X");
                this.storage.add(Deadline.createDeadline(parts[1], isMarked, parts[3]));
            } else if (parts.length == 5) {
                if (!parts[0].equals("E")) {
                    throw new CorruptedSaveException();
                }
                boolean isMarked = Objects.equals(parts[1], "X");
                this.storage.add(Event.createEvent(parts[1], isMarked, parts[3], parts[4]));
            } else {
                throw new CorruptedSaveException();
            }
        }
    }

    /*
     * @parameters
     * Task task
     *
     * @description
     * add task object to task list, display current number of tasks
     */
    public void addTask(Task task) {
        this.storage.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.printf(" %s\n", task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", this.storage.size());
    }

    /*
     * @parameters
     * int index
     *
     * @description
     * remove task at specified index in task list, display number of remaining tasks
     */
    public void deleteTask(int index) {
        Task removedTask = this.storage.get(index);
        this.storage.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.printf(" %s\n", removedTask.toString());
        System.out.printf("Now you have %d tasks in the list.\n", this.storage.size());
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
     * print task list in order of initial storage
     */
    public void displayTaskList() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < this.storage.size(); i++) {
            Task currTask = this.storage.get(i);
            System.out.printf("%d. %s\n", i + 1, currTask.toString());
        }
    }

    /*
     * @parameters
     * none
     *
     * @description
     * return string of task list for save
     */
    public String saveFormat() {
        return "";
        // stub
    }

    /*
     * @parameters
     * none
     *
     * @description
     * return this.storage.size()
     */
    public int getTaskListSize() {
        return this.storage.size();
    }

}
