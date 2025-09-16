package printbot.ui;

// A-MOREOOP

import printbot.tasks.Task;
import printbot.tasks.TaskList;

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

    /*
     * Function to print all recorded tasks
     * @param TaskList object from Parser from PrintBot
     */
    public void uiPrintTasks(TaskList taskList) {
        String output = "Here are the tasks in your list:\n";
        printMessage(output + taskList.consolidateTaskList());
    }

    /*
     * Function to print added task and acknowledge user command
     * @param added Task object, TaskList object from Parser from PrintBot
     */
    public void uiAddTask(Task task, TaskList taskList) {
        String header = "Got it. I've added this task:";
        String taskView = " " + task.toString();
        String tail = String.format("Now you have %d tasks in the list.", taskList.getSize());
        printMessage(header + "\n" + taskView + "\n" + tail);
    }

    /*
     * Function to print deleted task and acknowledge user command
     * @param deleted Task object, TaskList object from Parser from PrintBot
     */
    public void uiDeleteTask(Task task, TaskList taskList) {
        String header = "Noted. I've removed this task:";
        String taskView = " " + task.toString();
        String tail = String.format("Now you have %d tasks in the list.", taskList.getSize());
        printMessage(header + "\n" + taskView + "\n" + tail);
    }

    /*
     * Function to print marked task and acknowledge user command
     * @param marked Task object
     */
    public void uiMarkTask(Task task) {
        String header = "Nice, I've marked this task as done:";
        String taskView = " " + task.toString();
        printMessage(header + "\n" + taskView);
    }

    /*
     * Function to print unmarked task and acknowledge user command
     * @param unmarked Task object
     */
    public void uiUnmarkTask(Task task) {
        String header = "OK, I've marked this task as not done:";
        String taskView = " " + task.toString();
        printMessage(header + "\n" + taskView);
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
