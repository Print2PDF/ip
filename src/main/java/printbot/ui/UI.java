package printbot.ui;

import printbot.tasks.Task;
import printbot.tasks.TaskList;

/**
 * Class that contains functions to display messages in the console
 */
public class UI {

    private static final String LINE = "___________________________________";
    private static final String BOT_NAME = "printbot";

    public UI() {}

    /*
     * Function to wrap and standardise appearance of all messages
     * @param message
     * @return standardise message in console
     */
    private static void printMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /*
     * Function to return greeting/introduction message
     */
    public String uiGreetUser() {
        return String.format("Hello! I'm %s.\nWhat can I do for you?", BOT_NAME);
    }

    /*
     * Function to return exit message
     */
    public String uiByeUser() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Function to print all recorded tasks
     * @param taskList from Parser from PrintBot
     */
    public String uiPrintTasks(TaskList taskList) {
        String output = "Here are the tasks in your list:\n";
        return output + taskList.consolidateTaskList();
    }

    /**
     * Function to print added task and acknowledge user command
     * @param task added by command called by Parser
     * @param taskList from Parser from PrintBot
     */
    public String uiAddTask(Task task, TaskList taskList) {
        String header = "Got it. I've added this task:";
        String taskView = " " + task.toString();
        String tail = String.format("Now you have %d tasks in the list.", taskList.getSize());
        return header + "\n" + taskView + "\n" + tail;
    }

    /**
     * Function to print deleted task and acknowledge user command
     * @param task deleted by command called by Parser
     * @param taskList from Parser from PrintBot
     */
    public String uiDeleteTask(Task task, TaskList taskList) {
        String header = "Noted. I've removed this task:";
        String taskView = " " + task.toString();
        String tail = String.format("Now you have %d tasks in the list.", taskList.getSize());
        return header + "\n" + taskView + "\n" + tail;
    }

    /**
     * Function to print marked task and acknowledge user command
     * @param task marked by command called by Parser
     */
    public String uiMarkTask(Task task) {
        String header = "Nice, I've marked this task as done:";
        String taskView = " " + task.toString();
        return header + "\n" + taskView;
    }

    /**
     * Function to print unmarked task and acknowledge user command
     * @param task unmarked by command called by Parser
     */
    public String uiUnmarkTask(Task task) {
        String header = "OK, I've marked this task as not done:";
        String taskView = " " + task.toString();
        return header + "\n" + taskView;
    }

    /**
     * Function to print list of tasks containing search word in the description
     * @param matchListString from consolidatedMatchList called by Parser
     */
    public String uiFindTask(String matchListString) {
        String header = "Here are the matching tasks in your list:";
        String matchView = matchListString;
        return header + "\n" + matchView;
    }

    // ERRORS, INVALID COMMANDS
    /*
     * Function to wrap error message
     * @param error message
     */
    public String uiErrorMsg(String msg) {
        return msg;
    }

    /*
     * Function to alert user of invalid command
     */
    public String uiUnknownCmd() {
        return "Sorry! I do not recognise this command!";
    }

}
