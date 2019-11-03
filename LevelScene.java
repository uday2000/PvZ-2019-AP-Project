import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.transform.Translate.*;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LevelScene {

    @FXML
    public void initialize(){
        moveZombie();
    }

    @FXML
    ImageView zombie1, zombie2;

    int movespeed = 100;

    private Scene prevScene;

    @FXML
    private ImageView menuButtonImage = new ImageView();

    private Image menuPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\MenuButtonPressed.png"));
    private Image menuReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\MenuButtonReleased.png"));

    @FXML
    private void menuPress() throws InterruptedException {
        menuButtonImage.setImage(menuPressed);
    }

    @FXML
    private void menuRelease() {
        menuButtonImage.setImage(menuReleased);
    }

    void setPrevScene(Scene _scene) {
        prevScene = _scene;
    }

   public void menuclick(MouseEvent _mouseEvent) throws Exception{
       FXMLLoader exitloader = new FXMLLoader(getClass().getResource("FXMLS/MenuPanel.fxml"));
       Parent exitparent = exitloader.load();
       Scene exitscene = new Scene(exitparent, 352 ,224);
       PopupController popupcontroller = (PopupController) exitloader.getController();
       popupcontroller.setPrevStageScene((Stage)menuButtonImage.getScene().getWindow(), prevScene);

       Stage exitstage = new Stage();
       exitstage.setScene(exitscene);
       exitscene.setFill(Color.TRANSPARENT);
       exitstage.initStyle(StageStyle.TRANSPARENT);
       exitstage.show();
   }
    public void backScene(MouseEvent _mouseEvent){
        Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
        _stage.setScene(prevScene);
    }
    private void Zombiemover(ImageView zm){
        TranslateTransition move = new TranslateTransition();
        move.setNode(zm);
        move.setByX(-500);
        move.setDuration(Duration.seconds(movespeed));
        move.play();
    }

    public void moveZombie(){
        Zombiemover(zombie1);
        Zombiemover(zombie2);
   }
}