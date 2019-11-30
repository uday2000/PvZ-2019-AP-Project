import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Vector;

public class ZombieController extends Thread {

    private final static int[] levelZombie = {5, 10, 15, 20, 25};
    private static int[][] position = {{1306, 136}, {1306, 264}, {1306, 405}, {1306, 520}, {1306, 648}};
    private static HashMap<Integer, Zombie> masterZombieList = new HashMap<Integer, Zombie>();
    private Vector<Zombie> zombies = new Vector<>();
    private Vector<Zombie> zombieRunning = new Vector<>();
    private int zombieLeft;
    private int level;
    private GridPane gridPane;
    private Pane rootPane;
    private Image bucketZombie = new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\BucketZombieWalking.gif"));
    private Image coneZombie = new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\ConeZombieWalking.gif"));
    private Image normalZombie = new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\ZombieWalking.gif"));
    private Plants[][] plants;

    ZombieController(Pane rootPane, GridPane gridPane, int level, Plants[][] data) {
        this.gridPane = gridPane;
        this.rootPane = rootPane;
        this.level = level;
        zombieLeft = levelZombie[level];
        plants = data;
        populateZombie();
    }

    private void populateZombie() {
        for (int i = 0; i < zombieLeft; i++) {
            zombies.add(randomZombie());
        }
    }

    private Zombie randomZombie() {
        int i = (int) (Math.random() * 3);
        switch (i) {
            case 0:
                if (masterZombieList.containsKey(0)) {
                    return masterZombieList.get(0).clone();
                } else {
                    BucketZombie bzm = new BucketZombie(bucketZombie, gridPane,plants);
                    masterZombieList.put(0, bzm);
                    return bzm;
                }
            case 1:
                if (masterZombieList.containsKey(1)) {
                    return masterZombieList.get(1).clone();
                } else {
                    ConeZombie czm = new ConeZombie(coneZombie, gridPane,plants);
                    masterZombieList.put(1, czm);
                    return czm;
                }
            case 2:
                if (masterZombieList.containsKey(2)) {
                    return masterZombieList.get(2).clone();
                } else {
                    NormalZombie nzm = new NormalZombie(normalZombie, gridPane,plants);
                    masterZombieList.put(2, nzm);
                    return nzm;
                }

            default:
                return new NormalZombie(normalZombie, gridPane,plants);
        }
    }


    @Override
    public void run() {
        int i = 0;
        for (Zombie zm : zombies) {
            Timeline timeline = new Timeline(
                    new KeyFrame(
                            Duration.seconds(2 + i),
                            event -> {
                                showZombie(zm);
                                zombieLeft--;
                            }
                    )
            );
            timeline.setCycleCount(1);
            timeline.play();
            //System.out.println(zm);
            Thread thread = new Thread(zm);
            thread.start();
            i = i + 3;
        }
    }

    private void showZombie(Zombie zm) {
        int i = (int) (Math.random() * 5);
        //System.out.println(i);
        zm.setRow(i);
        zm.setLayoutX(position[i][0]);
        zm.setLayoutY(position[i][1]);
        rootPane.getChildren().add(zm);
    }

}
