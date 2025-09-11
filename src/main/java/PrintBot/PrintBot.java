package PrintBot;

import java.io.*;
import java.util.*;

// Fixing Issue - temporarily not using TaskList
public class PrintBot {

    private static final String SAVE_FILE_PATH = "./taskSave.txt";
    private static final String BOT_NAME = "PrintBot";

    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);
        List<Task> taskList = readSaveFile();

        printMessage(String.format("Hello! I'm %s.\nWhat can I do for you?", BOT_NAME));

        while (true) {
            String curr = inputReader.nextLine(); // Current Input

            if (curr.equalsIgnoreCase("bye")) {
                printMessage("Bye. Hope to see you again soon!\n");
                break;
            } else if (curr.equalsIgnoreCase("list")) {
                StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    sb.append("\n").append(i + 1).append(". ").append(taskList.get(i));
                }
                printMessage(sb.toString());
            } else if (curr.toLowerCase().startsWith("mark ")) {
                Integer taskNum;
                try {
                    taskNum = Integer.parseInt(curr.substring(5).trim());
                } catch (Exception e) {
                    printMessage("Oh no! Invalid task number.");
                    continue;
                }

                Task task;
                try {
                    task = taskList.get(taskNum - 1);
                } catch (Exception e) {
                    printMessage("Oh no! Invalid task object");
                    continue;
                }
                task.mark();
                printMessage("Nice, I've marked this task as done:\n " + task);
            } else if (curr.toLowerCase().startsWith("unmark ")) {
                Integer taskNum;
                try {
                    taskNum = Integer.parseInt(curr.substring(7).trim());
                } catch (Exception e) {
                    printMessage("Oh no! Invalid task number.");
                    continue;
                }

                Task task;
                try {
                    task = taskList.get(taskNum - 1);
                } catch (Exception e) {
                    printMessage("Oh no! Invalid task object");
                    continue;
                }
                task.unmark();
                printMessage("OK, I've marked this task as not done:\n " + task);
            } else if (curr.toLowerCase().startsWith("delete ")) {
                Integer taskNum;
                try {
                    taskNum = Integer.parseInt(curr.substring(7).trim());
                } catch (Exception e) {
                    printMessage("Oh no! Invalid task number.");
                    continue;
                }
                Task task;
                try {
                    task = taskList.remove(taskNum - 1);
                    printMessage("Noted. I've removed this task:\n " + task + "\n" + "Now you have " + taskList.size() + " tasks in the list.");
                } catch (Exception e) {
                    printMessage("Oh no! Invalid task number.");
                    continue;
                }
            } else if (curr.toLowerCase().startsWith("todo ")) {
                String restOfInput = curr.substring(5);
                if (checkContent(restOfInput)) {
                    botAddTask(taskList, new ToDo(curr));
                } else {
                    continue;
                }
            } else if (curr.toLowerCase().startsWith("deadline ")) {
                String restOfInput = curr.substring(9);
                if (checkContent(restOfInput)) {
                    botAddTask(taskList, new Deadline(curr));
                } else {
                    continue;
                }
            } else if (curr.toLowerCase().startsWith("event ")) {
                String restOfInput = curr.substring(6);
                if (checkContent(restOfInput)) {
                    botAddTask(taskList, new Event(curr));
                } else {
                    continue;
                }
            } else {
                printMessage("INVALID COMMAND");
            }

            writeSaveFile(taskList);

        }

        // End

    }

    // SAVE , LEVEL-7
    private static List<Task> readSaveFile() {
        List<Task> taskList = new ArrayList<>();
        File file = new File(SAVE_FILE_PATH);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            printMessage("Loading save file...");
            String currLine; // line-by-line in save file
            while ((currLine = reader.readLine()) != null) {
                try {
                    Task newTask = Task.readSave(currLine);
                    taskList.add(newTask);
                } catch (CorruptedSaveException e) {
                    printMessage(e.getMessage());
                }
            }
        } catch (IOException e) {
            printMessage("Oh no! Unable to find save file!");
        }
        return taskList;
    }

    private static void writeSaveFile(List<Task> taskList) {
        File file = new File(SAVE_FILE_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task t : taskList) {
                writer.write(t.writeSave());
                writer.newLine();
            }
        } catch (IOException e) {
            printMessage("Oh no! Unable to find save file!");
        }
    }

    // DISPLAY
    private static void printMessage(String message) {
        System.out.println("___________________________________");
        System.out.println(message);
        System.out.println("___________________________________");
    }

    // COMMANDS
    private static void botAddTask(List<Task> taskList, Task task) {
        taskList.add(task);
        String msg1 = String.format(" %s", task.toString());
        String msg2 = String.format("Now you have %d tasks in the list.", taskList.size());

        printMessage("Got it. I've added this task:\n" + msg1 + "\n" + msg2);
    }

    // VALIDATE COMMANDS
    private static boolean checkContent(String content) {
        if (content.isEmpty()) {
            printMessage("Oh no! Task description cannot be empty.");
            return false;
        } else {
            return true;
        }
    }

}
