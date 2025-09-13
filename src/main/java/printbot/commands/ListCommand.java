package printbot.commands;

import printbot.tasks.TaskList;
import printbot.ui.UI;

public class ListCommand extends Command {

    public ListCommand() {}

    public void execute(UI ui, TaskList taskList) {
        ui.uiPrintTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
