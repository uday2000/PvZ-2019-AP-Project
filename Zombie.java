import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;


abstract class Zombie extends Character implements Cloneable, Runnable {

    String type;
    int health, damage;
    private GridPane gridPane;
    int row;
    Plants[][] plants;
    TranslateTransition move;

    Zombie(Image zombieImage, GridPane gridPane, Plants[][] plants){
        this.setImage(zombieImage);
        this.gridPane = gridPane;
        this.setFitHeight(180);
        this.setFitWidth(160);
        this.plants = plants;
        this.damage = 80;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Zombie clone(){
        try {
            Zombie zm = (Zombie) super.clone();
            return zm;
        }
        catch (CloneNotSupportedException e){
            e.getMessage();
            return null;
        }
    }

    @Override
    public void run(){
        move = new TranslateTransition();
        move.setNode(this);
        int panewidth = 1408;
        move.setByX((-1 * panewidth) - 2000);
        int movespeed = 200;
        move.setDuration(Duration.seconds(movespeed));
        move.play();
        while (true){
           try {
                checkCollision();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkCollision() throws InterruptedException {
        for (Plants plants : plants[row]){
            if (plants!=null) {
                Bounds grid = plants.node.getBoundsInParent();
                double x = grid.getMaxX()+192;
                if (x - this.getBoundsInParent().getMinX()>0) {
                    System.out.println("Hello");
                    move.pause();
                    changeImage();
                    boolean b = handlePlants(plants);
                    if (b){
                        move.play();
                    }
                }
            }
        }
        Thread.sleep(1000);
    }

    private boolean handlePlants(Plants plants) {
        return plants.attack(this);
    }

    public abstract void changeImage();

    public int getDamage() {
        return this.damage;
    }
}