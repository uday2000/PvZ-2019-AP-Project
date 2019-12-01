import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class SettingsScene
{
    private int panewidth = 1408;
    private int paneheight = 896;

    private MusicController MC;
    public void setmc(MusicController mc) { MC = mc;}

    private Scene prevScene;

    @FXML
    private ImageView backButtonImage, BGMButton, SFXButton;

    private Image backPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\BackButtonPressed.png"));
    private Image backReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\BackButtonReleased.png"));

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
        _stage.setScene(prevScene);
    }
    public void boxClick(MouseEvent _mouseEvent) throws Exception{
        ImageView img = ((ImageView)((Node)_mouseEvent.getSource()));
        MC.playsfx("button");
        if(img.getOpacity() == 1l) {
            img.setOpacity(0l);
            if(img.getId().equals(BGMButton.getId())) {
                MC.setbgm(false);
                MC.stopAll();
            }
            if(img.getId().equals(SFXButton.getId()))
                MC.setsfx(false);
        }
        else {
            img.setOpacity(1l);
            if(img.getId().equals(BGMButton.getId())) {
                MC.setbgm(true);
                MC.playMusic("bgm");
            }
            if(img.getId().equals(SFXButton.getId()))
                MC.setsfx(true);
        }
    }

    void setPrevScene(Scene _scene) {
        prevScene = _scene;
    }
}
