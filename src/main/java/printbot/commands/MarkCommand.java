package printbot.commands;

import printbot.storage.Storage;
import printbot.tasks.Task;
import printbot.tasks.TaskList;
import printbot.ui.UI;

/**
 * Class represent command to mark specified task at index
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructor to create mark task at specified index
     * @param index of task in taskList to be marked
     */
    public MarkCommand(int index) {
        assert index >= 0 : "Cannot mark a task at negative index";
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        assert taskList != null : "Cannot mark a task in a null task list";
        taskList.markTask(this.index);
        storage.writeSaveFile(taskList);
        Task task = taskList.getAtIndex(this.index);
        return ui.uiMarkTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
