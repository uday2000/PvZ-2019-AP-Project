import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class SlotScene {

    @FXML
    private ImageView slot1, slot2, slot3, slot4, backButtonImage;

    private Scene prevScene;
    private Scene nextScene;

    private Image backPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\BackButtonPressed.png"));
    private Image backReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\BackButtonReleased.png"));
    private Image newGamePressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\NewGamePressed.png"));
    private Image newGameReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\NewGameReleased.png"));
    private Image loadGamePressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\LoadGamePressed.png"));
    private Image loadGameReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\LoadGameReleased.png"));

    @FXML
    private void buttonPress(MouseEvent _mouseEvent) throws InterruptedException {
        ImageView btn = (ImageView)_mouseEvent.getSource();
        if(imgcomp(btn.getImage(), newGameReleased))
            btn.setImage(newGamePressed);
        else
            btn.setImage(loadGamePressed);
    }
    @FXML
    private void buttonRelease(MouseEvent _mouseEvent) throws InterruptedException {
        ImageView btn = (ImageView)_mouseEvent.getSource();
        if(imgcomp(btn.getImage(), newGamePressed))
            btn.setImage(newGameReleased);
        else
            btn.setImage(loadGameReleased);
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
    private void backScreen(MouseEvent _mouseEvent) {
        Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
        _stage.setScene(prevScene);
    }

    void setPrevScene(Scene _scene) {
        prevScene = _scene;
    }
    void setNextScene(Scene _scene) {
        nextScene = _scene;
    }

    @FXML
    private void playScreen(MouseEvent _mouseEvent) {
        ImageView btnimg = (ImageView) _mouseEvent.getSource();
        if(!imgcomp(btnimg.getImage(), newGamePressed)) {
            Stage _stage = (Stage) ((Node) _mouseEvent.getSource()).getScene().getWindow();
            _stage.setScene(nextScene);
        }
        else{
            System.out.println("newgame");
        }
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