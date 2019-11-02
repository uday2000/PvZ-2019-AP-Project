import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TitleScene {

    @FXML
    private ImageView exitButtonImage = new ImageView();
    @FXML
    private ImageView playButtonImage = new ImageView();

    private Scene nextScene;

    private Image playPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\PlayButtonPressed.png"));
    private Image playReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\PlayButtonReleased.png"));
    private Image exitPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\ExitButtonPressed.png"));
    private Image exitRelease = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\ExitButtonReleased.png"));


    @FXML
    private void playPress() throws InterruptedException {
        playButtonImage.setImage(playPressed);
    }

    @FXML
    private void exitPress() throws InterruptedException {
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

    void setNextScene(Scene _scene) {
        nextScene = _scene;
    }

    public void nextScreen(MouseEvent _mouseEvent) {
        Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
        _stage.setScene(nextScene);
    }
}