// Level 1

import java.util.Scanner;

/*
 * Echo user input
 * Exit when user input == "bye"
 */
public class ScriptKiddie {
    public static void main(String[] args) {
        String line = "________________________________";
        String botName = "ScriptKiddie";
        Scanner userInput = new Scanner(System.in);
        String currUserInput = "";

        System.out.printf("%s\n", line);
        System.out.printf("Hello! I'm %s\n", botName);
        System.out.print("What can I do for you?\n");
        System.out.printf("%s\n", line);

        do {
            currUserInput = userInput.nextLine();
            System.out.printf("%s\n", line);
            if (!currUserInput.equals("bye")) {
                System.out.printf("%s\n", currUserInput);
                System.out.printf("%s\n", line);
            }
        } while (!currUserInput.equals("bye"));

        System.out.print("Bye. Hope to see you again soon!\n");
        System.out.printf("%s\n", line);
    }
}
