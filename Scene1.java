package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Scene1 {

    @FXML
    public ImageView exitButtonImage;
    @FXML
    private ImageView playButtonImage;

    private Scene nextScene;

    private Image playPressed = new Image(getClass().getResourceAsStream("sprites\\buttons\\playbutton pressed.png"));
    private Image playReleased = new Image(getClass().getResourceAsStream("sprites\\buttons\\playbutton.png"));
    private Image exitPressed = new Image(getClass().getResourceAsStream("sprites\\buttons\\exitbutton pressed.png"));
    private Image exitRelease = new Image(getClass().getResourceAsStream("sprites\\buttons\\exitbutton.png"));


    @FXML
    private void playAction() {playButtonImage.setImage(playPressed);}

    @FXML
    private void playRelease() {
        playButtonImage.setImage(playReleased);
    }

    @FXML
    public void exitGame() {
        System.exit(0);
    }

    @FXML
    public void exitReset() {
        exitButtonImage.setImage(exitRelease);
    }

    @FXML
    private void exitAction() {
        exitButtonImage.setImage(exitPressed);
    }

    void setNextScene(Scene scene4) {
            nextScene = scene4;
    }

    public void nextScreen(MouseEvent mouseEvent) {
        Stage stage4 = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage4.setScene(nextScene);
    }
}