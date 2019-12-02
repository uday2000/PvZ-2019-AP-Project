import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class NormalZombie extends Zombie {
    public NormalZombie(Image zombieImage, GridPane gridPane, Plants[][] plants, Pane pane, int[] position, GridPane lawnMower, ZombieController zombieController) {
        super(zombieImage, gridPane, plants, pane,lawnMower,zombieController);
        this.type = "NORMAL";
        this.health = 200;
        this.setLayoutX(position[0]);
        this.setLayoutY(position[1]);
        this.damage = 80;
    }


    @Override
    public void changeImage(int opt) {
        if (opt==1) {
            this.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\ZombieEating.gif")));
        }
        else {
            this.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\ZombieWalking.gif")));
        }
    }
}
