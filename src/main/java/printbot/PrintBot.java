package printbot;

// A-MOREOOP

//import java.io.*;
import printbot.parser.Parser;
import printbot.storage.Storage;
import printbot.tasks.TaskList;

import java.util.*;

/*
 * PrintBot main class
 * @author Eng Pin Wen <eng.pin.wen@gmail.com>
 * @version 0.1
 */
public class PrintBot {

    /*
     * Main function to start PrintBot application
     * @param args
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
