import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ConeZombie extends Zombie {
    public ConeZombie(Image zombieImage, GridPane gridPane, Plants[][] plants, Pane pane, int[] position, GridPane lawnMower, ZombieController zombieController) {
        super(zombieImage, gridPane,plants,pane, lawnMower,zombieController);
        this.type = "CONE";
        this.health = 500;
        this.setLayoutX(position[0]);
        this.setLayoutY(position[1]);
        this.damage = 90;
    }


    @Override
    public void changeImage(int opt) {
        if (opt==1) {
            this.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\ConeZombieEating.gif")));
        }
        else {
            this.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\ConeZombieWalking.gif")));
        }
    }
}
