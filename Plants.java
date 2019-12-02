import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public abstract class Plants extends Character implements Runnable {

    String type;
    volatile int health;
    GridPane gridPane;
    Node node;
    boolean exit=false;

    public Plants(GridPane gridPane, Node node) {
        this.gridPane = gridPane;
        this.node = node;
    }

    public abstract boolean attack(Zombie zm) throws InterruptedException, KillPlantException, KillZombieException;
}
