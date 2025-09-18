package printbot.commands;

import printbot.storage.Storage;
import printbot.tasks.Task;
import printbot.tasks.TaskList;
import printbot.ui.UI;

/**
 * Command to add task to taskList
 */
public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        taskList.addTask(this.task);
        return ui.uiAddTask(this.task, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
