import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shooter implements Runnable
{
    private static String shooterIdle = ("assets/sprites/plants/PeashooterIdle.gif");
    private static String shooterShoot = ("assets/sprites/plants/PeashooterShoot.gif");
    private static String pea = ("assets/sprites/misc/BulletPea.png");
    private Node node;


    Shooter( Node node) {
        this.node = node;
    }

    @Override
    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5)));
        double dx = 5;
    }
}
