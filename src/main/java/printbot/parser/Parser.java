package printbot.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import printbot.commands.AddCommand;
import printbot.commands.Command;
import printbot.commands.DeleteCommand;
import printbot.commands.ExitCommand;
import printbot.commands.FindCommand;
import printbot.commands.GreetCommand;
import printbot.commands.HelpCommand;
import printbot.commands.ListCommand;
import printbot.commands.MarkCommand;
import printbot.commands.UnknownCommand;
import printbot.commands.UnmarkCommand;
import printbot.exceptions.CommandException;
import printbot.exceptions.DateTimeConflictException;
import printbot.exceptions.DateTimeInvalidException;
import printbot.exceptions.FormatDeadlineException;
import printbot.exceptions.FormatEventException;
import printbot.exceptions.IndexException;
import printbot.exceptions.TaskNameEmptyException;
import printbot.tasks.Deadline;
import printbot.tasks.Event;
import printbot.tasks.TaskList;
import printbot.tasks.ToDo;

/**
 * Class to read user input and call corresponding commands and ui functions
 */
public class Parser {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private static final Pattern PATTERN_TODO = Pattern.compile("^todo\\s+(.+)$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_DEADLINE = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_EVENT = Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_FIND = Pattern.compile("^find\\s+(.+)$",
            Pattern.CASE_INSENSITIVE);

    /**
     * Function to parse user inputs and execute valid commands
     * @param input as user input
     * @param taskList as current task list
     */
    public static Command parseInput(String input, TaskList taskList) throws Exception {
        input = input.trim();
        String[] parts = input.split(" ", 2); // {command, restOfInput}
        String cmd = parts[0].trim();
        String restOfInput = parts.length > 1 ? parts[1] : "";

        switch (cmd) {
        case ("greet"):
            return new GreetCommand();
        case ("bye"):
            return new ExitCommand();
        case ("list"):
            return botList(restOfInput);
        case ("find"):
            return botFind(input);
        case ("mark"):
            return botMark(restOfInput, taskList);
        case ("unmark"):
            return botUnmark(restOfInput, taskList);
        case ("delete"):
            return botDelete(restOfInput, taskList);
        case ("todo"):
            return botAddTodo(input, taskList);
        case ("deadline"):
            return botAddDeadline(input, taskList);
        case ("event"):
            return botAddEvent(input, taskList);
        case ("help"):
            return botHelp(restOfInput);
        default:
            return new UnknownCommand();
        }
    }

    private static Command botList(String restOfInput) throws CommandException {
        if (!restOfInput.isEmpty()) {
            throw new CommandException();
        }
        return new ListCommand();
    }

    private static Command botFind(String input) throws CommandException {
        Matcher m = PATTERN_FIND.matcher(input);
        if (!m.matches()) {
            throw new CommandException();
        }
        String keyword = m.group(1).trim();
        return new FindCommand(keyword);
    }

    /*
     * AI-Assisted:
     * This private helper method encapsulates the repetitive try-catch logic
     * that was duplicated in botMark, botUnmark, and botDelete.
     */
    private static int parseTaskNumber(String input) throws CommandException, IndexException {
        try {
            return Integer.parseInt(input.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new CommandException();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexException();
        }
    }

    private static Command botMark(String restOfInput, TaskList taskList) throws CommandException, IndexException {
        int taskNum = parseTaskNumber(restOfInput);
        return new MarkCommand(taskNum);
    }

    private static Command botUnmark(String restOfInput, TaskList taskList) throws CommandException, IndexException {
        int taskNum = parseTaskNumber(restOfInput);
        return new UnmarkCommand(taskNum);
    }

    private static Command botDelete(String restOfInput, TaskList taskList) throws CommandException, IndexException {
        int taskNum = parseTaskNumber(restOfInput);
        return new DeleteCommand(taskNum);
    }

    private static Command botAddTodo(String input, TaskList taskList) throws CommandException, TaskNameEmptyException {
        Matcher m = PATTERN_TODO.matcher(input);
        if (!m.matches()) {
            throw new CommandException();
        }
        String content = m.group(1).trim();
        if (content.isEmpty()) {
            throw new TaskNameEmptyException();
        }
        ToDo task = new ToDo(content);
        return new AddCommand(task);
    }

    private static Command botAddDeadline(String input, TaskList taskList) throws FormatDeadlineException,
            TaskNameEmptyException, DateTimeInvalidException {
        Matcher m = PATTERN_DEADLINE.matcher(input);
        if (!m.matches()) {
            throw new FormatDeadlineException();
        }
        String content = m.group(1).trim();
        String dueDate = m.group(2).trim();
        if (content.isEmpty()) {
            throw new TaskNameEmptyException();
        }
        if (dueDate.isEmpty()) {
            throw new FormatDeadlineException();
        }
        Deadline task = new Deadline(content, dueDate);
        return new AddCommand(task);
    }

    private static Command botAddEvent(String input, TaskList taskList) throws FormatEventException,
            TaskNameEmptyException, DateTimeInvalidException, DateTimeConflictException {
        Matcher m = PATTERN_EVENT.matcher(input);
        if (!m.matches()) {
            throw new FormatEventException();
        }
        String content = m.group(1).trim();
        String startDate = m.group(2).trim();
        String endDate = m.group(3).trim();
        if (content.isEmpty()) {
            throw new TaskNameEmptyException();
        }
        if (startDate.isEmpty() || endDate.isEmpty()) {
            throw new FormatEventException();
        }
        Event task = new Event(content, startDate, endDate);
        return new AddCommand(task);
    }

    private static Command botHelp(String restOfInputs) throws CommandException {
        if (!restOfInputs.isEmpty()) {
            throw new CommandException();
        }
        return new HelpCommand();
    }

    // DATETIME

    /**
     * Function to translate string representation of datetime to DateTime object
     * @param input as datetime part of user input
     * @return localDateTime object as DateTime representation
     * @throws DateTimeInvalidException if string representation is not a valid input
     */
    public static LocalDateTime parseDateTime(String input) throws DateTimeInvalidException {
        try {
            return LocalDateTime.parse(input, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeInvalidException();
        }
    }

    /**
     * Function to translate datetime to string
     * @param input LocalDateTime input
     * @return String datetime as string
     */
    public static String dateToString(LocalDateTime input) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
        String time = input.format(timeFormatter);
        String month = input.format(monthFormatter);
        return input.getDayOfMonth() + " " + month + " " + input.getYear() + " " + time;
    }
}
