import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PopupController {

    private Stage prevstage;

    private MusicController MC;
    public void setmc(MusicController mc) { MC = mc;}

    private Scene nscene;
    private MainScene mcont;
    private SlotScene SS;

    @FXML
    private TextField ntext;

    @FXML
    private ImageView yesButtonImage, noButtonImage, saveButtonImage, exitButtonImage, nextButtonImage;

    private Image yesPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\YesButtonPressed.png"));
    private Image yesReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\YesButtonReleased.png"));
    private Image noPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\NoButtonPressed.png"));
    private Image noReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\NoButtonReleased.png"));
    private Image savePressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\SaveButtonPressed.png"));
    private Image saveReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\SaveButtonReleased.png"));
    private Image exitPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\ResumeButtonPressed.png"));
    private Image exitReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\ResumeButtonReleased.png"));
    private Image nextPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\NextButtonPressed.png"));
    private Image nextReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\NextButtonReleased.png"));

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
    private void nextPress() throws InterruptedException{
        nextButtonImage.setImage(nextPressed);
    }
    @FXML
    private void nextRelease() {
        nextButtonImage.setImage(nextReleased);
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

    public void nextGame(MouseEvent _mouseEvent) {
        Player p = new Player(ntext.getText());
        SS.addslot(p);
        mcont.setPlayer(p);
        prevstage.setScene(nscene);
    }

    public void setPrevStageScene(Stage _stage) {
        prevstage = _stage;
    }

    public void goBack(MouseEvent _mouseEvent) {
        Stage _stage = (Stage)noButtonImage.getScene().getWindow();
        _stage.close();
    }

    public void savegame(MouseEvent _mouseEvent){
        prevstage.close();
        Stage _stage = (Stage)saveButtonImage.getScene().getWindow();
        _stage.close();
    }

    public void resumeGame(MouseEvent _mouseEvent){
        Stage _stage = (Stage)exitButtonImage.getScene().getWindow();
        _stage.close();
    }
    public void setnextsceneandcont(Scene _scene, MainScene maincont) {
        nscene = _scene;
        mcont = maincont;
    }
    public void setcurscene(SlotScene s) {
        SS = s;
    }
}
