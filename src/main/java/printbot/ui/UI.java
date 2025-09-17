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
     * Function to print greeting/introduction message
     */
    public void uiGreetUser() {
        printMessage(String.format("Hello! I'm %s.\nWhat can I do for you?", BOT_NAME));
    }

    /*
     * Function to print exit message
     */
    public void uiByeUser() {
        printMessage("Bye. Hope to see you again soon!\n");
    }

    /**
     * Function to print all recorded tasks
     * @param taskList from Parser from PrintBot
     */
    public void uiPrintTasks(TaskList taskList) {
        String output = "Here are the tasks in your list:\n";
        printMessage(output + taskList.consolidateTaskList());
    }

    /**
     * Function to print added task and acknowledge user command
     * @param task added by command called by Parser
     * @param taskList from Parser from PrintBot
     */
    public void uiAddTask(Task task, TaskList taskList) {
        String header = "Got it. I've added this task:";
        String taskView = " " + task.toString();
        String tail = String.format("Now you have %d tasks in the list.", taskList.getSize());
        printMessage(header + "\n" + taskView + "\n" + tail);
    }

    /**
     * Function to print deleted task and acknowledge user command
     * @param task deleted by command called by Parser
     * @param taskList from Parser from PrintBot
     */
    public void uiDeleteTask(Task task, TaskList taskList) {
        String header = "Noted. I've removed this task:";
        String taskView = " " + task.toString();
        String tail = String.format("Now you have %d tasks in the list.", taskList.getSize());
        printMessage(header + "\n" + taskView + "\n" + tail);
    }

    /**
     * Function to print marked task and acknowledge user command
     * @param task marked by command called by Parser
     */
    public void uiMarkTask(Task task) {
        String header = "Nice, I've marked this task as done:";
        String taskView = " " + task.toString();
        printMessage(header + "\n" + taskView);
    }

    /**
     * Function to print unmarked task and acknowledge user command
     * @param task unmarked by command called by Parser
     */
    public void uiUnmarkTask(Task task) {
        String header = "OK, I've marked this task as not done:";
        String taskView = " " + task.toString();
        printMessage(header + "\n" + taskView);
    }

    /**
     * Function to print list of tasks containing search word in the description
     * @param matchListString from consolidatedMatchList called by Parser
     */
    public void uiFindTask(String matchListString) {
        String header = "Here are the matching tasks in your list:";
        String matchView = matchListString;
        printMessage(header + "\n" + matchView);
    }

    // ERRORS, INVALID COMMANDS
    /*
     * Function to wrap error message
     * @param error message
     */
    public void uiErrorMsg(String msg) {
        printMessage(msg);
    }

    /*
     * Function to alert user of invalid command
     */
    public void uiUnknownCmd() {
        printMessage("Sorry! I do not recognise this command!");
    }

}
