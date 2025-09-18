package printbot.commands;

import printbot.storage.Storage;
import printbot.tasks.Task;
import printbot.tasks.TaskList;
import printbot.exceptions.PrintException;
import printbot.ui.UI;

/**
 * Class represent command to delete task
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor to create delete task for specified index
     * @param index of task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        Task task = taskList.getAtIndex(index);
        taskList.deleteTask(index);
        return ui.uiDeleteTask(task, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}