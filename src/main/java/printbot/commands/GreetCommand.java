package printbot.commands;

import printbot.storage.Storage;
import printbot.tasks.TaskList;
import printbot.ui.UI;

/**
 * Command to greet user
 */
public class GreetCommand extends Command {

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.uiGreetUser();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
