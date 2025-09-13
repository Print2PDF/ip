package printbot.commands;

import printbot.tasks.TaskList;
import printbot.ui.UI;

public abstract class Command {

    //all must have execute(...);

    public abstract boolean isExit();

}
