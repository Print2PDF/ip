package printbot.commands;

import printbot.storage.Storage;
import printbot.tasks.TaskList;
import printbot.ui.UI;

/**
 * Command to display help information with all available commands
 */
public class HelpCommand extends Command {

    /**
     * Constructor for HelpCommand
     */
    public HelpCommand() {}

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.uiShowHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
