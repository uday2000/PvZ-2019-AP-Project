import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PotatoMine implements Runnable
{
    private static String pmarmed = ("assets/sprites/plants/PotatomineArmed.gif");
    static String pmhidden = ("assets/sprites/plants/PotatomineHidden.gif");
    private Node node;


    PotatoMine( Node node) {
        this.node = node;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(20000);
            ((ImageView)node).setImage(new Image(getClass().getResourceAsStream(pmarmed)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
