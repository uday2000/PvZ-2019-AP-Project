import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BucketZombie extends Zombie {

    public BucketZombie(Image zombieImage, GridPane gridPane, Plants[][] plants, Pane pane, int[] position, GridPane lawnMower,ZombieController zombieController) {
        super(zombieImage, gridPane,plants,pane,lawnMower,zombieController);
        this.type = "BUCKET";
        this.health = 1000;
        this.setLayoutX(position[0]);
        this.setLayoutY(position[1]);
        this.damage = 100;
    }


    @Override
    public void changeImage(int opt) {
        if (opt==1) {
            this.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\BucketZombieEating.gif")));
        }
        else {
            this.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\BucketZombieWalking.gif")));
        }
    }
}
