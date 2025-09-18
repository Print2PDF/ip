package printbot.commands;

import printbot.storage.Storage;
import printbot.tasks.TaskList;
import printbot.ui.UI;

/**
 * Command for default
 */
public class UnknownCommand extends Command {

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.uiErrorMsg("Sorry! I do not know this command.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
