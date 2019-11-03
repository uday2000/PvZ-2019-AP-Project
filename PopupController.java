import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PopupController {

    private Stage prevstage;
    private Scene prevscene;

    @FXML
    private ImageView yesButtonImage = new ImageView();
    @FXML
    private ImageView noButtonImage = new ImageView();
    @FXML
    private ImageView saveButtonImage = new ImageView();
    @FXML
    private ImageView exitButtonImage = new ImageView();

    private Image yesPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\YesButtonPressed.png"));
    private Image yesReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\YesButtonReleased.png"));
    private Image noPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\NoButtonPressed.png"));
    private Image noReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\NoButtonReleased.png"));
    private Image savePressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\SaveButtonPressed.png"));
    private Image saveReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\SaveButtonReleased.png"));
    private Image exitPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\ResumeButtonPressed.png"));
    private Image exitReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\ResumeButtonReleased.png"));

    @FXML
    private void yesPress() throws InterruptedException {
        yesButtonImage.setImage(yesPressed);
    }

    @FXML
    private void noPress() throws InterruptedException{
        noButtonImage.setImage(noPressed);
    }

    @FXML
    private void yesRelease() {
        yesButtonImage.setImage(yesReleased);
    }

    @FXML
    private void noRelease() {
        noButtonImage.setImage(noReleased);
    }

    @FXML
    private void savePress() throws InterruptedException{
        saveButtonImage.setImage(savePressed);
    }

    @FXML
    private void saveRelease() {
        saveButtonImage.setImage(saveReleased);
    }
    @FXML
    private void exitPress() throws InterruptedException{
        exitButtonImage.setImage(exitPressed);
    }

    @FXML
    private void exitRelease() {
        exitButtonImage.setImage(exitReleased);
    }

    public void exitGame(MouseEvent _mouseEvent) {
        System.exit(0);
    }

    public void setPrevStageScene(Stage _stage, Scene _scene)
    {
        prevstage = _stage;
        prevscene = _scene;
    }

    public void goBack(MouseEvent _mouseEvent) {
        Stage _stage = (Stage)noButtonImage.getScene().getWindow();
        _stage.close();
    }

    public void savegame(MouseEvent _mouseEvent){
        prevstage.setScene(prevscene);
        Stage _stage = (Stage)saveButtonImage.getScene().getWindow();
        _stage.close();
    }

    public void resumeGame(MouseEvent _mouseEvent){
        Stage _stage = (Stage)exitButtonImage.getScene().getWindow();
        _stage.close();
    }
}
