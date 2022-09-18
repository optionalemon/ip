package pikachu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button closeButton;
    @FXML
    private Button sendButton;

    private Pikachu pikachu;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/squirtle/happy.jpeg"));
    private Image pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/a_bit_happy.jpg"));

    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/images/background.png"));
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        Background background = new Background(new BackgroundImage(backgroundImage,
                BackgroundRepeat.SPACE,
                BackgroundRepeat.ROUND,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(getWidth(), getHeight(), true, true, true, false)));
        dialogContainer.setBackground(background);
    }

    public void setPikachu(Pikachu d) {
        pikachu = d;
        dialogContainer.getChildren().add(DialogBox.getPikachuDialog(pikachu.sayHi(), pikachuImage));
    }

    public void setPikachuImage(PikachuEmotion emotion) {
        switch (emotion) {
        case CRY:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/cry.jpg"));
            break;
        case CONFUSED:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/confused.jpeg"));
            break;
        case OK:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/ok.jpg"));
            break;
        case SAD:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/sad.jpeg"));
            break;
        case YEA:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/yea.jpg"));
            break;
        case PLAY:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/play.jpg"));
            break;
        case HAPPY:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/happy.jpeg"));
            break;
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (pikachu.getIsExit()) {
            closeButtonAction();
        }
        String input = userInput.getText();
        String response = pikachu.getResponse(input);
        PikachuEmotion emotion = pikachu.getEmotion();
        this.setPikachuImage(emotion);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPikachuDialog(response, pikachuImage)
        );
        userInput.clear();
    }

    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
