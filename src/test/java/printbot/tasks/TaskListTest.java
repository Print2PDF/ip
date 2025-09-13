package printbot.tasks;

import printbot.tasks.ToDo;
import printbot.tasks.Deadline;
import printbot.tasks.Task;
import printbot.tasks.TaskList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    /*
     * Function to test if adding and deleting a task from a tasklist yields the same task instance
     */
    @Test
    @DisplayName("Add and delete task: should return same task")
    void add_and_delete_task() throws Exception {
        Deadline addedTask = new Deadline("return book", "6/9/2025 1700");
        TaskList taskList = new TaskList();

        taskList.addTask(new ToDo("read book"));
        taskList.addTask(addedTask);
        int index = taskList.getSize() - 1;
        Task deletedTask = taskList.getAtIndex(index);
        taskList.deleteTask(index);
        Task lastTask = taskList.getAtIndex(taskList.getSize() - 1);

        assertNotEquals(lastTask.getContent(), addedTask.getContent());
        assertEquals(addedTask.getContent(), deletedTask.getContent());
        assertEquals(addedTask.writeSave(), deletedTask.writeSave());
    }

}
