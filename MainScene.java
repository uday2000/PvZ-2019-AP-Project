import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.text.*;

public class MainScene {

    @FXML
    private ImageView backSlotButtonImage = new ImageView();
    @FXML
    private ImageView playGameButtonImage = new ImageView();
    @FXML
    private ImageView levelButtonImage = new ImageView();
    @FXML
    private ImageView creditsButtonImage = new ImageView();
    @FXML
    private ImageView exitButtonImage = new ImageView();

    private Scene prevScene, playScene, creditsScene, levelselScene, gamePlayScene;
    private int panewidth = 1408;
    private int paneheight = 896;

    public Text nametext;

    private Image backSlotPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\ChangePlayerButtonPressed.png"));
    private Image backSlotReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\ChangePlayerButtonReleased.png"));
    private Image levelPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\LevelSelectButtonPressed.png"));
    private Image levelReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\LevelSelectButtonReleased.png"));
    private Image creditsPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\CreditsButtonPressed.png"));
    private Image creditsReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\CreditsButtonReleased.png"));
    private Image playPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\AdventureButtonPressed.png"));
    private Image playReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\AdventureButtonReleased.png"));
    private Image exitPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\ExitGButtonPressed.png"));
    private Image exitReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\ExitGButtonReleased.png"));

    @FXML
    private void backSlotPress() throws InterruptedException {
        backSlotButtonImage.setImage(backSlotPressed);
    }

    @FXML
    private void backSlotRelease() {
        backSlotButtonImage.setImage(backSlotReleased);
    }

    @FXML
    private void playGamePress() throws InterruptedException {
        playGameButtonImage.setImage(playPressed);
    }

    @FXML
    private void playGameRelease() {
        playGameButtonImage.setImage(playReleased);
    }

    @FXML
    private void levelPress() throws InterruptedException {
        levelButtonImage.setImage(levelPressed);
    }

    @FXML
    private void levelRelease() {
        levelButtonImage.setImage(levelReleased);
    }

    @FXML
    private void exitPress() throws InterruptedException {
        exitButtonImage.setImage(exitPressed);
    }

    @FXML
    private void exitRelease() {
        exitButtonImage.setImage(exitReleased);
    }

    @FXML
    private void creditsPress() throws InterruptedException {
        creditsButtonImage.setImage(creditsPressed);
    }

    @FXML
    private void creditsRelease() {
        creditsButtonImage.setImage(creditsReleased);
    }

    @FXML
    private void backSlotScreen(MouseEvent _mouseEvent) {
        Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
        _stage.setScene(prevScene);
    }
    @FXML
    private void playScreen(MouseEvent _mouseEvent) throws Exception{
        Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
        FXMLLoader LevelLoader = new FXMLLoader(getClass().getResource("fxmls/LevelScreen.fxml"));
        Parent levelParent = LevelLoader.load();
        Scene levelScene = new Scene(levelParent, panewidth, paneheight);
        LevelScene levelsceneController =  LevelLoader.getController();
        levelsceneController.setPrevScene((Scene)((Node) _mouseEvent.getSource()).getScene());
        _stage.setScene(levelScene);
    }
    @FXML
    private void levelSelectScreen(MouseEvent _mouseEvent) {
        Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
        _stage.setScene(levelselScene);
    }
    @FXML
    private void creditsScreen(MouseEvent _mouseEvent) {
        Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
        _stage.setScene(creditsScene);
    }
    public void exitGame(MouseEvent _mouseEvent) throws Exception
    {
        FXMLLoader exitloader = new FXMLLoader(getClass().getResource("FXMLS/ExitPanel.fxml"));
        Parent exitparent = exitloader.load();
        Scene exitscene = new Scene(exitparent, panewidth, paneheight);

        Stage exitstage = new Stage();
        exitstage.setScene(exitscene);
        exitscene.setFill(Color.TRANSPARENT);
        exitstage.initStyle(StageStyle.TRANSPARENT);
        exitstage.show();
    }

    void setPrevScene(Scene _scene) {
        prevScene = _scene;
    }
    void setLevelSelScene(Scene _scene) {
        levelselScene = _scene;
    }
    void setCreditsScene(Scene _scene) {
        creditsScene = _scene;
    }
}