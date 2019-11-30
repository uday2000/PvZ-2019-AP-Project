import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Walnut extends Plants implements Runnable {

    private final static String walnut = ("assets/sprites/plants/WalnutIdle.gif");
    private final static String walnutHalf = ("assets/sprites/plants/WalnutHalfEaten.gif");
    private final static String walnutEaten = ("assets/sprites/plants/WalnutEaten.gif");

    public Walnut(GridPane gridPane, Node node) {
        super(gridPane, node);
        this.setImage(new Image(getClass().getResourceAsStream(walnut)));
        this.setOpacity(1);
        this.type = "WALNUT";
        this.health = 3600;
    }

    @Override
    public boolean attack(Zombie zm) {
        while (zm.health>0 && this.health>0){
            this.health = this.health - zm.getDamage();
            if (this.health<50){
                this.setImage(new Image(getClass().getResourceAsStream(walnutHalf)));
                ((ImageView)node).setImage(this.getImage());
            }
        }
        if (this.health<=0){
            this.setImage(new Image(getClass().getResourceAsStream(walnutEaten)));
            ((ImageView)node).setImage(this.getImage());
            return false;
        }
        return true;
    }

    @Override
    public void run() {

    }
}
