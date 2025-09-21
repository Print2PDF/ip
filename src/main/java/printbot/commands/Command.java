package printbot.commands;

import printbot.storage.Storage;
import printbot.tasks.TaskList;
import printbot.ui.UI;

/**
 * Abstract class define functions of subclasses
 * Represent PrintBot storing actions and executing them
 */
public abstract class Command {

    public abstract String execute(TaskList taskList, UI ui, Storage storage);

    public abstract boolean isExit(); // used for non-gui run loop

}
