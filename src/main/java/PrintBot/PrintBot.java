package PrintBot;

// A-MOREOOP

//import java.io.*;
import java.util.*;

public class PrintBot {

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
