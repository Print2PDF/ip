package printbot;

import java.util.Scanner;

import printbot.parser.Parser;
import printbot.storage.Storage;
import printbot.tasks.TaskList;

/**
 * Class to initialise TaskList, Parser and start chatbot application
 */
public class PrintBot {

    /**
     * Function to start PrintBot application
     * @param args not used
     */
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        TaskList taskList = Storage.readSaveFile();

        System.out.println("PrintBot is ready! Enter a command:");

        while (true) {
            System.out.print("> ");
            System.out.flush();

            if (inputReader.hasNextLine()) {
                String input = inputReader.nextLine().trim();

                if (input.isEmpty()) {
                    continue;
                }

                Parser.parseCommand(input, taskList);
                Storage.writeSaveFile(taskList);

                if (input.equals("bye")) { // potential arrowhead, get rid of it
                    break;
                }
            }
        }

        inputReader.close();
    }

}
