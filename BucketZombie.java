import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class BucketZombie extends Zombie {

    public BucketZombie(Image zombieImage, GridPane gridPane, Plants[][] plants) {
        super(zombieImage, gridPane,plants);
        this.type = "BUCKET";
        this.health = 1000;
        this.damage = 100;
    }

    public BucketZombie clone(){
        return (BucketZombie)super.clone();
    }

    @Override
    public void changeImage() {
        this.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\zombies\\BucketZombieEating.gif")));
    }
}
