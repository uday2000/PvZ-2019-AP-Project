import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
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

import java.io.Serializable;
import java.util.Random;

public class LevelScene implements Serializable {

    public static String[][] img = new String[5][9];
    private static int panewidth = 1408;
    private static int paneheight = 896;
    private int movespeed = 100;
    private static boolean imageDropped = false;
    public transient Text counter;
    public transient Label blank, plant, walnut, potato, sun;
    private Plants[][] data;
    private MusicController MC;
    public void setmc(MusicController mc) { MC = mc;}
    private static int gridSize = 128;
    @FXML
    private transient ImageView zombie1, zombie2;
    @FXML
    private transient ImageView menuButtonImage;
    public transient GridPane grid;
    public transient Pane pane;
    private int level;
    private transient final Image peaShooterIdle = new Image(getClass().getResourceAsStream("assets\\sprites\\plants\\PeaShooterIdle.gif"));
    private transient final Image potatoMineHidden = new Image(getClass().getResourceAsStream("assets\\sprites\\plants\\PotatoMineHidden.gif"));
    private transient final Image sunFlowerIdle = new Image(getClass().getResourceAsStream("assets\\sprites\\plants\\SunflowerIdle.gif"));
    private transient final Image walnutIdle = new Image(getClass().getResourceAsStream("assets\\sprites\\plants\\WalnutIdle.gif"));
    private transient final Image emptyBlock = new Image(getClass().getResourceAsStream("assets\\sprites\\misc\\EmptyBlock.png"));
    private transient final Image menuPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\MenuButtonPressed.png"));
    private transient final Image menuReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\MenuButtonReleased.png"));
    private static boolean dragged = false;

    @FXML
    public void initialize(){
        Timeline tl = new Timeline(
                new KeyFrame(Duration.millis(10000L),
                        ae->{spawnSun();}));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
        data = new Plants[5][9];
        ZombieController zc = new ZombieController(this.pane, this.grid, level, data);
        zc.start();
    }

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
    public void cellOnDragDropped(DragEvent event) throws InterruptedException {
        String[] filePath = {"assets/sprites/plants/PeaShooterIdle.gif", "assets/sprites/plants/SunFlowerIdle.gif", "assets/sprites/plants/WalnutIdle.gif", "assets/sprites/plants/PotatoMineHidden.gif"};
        if (eventCheck(event) && event.getDragboard().hasString()) {
            Node target = (Node) event.getSource();
            int colIndex = GridPane.getColumnIndex(target);
            int rowIndex = GridPane.getRowIndex(target);
            if (img[GridPane.getRowIndex(target)][GridPane.getColumnIndex(target)] == null || img[GridPane.getRowIndex(target)][GridPane.getColumnIndex(target)].equals("assets\\sprites\\misc\\EmptyBlock.png")) {
                Plants plants = getPlant(Integer.parseInt(event.getDragboard().getString()), grid, target);
                if (plants.type.equals("SHOOTER")) {
                    shoot(target, grid, pane);
                }
                data[GridPane.getRowIndex(target)][GridPane.getColumnIndex(target)] = plants;
                imageDropped = true;
                img[rowIndex][colIndex] = filePath[Integer.parseInt(event.getDragboard().getString())];
                Thread thread = new Thread(plants);
                thread.start();
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
        if(img_path!= null && img_path.equals("assets/sprites/plants/SunflowerShoot.gif")) {
            ((ImageView) target).setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\plants\\SunflowerIdle.gif")));
            img[GridPane.getRowIndex(target)][GridPane.getColumnIndex(target)] = "assets\\sprites\\plants\\SunflowerIdle.gif";
            int coins = Integer.parseInt(counter.getText());
            counter.setText(Integer.toString(coins + 25));
        }
            return img_path;
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

    private void spawnSun() {
        Random r = new Random();
        int _x = r.nextInt(1024) + 256;
        int _y = r.nextInt(512) + 256;
        int spawn_y = _y - 800;

        ImageView Sun = new ImageView();
        Sun.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\misc\\SunToken.gif")));
        Sun.setX(_x);
        Sun.setY(spawn_y);

        Pane curpane = (Pane)menuButtonImage.getParent();
        curpane.getChildren().add(Sun);

        Sun.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            counter.setText(Integer.toString(Integer.parseInt(counter.getText()) + 25));
            curpane.getChildren().remove(Sun);
            event.consume();
        });

        TranslateTransition TT = new TranslateTransition();
        TT.setNode(Sun);
        TT.setByY(800);
        TT.setDuration(Duration.seconds(10));
        TT.play();
    }

    @FXML
    private void menuPress() throws InterruptedException {
        menuButtonImage.setImage(menuPressed);
    }
    @FXML
    private void menuRelease() {
        menuButtonImage.setImage(menuReleased);
    }
    public void menuclick(MouseEvent _mouseEvent) throws Exception{
        FXMLLoader exitloader = new FXMLLoader(getClass().getResource("FXMLS/MenuPanel.fxml"));
        Parent exitparent = exitloader.load();
        Scene exitscene = new Scene(exitparent, panewidth ,paneheight);
        PopupController popupcontroller = (PopupController) exitloader.getController();
        popupcontroller.setPrevStageScene((Stage)((Node)_mouseEvent.getSource()).getScene().getWindow());

        Stage exitstage = new Stage();
        exitstage.setScene(exitscene);
        exitscene.setFill(Color.TRANSPARENT);
        exitstage.initStyle(StageStyle.TRANSPARENT);
        exitstage.show();
    }

    private Plants getPlant(int i, GridPane gridPane, Node node) {
        switch (i) {
            case 0:
                return new Shooter(gridPane, node, pane);

            case 1:
                return new Sunflower(gridPane, node);

            case 2:
                return new Walnut(gridPane, node);

            case 3:
                return new PotatoMine(gridPane, node);

            default:
                return new Shooter(gridPane, node, pane);
        }
    }

    static void shoot(Node node, GridPane gridPane, Pane pane) throws InterruptedException {
        int y = GridPane.getRowIndex(node);
        int x = GridPane.getColumnIndex(node);
        ImageView pea = new ImageView();
        System.out.println("x and y are" + x + " " + y);
        pea.setImage(new Image(LevelScene.class.getResourceAsStream("assets/sprites/misc/BulletPea.png")));
        Bounds gridBounds = gridPane.getBoundsInParent();
        pea.setLayoutX(gridBounds.getMinX() + (x * gridSize) + 128);
        pea.setLayoutY(gridBounds.getMinY() + (y * gridSize) + 40);
        pane.getChildren().add(pea);
        KeyValue xValue = new KeyValue(pea.translateXProperty(), 1100);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000 * (9 - y)), xValue);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.play();
    }

    public void setlevel(int lvl){
        level = lvl;
    }
}