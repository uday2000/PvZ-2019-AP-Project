import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class CellAction {

    private int posX;
    private int posY;
    private String type;
    private GridPane gridPane;
    private String[][] img;


    public CellAction(int x, int y, String type, GridPane gridPane) {
        this.posX = x;
        this.posY = y;
        this.type = type;
        this.gridPane = gridPane;
        createThread(type);
    }

    public void createThread(String type) {
        Node node = getNode();
        if (type.equals("POTATO")) {
            Thread thread = new Thread(new PotatoMine(node));
            thread.start();
        } else if (type.equals("SUN")) {
            Thread thread = new Thread(new Sun(node));
            thread.start();
        } else if (type.equals("SHOOTER")) {
            ImageView pea = new ImageView();
            pea.setImage(new Image(getClass().getResourceAsStream("assets/sprites/misc/BulletPea.png")));
            KeyValue xValue = new KeyValue(pea.xProperty(), 100);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), xValue);
            Timeline timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.setAutoReverse(true);
            timeline.getKeyFrames().addAll(keyFrame);
            timeline.play();
        }
    }

    private Node getNode() {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == posY && GridPane.getRowIndex(node) == posX) {
                return node;
            }
        }
        return null;
    }
}
