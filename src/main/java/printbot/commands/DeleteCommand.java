package printbot.commands;

import printbot.storage.Storage;
import printbot.tasks.Task;
import printbot.tasks.TaskList;
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
        assert index >= 0 : "Cannot delete when index is negative";
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        assert taskList != null : "Cannot delete from a null task list";
        Task task = taskList.deleteTask(index);
        return ui.uiDeleteTask(task, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
