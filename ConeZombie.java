import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class ConeZombie extends Zombie {
    public ConeZombie(Image zombieImage, GridPane gridPane, Plants[][] plants) {
        super(zombieImage, gridPane,plants);
        this.type = "CONE";
        this.health = 500;
        this.damage = 90;
    }

    public ConeZombie clone(){
        ConeZombie czm = (ConeZombie)super.clone();
        return czm;
    }

    @Override
    public void changeImage() {
        this.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\ConeZombieEating.gif")));
    }
}
