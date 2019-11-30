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

    private MusicController MC;
    public void setmc(MusicController mc) { MC = mc;}

    PotatoMine(GridPane gridPane, Node node) {
        super(gridPane, node);
        this.setImage(new Image(getClass().getResourceAsStream(pmhidden)));
        this.setOpacity(1);
        this.type="POTATO";
        this.health = 250;
    }

    @Override
    public boolean attack(Zombie zm) {
        while (!armed && health>0){
            this.health = this.health - zm.getDamage();
        }
        if (health>0) {
            health = 100000;
            this.setImage(new Image(getClass().getResourceAsStream("assets/sprites/plants/PotatomineExplode.gif")));
            ((ImageView) node).setImage(this.getImage());
            Pane parPane = (Pane) gridPane.getParent();
            armed = false;
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(14000);
            this.setImage(new Image(getClass().getResourceAsStream(pmarmed)));
            armed=true;
            ((ImageView)node).setImage(this.getImage());;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
