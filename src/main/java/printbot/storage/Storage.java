package printbot.storage;


import printbot.exceptions.CorruptedSaveException;
import printbot.tasks.Task;
import printbot.tasks.TaskList;

import java.io.*;
import java.util.List;

public class Storage {

    private static final String SAVE_FILE_PATH = "./taskSave.txt";

    public Storage() {}

    // SAVE , LEVEL-7
    public static TaskList readSaveFile() {
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

    public static void writeSaveFile(TaskList taskList) {
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
