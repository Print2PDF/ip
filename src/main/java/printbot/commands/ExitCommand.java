package printbot.commands;

import printbot.tasks.TaskList;
import printbot.ui.UI;

public class ExitCommand extends Command {

    public ExitCommand() {}

    public void execute(UI ui) {
        ui.uiByeUser();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
