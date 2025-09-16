package printbot.ui;

// A-MOREOOP

import printbot.tasks.Task;
import printbot.tasks.TaskList;

public class UI {

    private static final String LINE = "___________________________________";
    private static final String BOT_NAME = "printbot";

    public UI() {}

    private static void printMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void uiGreetUser() {
        printMessage(String.format("Hello! I'm %s.\nWhat can I do for you?", BOT_NAME));
    }

    public void uiByeUser() {
        printMessage("Bye. Hope to see you again soon!\n");
    }

    public void uiPrintTasks(TaskList taskList) {
        String output = "Here are the tasks in your list:\n";
        printMessage(output + taskList.consolidateTaskList());
    }

    public void uiAddTask(Task task, TaskList taskList) {
        String header = "Got it. I've added this task:";
        String taskView = " " + task.toString();
        String tail = String.format("Now you have %d tasks in the list.", taskList.getSize());
        printMessage(header + "\n" + taskView + "\n" + tail);
    }

    public void uiDeleteTask(Task task, TaskList taskList) {
        String header = "Noted. I've removed this task:";
        String taskView = " " + task.toString();
        String tail = String.format("Now you have %d tasks in the list.", taskList.getSize());
        printMessage(header + "\n" + taskView + "\n" + tail);
    }

    public void uiMarkTask(Task task) {
        String header = "Nice, I've marked this task as done:";
        String taskView = " " + task.toString();
        printMessage(header + "\n" + taskView);
    }

    public void uiUnmarkTask(Task task) {
        String header = "OK, I've marked this task as not done:";
        String taskView = " " + task.toString();
        printMessage(header + "\n" + taskView);
    }

    public void uiFindTask(String matchListString) {
        String header = "Here are the matching tasks in your list:";
        String matchView = matchListString;
        printMessage(header + "\n" + matchView);
    }

    // ERRORS, INVALID COMMANDS
    public void uiErrorMsg(String msg) {
        printMessage(msg);
    }

    public void uiUnknownCmd() {
        printMessage("Sorry! I do not recognise this command!");
    }

}
