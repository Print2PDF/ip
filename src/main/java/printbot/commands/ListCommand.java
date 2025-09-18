package printbot.commands;

import printbot.storage.Storage;
import printbot.tasks.TaskList;
import printbot.ui.UI;

/**
 * Class represent command to display list of stored tasks
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        assert taskList != null : "Cannot consolidate a null task list";
        return ui.uiPrintTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
