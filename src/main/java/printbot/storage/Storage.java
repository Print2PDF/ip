package printbot.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import printbot.exceptions.CorruptedSaveException;
import printbot.tasks.Task;
import printbot.tasks.TaskList;

/**
 * Class to read and write save files
 */
public class Storage {

    private static final String SAVE_FILE_PATH = "./taskSave.txt";

    public Storage() {}

    /**
     * Function to find, read save and create recorded TaskList object
     * @return TaskList object with recorded Task objects
     */
    public TaskList readSaveFile() {
        TaskList taskList = new TaskList();
        File file = new File(SAVE_FILE_PATH);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("Loading save file...");
            String currLine; // line-by-line in save file
            while ((currLine = reader.readLine()) != null) {
                try {
                    Task newTask = Task.readSave(currLine);
                    taskList.addTask(newTask);
                } catch (CorruptedSaveException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Oh no! Unable to find save file!");
        }
        return taskList;
    }

    /**
     * Function to translate stored Task objects to save format as String, write to save file
     * @param taskList object from PrintBot
     */
    public void writeSaveFile(TaskList taskList) {
        File file = new File(SAVE_FILE_PATH);
        List<String> tLReadable = taskList.getSaveFormat();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String s : tLReadable) {
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Oh no! Unable to find save file!");
        }
    }
}
