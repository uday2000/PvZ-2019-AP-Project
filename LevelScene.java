import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LevelScene {

    public static String[][] img = new String[5][9];
    private int panewidth = 1408;
    private int paneheight = 896;
    private static boolean imageDropped = false;
    public Text counter;
    public Label blank, plant, walnut, potato, sun;
    public GridPane grid;
    public Pane pane;
    public static Label timer;
    private Image peaShooterIdle = new Image(getClass().getResourceAsStream("assets\\sprites\\plants\\PeaShooterIdle.gif"));
    private Image potatoMineHidden = new Image(getClass().getResourceAsStream("assets\\sprites\\plants\\PotatoMineHidden.gif"));
    private Image sunFlowerIdle = new Image(getClass().getResourceAsStream("assets\\sprites\\plants\\SunflowerIdle.gif"));
    private Image walnutIdle = new Image(getClass().getResourceAsStream("assets\\sprites\\plants\\WalnutIdle.gif"));
    private Image emptyBlock = new Image(getClass().getResourceAsStream("assets\\sprites\\misc\\EmptyBlock.png"));
    private static boolean dragged = false;
    private boolean eventCheck(DragEvent event) {
        Label[] sidebar = {plant, sun, walnut, potato};
        for (int i = 0; i < 4; i++) {
            if (event.getGestureSource() == (sidebar[i])) {
                return true;
            }
        }
        return false;
    }

    public void onDragDetected(MouseEvent event) {
        imageDropped = false;
        Label[] sidebar = {plant, sun, walnut, potato};
        dragged = true;
        for (int i = 0; i < 4; i++) {
            if (sidebar[i] == event.getSource() && Integer.parseInt(counter.getText()) >= 25) {
                Dragboard db = sidebar[i].startDragAndDrop(TransferMode.COPY);
                ClipboardContent content = new ClipboardContent();
                content.putString(Integer.toString(i));
                db.setContent(content);
                event.consume();
            }
        }
    }

    public void cellOnDragOver(DragEvent event) {
        if (eventCheck(event) && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
    }

    public void cellOnDragExitted(DragEvent event) {
        Node target = (Node) event.getSource();
        String prevState = img[GridPane.getRowIndex(target)][GridPane.getColumnIndex(target)];
        double prevOpacity = target.getOpacity();
        if (prevState==null || prevState.equals("assets\\sprites\\misc\\EmptyBlock.png")){
            prevState="assets\\sprites\\misc\\EmptyBlock.png";
            prevOpacity = 0.2;
        }
        if (!imageDropped) {
            if (eventCheck(event) && event.getDragboard().hasString()) {
                ((ImageView) target).setImage(new Image(getClass().getResourceAsStream(prevState)));
                ((ImageView) target).setOpacity(prevOpacity);
                img[GridPane.getRowIndex(target)][GridPane.getColumnIndex(target)] = prevState;
                event.consume();
            }
        }
    }

    public void cellOnDragEntered(DragEvent event) {
        String[] filePath = {"assets/sprites/plants/PeaShooterIdle.gif", "assets/sprites/plants/SunFlowerIdle.gif", "assets/sprites/plants/WalnutIdle.gif", "assets/sprites/plants/PotatoMineHidden.gif"};
        if (eventCheck(event) && event.getDragboard().hasString()) {
            Node target = (Node) event.getSource();
            int colIndex = GridPane.getColumnIndex(target);
            int rowIndex = GridPane.getRowIndex(target);

            if (img[GridPane.getRowIndex(target)][GridPane.getColumnIndex(target)] == null || img[GridPane.getRowIndex(target)][GridPane.getColumnIndex(target)].equals("assets\\sprites\\misc\\EmptyBlock.png")) {
                ((ImageView) target).setImage(new Image(getClass().getResourceAsStream(filePath[Integer.parseInt(event.getDragboard().getString())])));
                ((ImageView) target).setOpacity(1);
            }
        }
        event.consume();
    }

    public void cellOnDragDropped(DragEvent event) {
        Image[] images = {peaShooterIdle, sunFlowerIdle, walnutIdle, potatoMineHidden, emptyBlock};
        String[] filePath = {"assets/sprites/plants/PeaShooterIdle.gif", "assets/sprites/plants/SunFlowerIdle.gif", "assets/sprites/plants/WalnutIdle.gif", "assets/sprites/plants/PotatoMineHidden.gif"};
        if (eventCheck(event) && event.getDragboard().hasString()) {
            Node target = (Node) event.getSource();
            int colIndex = GridPane.getColumnIndex(target);
            int rowIndex = GridPane.getRowIndex(target);
            if (img[GridPane.getRowIndex(target)][GridPane.getColumnIndex(target)] == null || img[GridPane.getRowIndex(target)][GridPane.getColumnIndex(target)].equals("assets\\sprites\\misc\\EmptyBlock.png")) {
                ((ImageView) target).setImage(new Image(getClass().getResourceAsStream(filePath[Integer.parseInt(event.getDragboard().getString())])));
                ((ImageView) target).setOpacity(1);
                imageDropped = true;
                img[rowIndex][colIndex] = filePath[Integer.parseInt(event.getDragboard().getString())];
                CellAction ca = new CellAction(rowIndex, colIndex, getType(Integer.parseInt(event.getDragboard().getString())), grid);
                setCoins(Integer.parseInt(event.getDragboard().getString()));
            }
        }
        dragged = false;
        event.setDropCompleted(true);
        event.consume();
    }

    public String returnURL(MouseEvent mouseEvent) {
        Node target = (Node) mouseEvent.getSource();
        String img_path = img[GridPane.getRowIndex(target)][GridPane.getColumnIndex(target)];
        if (img_path.equals("assets/sprites/plants/SunflowerShoot.gif")) {
            ((ImageView) target).setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\plants\\SunflowerIdle.gif")));
            img[GridPane.getRowIndex(target)][GridPane.getColumnIndex(target)] = "assets\\sprites\\plants\\SunflowerIdle.gif";
            int coins = Integer.parseInt(counter.getText());
            counter.setText(Integer.toString(coins + 25));
        }
        return img_path;
    }

    private String getType(int index) {
        switch (index) {
            case 0:
                return "SHOOTER";
            case 1:
                return "SUN";
            case 2:
                return "WALNUT";
            case 3:
                return "POTATO";
        }
        return null;
    }

    public void setCoins(int i) {
        int coins = Integer.parseInt(counter.getText());
        counter.setText(Integer.toString(coins + 10));
        switch (i) {
            case 0:
                counter.setText(Integer.toString(coins - 100));
                break;
            case 1:
                counter.setText(Integer.toString(coins - 50));
                break;
            case 2:
                counter.setText(Integer.toString(coins - 50));
                break;
            case 3:
                counter.setText(Integer.toString(coins - 25));
                break;
        }
    }

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
        Scene exitscene = new Scene(exitparent, panewidth ,paneheight);
        PopupController popupcontroller = (PopupController) exitloader.getController();
        popupcontroller.setPrevStageScene((Stage)menuButtonImage.getScene().getWindow(), prevScene);

        Stage exitstage = new Stage();
        exitstage.setScene(exitscene);
        exitscene.setFill(Color.TRANSPARENT);
        exitstage.initStyle(StageStyle.TRANSPARENT);
        exitstage.show();
    }

    private void Zombiemover(ImageView zm){
        TranslateTransition move = new TranslateTransition();
        move.setNode(zm);
        move.setByX((-1*panewidth) - 500);
        move.setDuration(Duration.seconds(movespeed));
        move.play();
    }

    public void moveZombie(){
        Zombiemover(zombie1);
        Zombiemover(zombie2);
    }
}