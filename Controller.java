package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    public ImageView exitButtonImage;
    @FXML
    private ImageView playButtonImage = new ImageView();
    @FXML
    private Button playButton = new Button();

    private Image playPressed = new Image(getClass().getResourceAsStream("sprites\\buttons\\playbutton pressed.png"));
    private Image playReleased = new Image(getClass().getResourceAsStream("sprites\\buttons\\playbutton.png"));
    private Image exitPressed = new Image(getClass().getResourceAsStream("sprites\\buttons\\exitbutton pressed.png"));
    private Image exitRelease = new Image(getClass().getResourceAsStream("sprites\\buttons\\exitbutton.png"));


    @FXML
    private void exitAction() {
        exitButtonImage.setImage(exitPressed);
    }


    @FXML
    private void playAction() throws InterruptedException {
        playButtonImage.setImage(playPressed);
    }

    @FXML
    private void playRelease() {
        playButtonImage.setImage(playReleased);
    }

    public void exitGame() {
        System.exit(0);
    }

    public void exitReset() {
        exitButtonImage.setImage(exitRelease);
    }
}
