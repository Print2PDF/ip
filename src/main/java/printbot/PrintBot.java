package printbot;

import java.util.Scanner;

import printbot.commands.Command;
import printbot.commands.GreetCommand;
import printbot.parser.Parser;
import printbot.storage.Storage;
import printbot.tasks.TaskList;
import printbot.ui.UI;

/**
 * Class to initialise TaskList, Parser and start chatbot application
 */
public class PrintBot {
    private Scanner scanner;
    private TaskList taskList;
    private UI ui;
    private Storage storage;

    /**
     * Constructor for PrintBot using existing save file
     */
    public PrintBot() {
        this.scanner = new Scanner(System.in);
        this.taskList = new TaskList();
        this.ui = new UI();
        this.storage = new Storage();
    }

    public void loadData() {
        this.taskList = storage.readSaveFile();
    }

    /**
     * Function to greet user
     * @return String "Hello, I'm PrintBot ..."
     */
    public String sayHello() {
        Command greet = new GreetCommand();
        return greet.execute(this.taskList, this.ui, this.storage);
    }

    /**
     * Function to get PrintBot's response to user input
     * @param input as user input
     * @return String response from Parser
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseInput(input, this.taskList);
            return command.execute(this.taskList, this.ui, this.storage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
