import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class NormalZombie extends Zombie {
    public NormalZombie(Image zombieImage, GridPane gridPane, Plants[][] plants) {
        super(zombieImage, gridPane, plants);
        this.type = "NORMAL";
        this.health = 200;
    }

    public NormalZombie clone(){
        return (NormalZombie)super.clone();
    }

    @Override
    public void changeImage() {
        this.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\ZombieEating.gif")));
    }
}
