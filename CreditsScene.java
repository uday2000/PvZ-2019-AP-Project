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

public class CreditsScene
{
    private int panewidth = 1408;
    private int paneheight = 896;

    private Scene prevScene;

    @FXML
    private ImageView backButtonImage;

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
        Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
        _stage.setScene(prevScene);
    }

    void setPrevScene(Scene _scene) {
        prevScene = _scene;
    }
}
