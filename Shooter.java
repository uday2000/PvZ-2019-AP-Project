import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Shooter extends Plants implements Runnable{


    private final static String shooterIdle = ("assets/sprites/plants/PeashooterIdle.gif");
    private final static String shooterShoot = ("assets/sprites/plants/PeashooterShoot.gif");
    private final static String shooterEaten = ("assets/sprites/plants/PeashooterEaten.gif");

    private static String pea = ("assets/sprites/misc/BulletPea.png");
    private static int gridSize = 128;
    private Pane pane;


    Shooter(GridPane gridPane, Node node, Pane pane) {
        super(gridPane,node);
        this.setImage(new Image(getClass().getResourceAsStream(shooterIdle)));
        this.setOpacity(1);
        this.type="SHOOTER";
        this.health = 300;
        this.pane = pane;
    }

    @Override
    public void run() {

    }

    @Override
    public boolean attack(Zombie zm) throws InterruptedException, KillPlantException, KillZombieException {
        while (zm.health>0 && this.health>0){
            this.health = this.health-zm.getDamage();
            Thread.sleep(1000);
        }
        if (this.health<=0){
            this.setImage(new Image(getClass().getResourceAsStream(shooterEaten)));
            ((ImageView)node).setImage(this.getImage());
            throw new KillPlantException("Shooter eaten");
        }
        if (zm.health<=0){
            throw new KillZombieException("Shot Down");
        }
        return true;
    }
}
