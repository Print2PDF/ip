package printbot;

import javafx.application.Application;

/**
 * Class to avoid classpath issues on some platforms.
 */
public class Launcher {

    public static void main(String[] args) {
        Application.launch(printbot.gui.Main.class, args);
    }

}
