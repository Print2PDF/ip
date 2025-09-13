package printbot.commands;

import printbot.exceptions.PrintException;
import printbot.tasks.Task;
import printbot.tasks.TaskList;
import printbot.ui.UI;

public class UnmarkCommand extends Command {

    public void execute(UI ui, TaskList taskList, int index) throws PrintException {
        try {
            Task task = taskList.getAtIndex(index);
            taskList.unmarkTask(index);
            ui.uiUnmarkTask(task);
        } catch (Exception e) {
            throw new PrintException("Index out of bounds!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
