package printbot.storage;

import printbot.tasks.Deadline;
import printbot.tasks.Task;
import printbot.tasks.TaskList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    /*
     * Function to check if writing a task and reading from the same save yields the same task
     * i.e. same type, description and datetime
     */
    @Test
    @DisplayName("save and read deadline: valid should return same deadline task")
    void write_and_read_valid() throws Exception {
        Task saveTask = new Deadline("return book", "6/9/2025 1700");
        TaskList saveList = new TaskList();
        saveList.addTask(saveTask);
        Storage.writeSaveFile(saveList);

        TaskList readList = Storage.readSaveFile();
        Task readTask = readList.getAtIndex(readList.getSize() - 1);

        assertInstanceOf(Deadline.class, readTask);
        assertEquals(saveTask.getContent(), readTask.getContent()); // description
        assertEquals(saveTask.isItMarked(), readTask.isItMarked()); // mark status
        assertEquals(saveTask.writeSave(), readTask.writeSave()); // datetime
    }

}
