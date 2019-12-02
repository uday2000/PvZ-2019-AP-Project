import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import javafx.util.Duration;


abstract class Zombie extends Character implements Runnable {

    String type;
    int health;
    int row;
    Plants[][] plants;
    Pane pane;
    Timeline move;
    int id;
    int damage;
    private GridPane gridPane;
    boolean alive = false;
    GridPane lawnMower;
    ZombieController zombieController;
    Zombie(Image zombieImage, GridPane gridPane, Plants[][] plants, Pane pane,GridPane lawmower, ZombieController zc) {
        this.setImage(zombieImage);
        this.gridPane = gridPane;
        this.setFitHeight(180);
        this.setFitWidth(160);
        this.plants = plants;
        this.pane = pane;
        this.lawnMower=lawmower;
        zombieController=zc;
    }

    void setId(int id) {
        this.id = id;
    }

    public void setRow(int row) {
        this.row = row;

    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void run() {
        setOpacity(1);
        alive=true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        KeyValue xValue = new KeyValue(this.translateXProperty(), -1400);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100000), xValue);
        move = new Timeline();
        move.setCycleCount(1);
        move.getKeyFrames().addAll(keyFrame);
        move.play();
        while (alive) {
            try {
                    checkColliosion();
            } catch (InterruptedException | KillPlantException | KillZombieException e) {
                e.getLocalizedMessage();
            }
        }
    }

    private void checkColliosion() throws InterruptedException, KillZombieException, KillPlantException {
        for (Plants plants : plants[row]) {
            if (plants != null) {
                if (plants.type.equals("SHOOTER")) {
                    this.health = this.health - 20;
                    //System.out.println(health);
                }
                Bounds grid = plants.node.getBoundsInParent();
                double xMax = grid.getMaxX() + 192;
                double xMin = grid.getMinX() + 192;
                if (xMax - this.getBoundsInParent().getMinX() > 0 && xMin - this.getBoundsInParent().getMaxX() < 0) {
                    move.pause();
                    changeImage(1);
                    try {
                        handlePlants(plants);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (KillPlantException e) {
                        for (int i = 0; i < 9; i++) {
                            if (this.plants[row][i] != null && this.plants[row][i].equals(plants)) {
                                this.plants[row][i] = null;
                                plants.exit=true;
                                break;
                            }
                        }
                        changeImage(0);
                        move.play();
                        e.printStackTrace();
                    } catch (KillZombieException e) {
                        this.setImage(null);
                        alive=false;
                    }
                }
            }
        }
        Thread.sleep(1000);
        if (this.getBoundsInParent().getMinX()-lawnMower.getBoundsInParent().getMaxX()<0 && !zombieController.lawnmowerUsed[this.row]){
            KeyValue xValue = new KeyValue(lawnMower.getChildren().get(row).translateXProperty(), 10000);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(20000), xValue);
            Timeline timeline = new Timeline();
           timeline.setCycleCount(1);
            timeline.getKeyFrames().addAll(keyFrame);
            timeline.play();
            for (Zombie zm : zombieController.zombies ){
                if (lawnMower.getBoundsInParent().getMaxX()-zm.getBoundsInParent().getMinX()>0){
                    zm.setImage(null);
                    zm.alive=false;
                }
            }
        }

    }

    private void handlePlants(Plants plant) throws InterruptedException, KillPlantException, KillZombieException {
        plant.attack(this);

    }

    public abstract void changeImage(int opt);


}