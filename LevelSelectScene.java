import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class LevelSelectScene
{
    private int panewidth = 1408;
    private int paneheight = 896;

    @FXML
    private ImageView backButtonImage, button1 = new ImageView(), button2 = new ImageView(), button3 = new ImageView(), button4 = new ImageView(), button5 = new ImageView();
    private Scene prevScene;
    private MainScene msobj;

    private Image backPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\BackButtonPressed.png"));
    private Image backReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\BackButtonReleased.png"));
    private Image b1Pressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\UnlockedLevelButtonPressed1.png"));
    private Image b1Released = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\UnlockedLevelButtonReleased1.png"));
    private Image b2Pressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\UnlockedLevelButtonPressed2.png"));
    private Image b2Released = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\UnlockedLevelButtonReleased2.png"));
    private Image b3Pressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\UnlockedLevelButtonPressed3.png"));
    private Image b3Released = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\UnlockedLevelButtonReleased3.png"));
    private Image b4Pressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\UnlockedLevelButtonPressed4.png"));
    private Image b4Released = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\UnlockedLevelButtonReleased4.png"));
    private Image b5Pressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\UnlockedLevelButtonPressed5.png"));
    private Image b5Released = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\UnlockedLevelButtonReleased5.png"));
    private Image lockedbutton = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\LockedLevelButton.png"));

    private int[] unlockedlevels = {1, 1, 0, 0, 0};
    private ArrayList<ImageView> levels = new ArrayList<ImageView>(Arrays.asList(button1, button2, button3, button4, button5));
    private ArrayList<Image> levelunlockimg = new ArrayList<Image>(Arrays.asList(b1Released, b2Released, b3Released, b4Released, b5Released));

    @FXML
    public void initialize() {
        for(int i=0; i<unlockedlevels.length; i++) {
            if(unlockedlevels[i] == 1)
                levels.get(i).setImage(levelunlockimg.get(i));
        }
    }

    @FXML
    private void backPress() throws InterruptedException {
        backButtonImage.setImage(backPressed);
    }
    @FXML
    private void backRelease() {
        backButtonImage.setImage(backReleased);
    }

    @FXML
    private void b1Press() throws InterruptedException {
            button1.setImage(b1Pressed);
    }
    @FXML
    private void b1Release() {
            button1.setImage(b1Released);
    }
    @FXML
    private void b2Press() throws InterruptedException {
        if(!imgcomp(button2.getImage(), lockedbutton))
            button2.setImage(b2Pressed);
    }
    @FXML
    private void b2Release() {
        if(!imgcomp(button2.getImage(), lockedbutton))
            button2.setImage(b2Released);
    }
    @FXML
    private void b3Press() throws InterruptedException {
        if(!imgcomp(button3.getImage(), lockedbutton))
          button3.setImage(b3Pressed);
    }
    @FXML
    private void b3Release() {
        if(!imgcomp(button3.getImage(), lockedbutton))
            button3.setImage(b3Released);
    }
    @FXML
    private void b4Press() throws InterruptedException {
        if(!imgcomp(button4.getImage(), lockedbutton))
           button4.setImage(b4Pressed);
    }
    @FXML
    private void b4Release() {
        if(!imgcomp(button4.getImage(), lockedbutton))
            button4.setImage(b4Released);
    }
    @FXML
    private void b5Press() throws InterruptedException {
        if(!imgcomp(button5.getImage(), lockedbutton))
            button5.setImage(b5Pressed);
    }
    @FXML
    private void b5Release() {
        if(!imgcomp(button5.getImage(), lockedbutton))
            button5.setImage(b5Released);
    }

    @FXML
    private void selectLevel1(MouseEvent _mouseEvent)
    {
        if(!imgcomp(((ImageView)_mouseEvent.getSource()).getImage(), lockedbutton)) {
            msobj.setlevelnum(1);
            Stage _stage = (Stage) ((Node) _mouseEvent.getSource()).getScene().getWindow();
            _stage.setScene(prevScene);
        }
    }
    @FXML
    private void selectLevel2(MouseEvent _mouseEvent)
    {
        if(!imgcomp(((ImageView)_mouseEvent.getSource()).getImage(), lockedbutton)) {
            msobj.setlevelnum(2);
            Stage _stage = (Stage) ((Node) _mouseEvent.getSource()).getScene().getWindow();
            _stage.setScene(prevScene);
        }
    }
    @FXML
    private void selectLevel3(MouseEvent _mouseEvent)
    {
        if(!imgcomp(((ImageView)_mouseEvent.getSource()).getImage(), lockedbutton)) {
            msobj.setlevelnum(3);
            Stage _stage = (Stage) ((Node) _mouseEvent.getSource()).getScene().getWindow();
            _stage.setScene(prevScene);
        }
    }
    @FXML
    private void selectLevel4(MouseEvent _mouseEvent)
    {
        if(!imgcomp(((ImageView)_mouseEvent.getSource()).getImage(), lockedbutton)) {
            msobj.setlevelnum(4);
            Stage _stage = (Stage) ((Node) _mouseEvent.getSource()).getScene().getWindow();
            _stage.setScene(prevScene);
        }
    }
    @FXML
    private void selectLevel5(MouseEvent _mouseEvent)
    {
        if(!imgcomp(((ImageView)_mouseEvent.getSource()).getImage(), lockedbutton)){
            msobj.setlevelnum(5);
            Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
            _stage.setScene(prevScene);
        }
    }

    public void backClick(MouseEvent _mouseEvent) throws Exception{
        Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
        _stage.setScene(prevScene);
    }

    void setPrevScene(Scene _scene, MainScene _mainscene) {
        prevScene = _scene;
        msobj = _mainscene;
    }

    private boolean imgcomp(Image i1, Image i2)
    {
        int w = (int) i1.getWidth(), h = (int) i1.getHeight();
        PixelReader r1 = i1.getPixelReader(), r2 = i2.getPixelReader();
        double nsp = IntStream.range(0, w).parallel().mapToLong(i -> IntStream.range(0, h).parallel().filter(j -> r1.getArgb(i, j) != r2.getArgb(i, j)).count()).sum();
        if(nsp > 0)
            return false;
        return true;
    }
}
