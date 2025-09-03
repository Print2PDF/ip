package ScriptKiddie;

// Level 3

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/*
 * Store user input as unmarked subtype of Task, return number of tasks
 * Mark stored task when input is "mark"
 * Unmark stored task when input is "unmark"
 * Return list of stored items when input is "list"
 * Exit when user input is "bye"
 */
public class ScriptKiddie {

    private final static String line = "________________________________";
    private final static String botName = "ScriptKiddie";

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        String currUserInput = "";
        List<Task> taskList = new ArrayList<>();

        System.out.printf("%s\n", line);
        System.out.printf("Hello! I'm %s\n", botName);
        System.out.print("What can I do for you?\n");
        System.out.printf("%s\n", line);

        while (true) {

            currUserInput = userInput.nextLine();

            if (currUserInput.equals("bye")) {
                break;
            } else if (currUserInput.equals("list")) {
                System.out.printf("%s\n", line);
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < taskList.size(); i++) {
                    Task currTask = taskList.get(i);
                    System.out.printf("%d. %s\n", i + 1, currTask.toString());
                }

                System.out.printf("%s\n", line);
            } else if (currUserInput.substring(0, Math.min(4, currUserInput.length())).equals("mark")) {

                System.out.printf("%s\n", line);

                String[] tempArray = currUserInput.split(" ");
                String numStr = tempArray[tempArray.length - 1];
                try {
                    int taskNum = Integer.parseInt(numStr) - 1;
                    taskList.get(taskNum).mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.printf(" %s\n", taskList.get(taskNum).toString());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number!");
                }

                System.out.printf("%s\n", line);

            } else if (currUserInput.substring(0, Math.min(6, currUserInput.length())).equals("unmark")) {

                System.out.printf("%s\n", line);

                String[] tempArray = currUserInput.split(" ");
                String numStr = tempArray[tempArray.length - 1];
                try {
                    int taskNum = Integer.parseInt(numStr) - 1;
                    taskList.get(taskNum).mark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.printf(" %s\n", taskList.get(taskNum).toString());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number!");
                }

                System.out.printf("%s\n", line);

            } else {
                Task newTask = parseTask(currUserInput);

                System.out.printf("%s\n", line);

                if (newTask == null) {
                    System.out.println("Invalid input!");
                } else {
                    taskList.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.printf(" %s\n", newTask.toString());
                    System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                }

                System.out.printf("%s\n", line);
            }

        }

        System.out.printf("%s\n", line);
        System.out.print("Bye. Hope to see you again soon!\n");
        System.out.printf("%s\n", line);

    }

    public static Task parseTask(String input) {

        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String restOfInput = parts.length > 1 ? parts[1] : "";

        if ("todo".equals(command)) {
            return new ToDo(restOfInput);
        } else if ("deadline".equals(command)) {
            String[] deadlineParts = restOfInput.split(" /by ", 2);
            return new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
        } else if ("event".equals(command)) {
            String[] eventParts = restOfInput.split( " /from ", 2);
            String content = eventParts[0].trim();
            String[] timeParts = eventParts[1].split(" /to ", 2);
            return new Event(content, timeParts[0].trim(), timeParts[1].trim());
        } else {
            return null;
        }

    }

}
