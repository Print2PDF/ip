package printbot.commands;

import printbot.storage.Storage;
import printbot.tasks.Task;
import printbot.tasks.TaskList;
import printbot.ui.UI;

/**
 * Class represent command to mark specified task at index
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructor to create mark task at specified index
     * @param index of task in taskList to be marked
     */
    public UnmarkCommand(int index) {
        assert index >= 0 : "Cannot unmark a task at negative index";
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        assert taskList != null : "Cannot unmark a task in a null task list";
        taskList.unmarkTask(this.index);
        Task task = taskList.getAtIndex(this.index);
        return ui.uiUnmarkTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
