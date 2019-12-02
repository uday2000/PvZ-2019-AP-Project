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

public class LevelEndScene
{
    private MusicController MC;
    public void setmc(MusicController mc) { MC = mc;}

    Player player;

    @FXML
    private ImageView backButtonImage, nextButtonImage;

    private Image backPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\BackButtonPressed.png"));
    private Image backReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\BackButtonReleased.png"));
    private Image nextPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\NextButtonPressed.png"));
    private Image nextReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\NextButtonReleased.png"));
    private Image winBG = new Image(getClass().getResourceAsStream("assets\\sprites\\bgs\\WinBG.png"));
    private Image winBG1 = new Image(getClass().getResourceAsStream("assets\\sprites\\bgs\\WinBG0.png"));
    private Image winBG2 = new Image(getClass().getResourceAsStream("assets\\sprites\\bgs\\WinBG1.png"));
    private Image winBG3 = new Image(getClass().getResourceAsStream("assets\\sprites\\bgs\\WinBG2.png"));

    @FXML
    private void backPress() throws InterruptedException {
        backButtonImage.setImage(backPressed);
    }
    @FXML
    private void backRelease() {
        backButtonImage.setImage(backReleased);
    }

    public void backClick(MouseEvent _mouseEvent) throws Exception{
        MC.playsfx("button");
        Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
        _stage.close();
    }

    @FXML
    private void nextPress() throws InterruptedException {
        nextButtonImage.setImage(nextPressed);
    }
    @FXML
    private void nextRelease() {
        nextButtonImage.setImage(nextReleased);
    }

    public void nextClick(MouseEvent _mouseEvent) throws Exception{
        MC.playsfx("button");
        Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
        _stage.close();
    }
}
