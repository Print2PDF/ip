package printbot.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import printbot.PrintBot;

/**
 * Class to handle GUI functions
 */
public class MainWindow {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private PrintBot printBot;

    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/DaBot.png"));
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    /**
     * Function to initialise main window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Function to set PrintBot instance
     */
    public void setPrintBot(PrintBot bot) {
        printBot = bot;
        printBot.loadData();
        dialogContainer.getChildren().addAll(DialogBox.getPrintBotDialog(printBot.sayHello(), botImage));
    }

    /**
     * Function to create dialog boxes, 1 for echoing user input and 1 for displaying PrintBot's reply
     * Add to dialog container
     * Clear user input after processing
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = printBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPrintBotDialog(response, botImage));

        userInput.clear();
    }
}
