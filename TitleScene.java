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

    void setNextScene(Scene _scene) {
        nextScene = _scene;
    }

    public void exitClick(MouseEvent _mouseEvent) throws Exception{
        FXMLLoader exitloader = new FXMLLoader(getClass().getResource("FXMLS/ExitPanel.fxml"));
        Parent exitparent = exitloader.load();
        Scene exitscene = new Scene(exitparent, 352 ,224);

        Stage exitstage = new Stage();
        exitstage.setScene(exitscene);
        exitscene.setFill(Color.TRANSPARENT);
        exitstage.initStyle(StageStyle.TRANSPARENT);
        exitstage.show();
    }

    public void nextScreen(MouseEvent _mouseEvent) {
        Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
        _stage.setScene(nextScene);
    }
}