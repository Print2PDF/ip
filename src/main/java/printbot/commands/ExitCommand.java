package printbot.commands;

import printbot.storage.Storage;
import printbot.tasks.TaskList;
import printbot.ui.UI;

/**
 * Command to exit PrintBot application
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.uiByeUser();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
