import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SlotScene {

    @FXML
    private ImageView backButtonImage = new ImageView();
    @FXML
    private ImageView newGameButtonImage = new ImageView();
    @FXML
    private ImageView loadGameButtonImage = new ImageView();

    private Scene prevScene;

    private Image backPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\BackButtonPressed.png"));
    private Image backReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\BackButtonReleased.png"));
    private Image newGamePressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\NewGamePressed.png"));
    private Image newGameReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\NewGameReleased.png"));
    private Image loadGamePressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\LoadGamePressed.png"));
    private Image loadGameReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\LoadGameReleased.png"));

    @FXML
    private void backPress() throws InterruptedException {
        backButtonImage.setImage(backPressed);
    }

    @FXML
    private void newGamePress() throws InterruptedException {
        newGameButtonImage.setImage(newGamePressed);
    }

    @FXML
    private void loadGamePress() throws InterruptedException {
        loadGameButtonImage.setImage(loadGamePressed);
    }

    @FXML
    private void backRelease() {
        backButtonImage.setImage(backReleased);
    }

    @FXML
    private void loadGameRelease() {
        loadGameButtonImage.setImage(loadGameReleased);
    }

    @FXML
    private void newGameRelease() {
        newGameButtonImage.setImage(newGameReleased);
    }

    @FXML
    private void backScreen(MouseEvent _mouseEvent) {
        Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
        _stage.setScene(prevScene);
    }


    void setPrevScene(Scene _scene) {
        prevScene = _scene;
    }

}