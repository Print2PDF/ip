// Level 2

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/*
 * Confirm user input
 * Return list of stored items when input == "list"
 * Exit when user input == "bye"
 */
public class ScriptKiddie {
    public static void main(String[] args) {
        String line = "________________________________";
        String botName = "ScriptKiddie";
        Scanner userInput = new Scanner(System.in);
        String currUserInput = "";
        List<String> storedList = new ArrayList<>();

        System.out.printf("%s\n", line);
        System.out.printf("Hello! I'm %s\n", botName);
        System.out.print("What can I do for you?\n");
        System.out.printf("%s\n", line);

        do {
            currUserInput = userInput.nextLine();
            System.out.printf("%s\n", line);
            if (currUserInput.equals("list")) {
                for (int i = 0; i < storedList.size(); i++) {
                    String item = storedList.get(i);
                    System.out.printf("%d. %s\n", i + 1, item);
                }
                System.out.printf("%s\n", line);
            } else if (!currUserInput.equals("bye")) {
                storedList.add(currUserInput);
                System.out.printf("added: %s\n", currUserInput);
                System.out.printf("%s\n", line);
            }
        } while (!currUserInput.equals("bye"));

        System.out.print("Bye. Hope to see you again soon!\n");
        System.out.printf("%s\n", line);
    }
}
