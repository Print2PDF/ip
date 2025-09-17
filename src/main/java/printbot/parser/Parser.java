package printbot.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import printbot.exceptions.PrintException;
import printbot.tasks.Deadline;
import printbot.tasks.Event;
import printbot.tasks.Task;
import printbot.tasks.TaskList;
import printbot.tasks.ToDo;
import printbot.ui.UI;

/**
 * Class to read user input and call corresponding commands and ui functions
 */
public class Parser {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    private static final Pattern PATTERN_TODO = Pattern.compile("^todo\\s+(.+)$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_DEADLINE = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_EVENT = Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$",
            Pattern.CASE_INSENSITIVE);
    //Level-9
    private static final Pattern PATTERN_FIND = Pattern.compile("^find\\s+(.+)$",
            Pattern.CASE_INSENSITIVE);

    private static final UI ui = new UI();

    public Parser() {}

    /**
     * Function to parse user inputs and execute valid commands
     * @param input as user input
     * @param taskList from PrintBot
     */
    public static void parseCommand(String input, TaskList taskList) {
        String[] parts = input.split(" ", 2); // {command, restOfInput}
        String command = parts[0].trim();
        String restOfInput = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case ("greet"):
            botGreet();
            break;
        case ("bye"):
            botBye();
            break;
        case ("list"):
            botList(restOfInput, taskList);
            break;
        case ("mark"):
            botMark(restOfInput, taskList);
            break;
        case ("unmark"):
            botUnmark(restOfInput, taskList);
            break;
        case ("delete"):
            botDelete(restOfInput, taskList);
            break;
        case ("todo"):
            botAddTodo(input, taskList);
            break;
        case ("deadline"):
            botAddDeadline(input, taskList);
            break;
        case ("event"):
            botAddEvent(input, taskList);
            break;
        case ("find"):
            botFind(input, taskList);
            break;
        default:
            unknownCommand();
            break;
        }
    }

    /*
     * these functions below clean user input, and call ui functions and execute inputs as commands
     */

    private static void botGreet() {
        ui.uiGreetUser();
    }

    private static void botBye() {
        ui.uiByeUser();
    }

    /*
     * Function to print TaskList object
     * @param user input without command identifier, TaskList object from PrintBot
     */
    private static void botList(String restOfInput, TaskList taskList) {
        if (restOfInput != null && !restOfInput.trim().isEmpty()) {
            ui.uiErrorMsg("'list' command should not have any trailing inputs!");
        } else {
            ui.uiPrintTasks(taskList);
        }
    }

    /*
     * Function to mark task and print marked task
     * @param user input without command identifier, TaskList object from PrintBot
     */
    private static void botMark(String restOfInput, TaskList taskList) {
        Integer taskNum;
        try {
            taskNum = Integer.parseInt(restOfInput.trim()) - 1; // 1-based to 0-based
        } catch (Exception e) {
            ui.uiErrorMsg("Oh no! Invalid task number!");
            return;
        }
        try {
            Task task = taskList.getAtIndex(taskNum); // will remove later
            taskList.markTask(taskNum);
            ui.uiMarkTask(task);
        } catch (Exception e) {
            ui.uiErrorMsg("Oh no! Task object does not exist at this index!");
        }
    }

    /*
     * Function to unmark task and print unmarked task
     * @param user input without command identifier, TaskList object from PrintBot
     */
    private static void botUnmark(String restOfInput, TaskList taskList) {
        Integer taskNum;
        try {
            taskNum = Integer.parseInt(restOfInput.trim()) - 1; // 1-based to 0-based
        } catch (Exception e) {
            ui.uiErrorMsg("Oh no! Invalid task number!");
            return;
        }
        try {
            Task task = taskList.getAtIndex(taskNum); // will remove later
            taskList.unmarkTask(taskNum);
            ui.uiUnmarkTask(task);
        } catch (Exception e) {
            ui.uiErrorMsg("Oh no! Task object does not exist at this index!");
        }
    }

    /*
     * Function to delete task and print deleted task
     * @param user input without command identifier, TaskList object from PrintBot
     */
    private static void botDelete(String restOfInput, TaskList taskList) {
        Integer taskNum;
        try {
            taskNum = Integer.parseInt(restOfInput.trim()) - 1; // 1-based to 0-based
        } catch (Exception e) {
            ui.uiErrorMsg("Oh no! Invalid task number!");
            return;
        }
        try {
            Task task = taskList.getAtIndex(taskNum); // will remove later
            taskList.deleteTask(taskNum);
            ui.uiDeleteTask(task, taskList);
        } catch (Exception e) {
            ui.uiErrorMsg("Oh no! Task object does not exist at this index!");
        }
    }

    /*
     * Function to add ToDo object and print add task message
     * @param full user input, TaskList object from PrintBot
     */
    private static void botAddTodo(String input, TaskList taskList) {
        Matcher m = PATTERN_TODO.matcher(input);
        if (!m.matches()) {
            ui.uiErrorMsg("Oh no! Invalid format for todo task");
            return; // replace this with exceptions
        }
        String content = m.group(1).trim();
        if (content.isEmpty()) {
            ui.uiErrorMsg("ToDo requires description");
            return;
        }
        ToDo t = new ToDo(content);
        taskList.addTask(t);
        ui.uiAddTask(t, taskList);
    }

    /*
     * Function to add Deadline object and print add message
     * @param full user input, TaskList object from PrintBot
     */
    private static void botAddDeadline(String input, TaskList taskList) {
        Matcher m = PATTERN_DEADLINE.matcher(input);
        if (!m.matches()) {
            ui.uiErrorMsg("Oh no! Invalid format for deadline task");
            return;
        }
        String content = m.group(1).trim();
        String dueDate = m.group(2).trim();
        if (content.isEmpty() || dueDate.isEmpty()) {
            ui.uiErrorMsg("Deadline requires description and a /by date");
            return;
        }
        Deadline t = new Deadline(content, dueDate);
        taskList.addTask(t);
        ui.uiAddTask(t, taskList);
    }

    /*
     * Function to add Event object and print add task message
     * @param full user input, TaskList object from PrintBot
     */
    private static void botAddEvent(String input, TaskList taskList) {
        Matcher m = PATTERN_EVENT.matcher(input);
        if (!m.matches()) {
            ui.uiErrorMsg("Oh no! Invalid format for event task");
            return;
        }
        String content = m.group(1).trim();
        String startDate = m.group(2).trim();
        String endDate = m.group(3).trim();
        if (content.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            ui.uiErrorMsg("Event requires description, a /from date and a /to date");
            return;
        }
        Event t = new Event(content, startDate, endDate);
        taskList.addTask(t);
        ui.uiAddTask(t, taskList);
    }

    private static void botFind(String input, TaskList taskList) {
        Matcher m = PATTERN_FIND.matcher(input);
        if (!m.matches()) {
            ui.uiErrorMsg("Oh no! Invalid format for finding keyword");
            return;
        }
        String keyword = m.group(1).trim();
        String result = taskList.consolidateMatchList(keyword);
        ui.uiFindTask(result);
    }

    private static void unknownCommand() {
        ui.uiUnknownCmd();
    }

    // DATETIME

    /**
     * Function to translate string representation of datetime to DateTime object
     * @param input as datetime part of user input
     * @return localDateTime object as DateTime representation
     * @throws PrintException if string representation is not a valid input
     */
    public static LocalDateTime parseDateTime(String input) throws PrintException {
        try {
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new PrintException("Invalid date time format. Use DD/MM/YYYY hhmm.");
        }
    }

}








