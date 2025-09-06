package PrintBot;

// Level 7

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * Purpose
 * Allows user to record, view, manage and delete tasks
 *
 * Commands
 * todo -- stores task name in new ToDo instance, unmarked
 * deadline -- stores task name and deadline in new Deadline instance, unmarked
 * event -- stores task name, start time and end time in new Event instance, unmarked
 *      all new task objects will be added to task list
 *
 * list -- display list of currently stored tasks and their progress status
 * mark a -- mark task at index a status as completed
 * unmark a -- mark task at index a status as not completed
 * delete a -- remove task at index a from task list
 *
 * Automatic Features
 * save -- saves task list when exiting PrintBot, loads task list when restarting PrintBot
 *
 */
public class PrintBot {

    private final static String line = "________________________________";
    private final static String botName = "PrintBot";
    private static TaskList taskList;

    public static void main(String[] args) {

        // read save here
        taskList = saveReader();
        //taskList = new TaskList();

        Scanner userInput = new Scanner(System.in);
        String currInput = "";

        System.out.printf("%s\n", line);
        System.out.printf("Hello! I'm %s\n", botName);
        System.out.println("What can I do for you?");
        System.out.printf("%s\n", line);

        while (true) {

            currInput = userInput.nextLine();

            System.out.printf("%s\n", line);

            if (currInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.printf("%s\n", line);
                break;
            }

            try {
                commandParser(currInput);
            } catch (CommandException e) {
                System.out.println(e.getMessage());
            }

            System.out.printf("%s\n", line);

        }

        saveWriter();
        taskList = new TaskList();

    }

    public static void commandParser(String input) throws CommandException {

        String[] prelim = input.split(" ", 2); // identify initial command
        String command = prelim[0];
        String restOfInput = prelim.length > 1 ? prelim[1] : "";

        if (command.equals("bye") & restOfInput.isEmpty()) { // BYE
            System.out.println("Bye. Hope to see you again soon!");
            //return new NotATask("bye");
        } else if (command.equals("list") & restOfInput.isEmpty()) { // LIST
            taskList.displayTaskList();
            //return new NotATask("list");
        } else if (command.equals("mark")) { // MARK
            try {
                int taskNum = Integer.parseInt(restOfInput) - 1;
                taskList.markTask(taskNum);
                //return new NotATask("mark");
            } catch (NumberFormatException e) {
                throw new IndexException();
            }
        } else if (command.equals("unmark")) { // UNMARK
            try {
                int taskNum = Integer.parseInt(restOfInput) - 1;
                taskList.unmarkTask(taskNum);
                //return new NotATask("unmark");
            } catch (NumberFormatException e) {
                throw new IndexException();
            }
        } else if (command.equals("delete")) { // DELETE
            try {
                int taskNum = Integer.parseInt(restOfInput) - 1;
                taskList.deleteTask(taskNum);
                //return new NotATask("delete");
            } catch (NumberFormatException e) {
                throw new IndexException();
            }
        } else if (command.equals("todo")) {
            ToDo newTask = new ToDo(restOfInput);
            taskList.addTask(newTask);
        } else if (command.equals("deadline")) {
            String[] deadlineParts = restOfInput.split(" /by ", 2);
            if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                throw new CommandException("Oh no! Deadline must include a '/by' date");
            }
            Deadline newTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
            taskList.addTask(newTask);
        } else if (command.equals("event")) {
            String[] eventParts = restOfInput.split( " /from ", 2);
            if (eventParts.length < 2 || eventParts[1].trim().isEmpty()) {
                throw new CommandException("Oh no! Event must include a '/from' date");
            }
            String content = eventParts[0].trim();
            String[] timeParts = eventParts[1].split(" /to ", 2);
            if (timeParts.length < 2 || timeParts[1].trim().isEmpty()) {
                throw new CommandException("Oh no! Event must include a '/to' date");
            }
            Event newTask = new Event(content, timeParts[0].trim(), timeParts[1].trim());
            taskList.addTask(newTask);
        } else {
            throw new TaskException();
        }

    }

    /*
     * @parameters
     * none
     *
     * @description
     * overwrite current save file "save.txt" with single string of tasks
     */
    public static void saveWriter() {

        String fileName = "/Users/engpi/IdeaProjects/PrintBot/save.txt";
        String saveContent = taskList.saveFormat();

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(saveContent);
            System.out.println("Task list saved successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }

    /*
     * @parameters
     * none
     *
     * @description
     * read current save file "save.txt" and return TaskList object
     */
    public static TaskList saveReader() {
        System.out.println("Reading save...");

        try {
            File save = new File("/Users/engpi/IdeaProjects/PrintBot/save.txt");
            try (Scanner saveReader = new Scanner(save)) {
                // Check if there's a line to read
                if (saveReader.hasNextLine()) {
                    String data = saveReader.nextLine();
                    System.out.println("Successfully read from file.");
                    return new TaskList(data);
                } else {
                    System.out.println("File is empty.");
                    return new TaskList();
                }
            } catch (CorruptedSaveException e) {
                System.out.println(e.getMessage());
                return new TaskList();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. File not found.");
            e.printStackTrace();
        }
        return new TaskList();
    }

}