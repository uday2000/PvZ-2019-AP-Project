import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class Sun implements Runnable
{
    private static String sunToken = ("assets/sprites/plants/SunflowerShoot.gif");
    private static String sunIdle = ("assets/sprites/plants/SunflowerIdle.gif");
    private Node node;


    Sun( Node node) {
        this.node = node;
    }

    @Override
    public void run() {
        Random r = new Random();
        try {
            while (true) {
                if (!(LevelScene.img[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)].equals("assets/sprites/plants/SunflowerShoot.gif"))) {

                    Thread.sleep(10000 + (r.nextInt(5)*1000));
                    ((ImageView) node).setImage(new Image(getClass().getResourceAsStream(sunToken)));
                    LevelScene.img[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)]="assets/sprites/plants/SunflowerShoot.gif";
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
