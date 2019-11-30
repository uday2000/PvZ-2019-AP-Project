import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Sunflower extends Plants implements Runnable
{
    private final static String sunIdle = ("assets/sprites/plants/SunflowerIdle.gif");
    private final static String sunToken = ("assets/sprites/plants/SunflowerShoot.gif");
    private final static String sunEaten = ("assets/sprites/plants/SunflowerEaten.gif");

    Sunflower(GridPane gridPane, Node node) {
        super(gridPane,node);
        this.setImage(new Image(getClass().getResourceAsStream(sunIdle)));
        this.setOpacity(1);
        this.type="SUN";
        this.health = 250;
    }

    @Override
    public boolean attack(Zombie zm) {
        while (zm.health>0 && this.health>0){
            this.health = this.health - zm.getDamage();
        }
        if (this.health<=0){
            this.setImage(new Image(getClass().getResourceAsStream(sunEaten)));
            ((ImageView)node).setImage(this.getImage());
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (LevelScene.img[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)]!=null || !(LevelScene.img[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)].equals("assets/sprites/plants/SunflowerShoot.gif"))) {
                    Thread.sleep(20000);
                    this.setImage(new Image(getClass().getResourceAsStream(sunToken)));
                    LevelScene.img[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = "assets/sprites/plants/SunflowerShoot.gif";
                    ((ImageView)node).setImage(this.getImage());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
