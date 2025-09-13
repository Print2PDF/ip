package printbot.commands;

import printbot.tasks.Task;
import printbot.tasks.TaskList;
import printbot.ui.UI;

public class AddCommand extends Command {

    public void execute(UI ui, TaskList taskList, Task task) {
        taskList.addTask(task);
        ui.uiAddTask(task, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
