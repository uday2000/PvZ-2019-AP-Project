//package sample;

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

    private Image playPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\PlayButtonPressed.png"));
    private Image playReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\PlayButtonReleased.png"));
    private Image exitPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\ExitButtonPressed.png"));
    private Image exitRelease = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\ExitButtonReleased.png"));

    @FXML
    private void playPress() throws InterruptedException {
        playButtonImage.setImage(playPressed);
    }

    @FXML
    private void exitPress() throws InterruptedException{
        exitButtonImage.setImage(exitPressed);
    }

    @FXML
    private void playRelease() {
        playButtonImage.setImage(playReleased);
    }

    @FXML
    private void exitRelease() {
        exitButtonImage.setImage(exitRelease);
    }

    public void exitGame() {
        System.exit(0);
    }

    public void gotoScene() {
        System.out.print("something");
    }
}
