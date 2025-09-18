package printbot.commands;

import printbot.storage.Storage;
import printbot.tasks.TaskList;
import printbot.ui.UI;

/**
 * Class to represent command to find all tasks containing the keyword
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor to set keyword to be searched for
     * @param keyword to be searched for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        String matchList = taskList.consolidateMatchList(keyword);
        return ui.uiFindTask(matchList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
