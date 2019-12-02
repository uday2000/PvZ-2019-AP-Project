import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class PotatoMine extends Plants implements Runnable {
    private final static String pmhidden = ("assets/sprites/plants/PotatomineHidden.gif");
    private final static String pmarmed = ("assets/sprites/plants/PotatomineArmed.gif");
    private final static String pblasted = ("assets/sprites/plants/PotatomineExplode.gif");
    boolean armed = false;
    boolean eaten = false;
    PotatoMine(GridPane gridPane, Node node) {
        super(gridPane, node);
        this.setImage(new Image(getClass().getResourceAsStream(pmhidden)));
        this.setOpacity(1);
        this.type="POTATO";
        this.health = 300;
    }

    @Override
    public boolean attack(Zombie zm) throws KillPlantException, InterruptedException, KillZombieException {
        while (!armed && health>0){
            this.health = this.health - zm.getDamage();
            Thread.sleep(1000);
        }

        if (health>0) {
            health = 10000;
            this.setImage(new Image(getClass().getResourceAsStream("assets/sprites/plants/PotatomineExplode.gif")));
            ((ImageView) node).setImage(this.getImage());
            armed=false;
            eaten=true;
            zm.health=0;
            throw new KillZombieException("Mine out");
        }
        if (health<=0 && !eaten ) {
            this.setImage(new Image(getClass().getResourceAsStream("assets/sprites/plants/PotatomineEaten.gif")));
            ((ImageView) node).setImage(this.getImage());
            armed=false;
            throw new KillPlantException("Mine out");
        }
        else {
            throw new KillPlantException("Mine already eaten");
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(14000);
            if (health>0) {
                this.setImage(new Image(getClass().getResourceAsStream(pmarmed)));
                armed = true;
            }
            //LevelScene.img[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = "assets/sprites/plants/PotatoArmed.gif";
            ((ImageView)node).setImage(this.getImage());;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
