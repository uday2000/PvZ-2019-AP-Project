import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Vector;

public class ZombieController extends Thread {

    private final static int[] levelZombie = {5, 10, 15, 20, 25};
    private static int[][] position = {{1306, 136}, {1306, 264}, {1306, 405}, {1306, 520}, {1306, 648}};
    Vector<Zombie> zombies = new Vector<>();
    private Vector<Zombie> zombieRunning = new Vector<>();
    private int zombieLeft;
    private int level;
    private GridPane gridPane;
    private Pane rootPane;
    private Image bucketZombie = new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\BucketZombieWalking.gif"));
    private Image coneZombie = new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\ConeZombieWalking.gif"));
    private Image normalZombie = new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\ZombieWalking.gif"));
    private Plants[][] plants;
    private GridPane lawnMower;
    boolean[] lawnmowerUsed = new boolean[5];


    ZombieController(Pane rootPane, GridPane gridPane, int level, Plants[][] data, GridPane lawnmower) {
        this.gridPane = gridPane;
        this.rootPane = rootPane;
        this.level = level;
        zombieLeft = levelZombie[level];
        plants = data;
        this.lawnMower = lawnmower;
        populateZombie();
        for (int i = 0; i <5 ; i++) {
            lawnmowerUsed[i]=false;
        }
    }

    private void populateZombie() {
        for (int i = 0; i < levelZombie[level]; i++) {
            Zombie zm = randomZombie();
            zm.setId(levelZombie[level]-zombieLeft+1);
            zombieLeft--;
            zombies.add(zm);
            showZombie(zm);
        }
    }

    private Zombie randomZombie() {
        int j = (int) (Math.random() * 3);
        int i = (int) (Math.random() * 5);
        switch (j) {
            case 0:
                return new BucketZombie(bucketZombie, gridPane, plants, rootPane, position[i],lawnMower,this);
            case 1:
                return new ConeZombie(coneZombie, gridPane, plants, rootPane, position[i],lawnMower,this);
            case 2:
                return new NormalZombie(normalZombie, gridPane, plants, rootPane, position[i],lawnMower,this);
            default:
                return new NormalZombie(normalZombie, gridPane, plants, rootPane, position[i],lawnMower,this);
        }
    }


    @Override
    public void run() {
        int i = 0;
        for (Zombie zm : zombies) {
            Timeline timeline = new Timeline(
                    new KeyFrame(
                            Duration.seconds(20 + (i * 10)),
                            event -> {
                                Thread thread = new Thread(zm);
                                thread.start();
                            }
                    )
            );
            timeline.setCycleCount(1);
            timeline.play();
            i = i + 1;
        }
        for (Zombie zm : zombies){
            while (zm.alive){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //System.out.println("Level Won");
    }

    private void showZombie(Zombie zm) {
        int i = (int) (Math.random() * 5);
        zm.setRow(i);
        zm.setLayoutX(position[i][0]);
        zm.setLayoutY(position[i][1]);
        zm.setOpacity(0);
        rootPane.getChildren().add(zm);
    }


}
