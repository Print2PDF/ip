package ScriptKiddie;

// Level 3

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/*
 * Confirm and store user input as unmarked
 * Mark stored item when input is "mark"
 * Return list of stored items when input is "list"
 * Exit when user input is "bye"
 */
public class ScriptKiddie {

    private final static String botName = "ScriptKiddie";

    public static void main(String[] args) {
        String line = "________________________________";
        //String botName = "ScriptKiddie";
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
                    taskList.get(taskNum).unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.printf(" %s\n", taskList.get(taskNum).toString());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number!");
                }

                System.out.printf("%s\n", line);
            } else {
                Task newTask = new Task(currUserInput);
                taskList.add(newTask);
                System.out.printf("%s\n", line);
                System.out.printf("added: %s\n", newTask.toString());
                System.out.printf("%s\n", line);
            }
        }

        System.out.printf("%s\n", line);
        System.out.print("Bye. Hope to see you again soon!\n");
        System.out.printf("%s\n", line);
    }
}
