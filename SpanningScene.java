import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SpanningScene
{
    private int panewidth = 1408;
    private int paneheight = 896;
    private int curlevel;
    private Stage curstage;
    private boolean play = false;
    @FXML
    private ImageView spanningImage;

    private MusicController MC;
    public void setmc(MusicController mc) { MC = mc;}

    @FXML
    public void initialize() {
        System.gc();
        Timeline tl = new Timeline(
                new KeyFrame(Duration.millis(12000l),
                        ae->{try{
                    playScene();
                }catch(Exception e) {
                    e.printStackTrace();
                }}));
        tl.play();
    }

    private void playScene() throws Exception{
        FXMLLoader LevelLoader = new FXMLLoader(getClass().getResource("fxmls/LevelScreen.fxml"));
        Parent levelParent = LevelLoader.load();
        Scene levelScene = new Scene(levelParent, panewidth, paneheight);
        LevelScene levelsceneController =  LevelLoader.getController();
        levelsceneController.setlevel(curlevel);

        Stage nplaystage = new Stage();
        nplaystage.initStyle(StageStyle.UNDECORATED);
        nplaystage.setScene(levelScene);
        nplaystage.show();
        System.gc();
        Timeline tl = new Timeline(
                new KeyFrame(Duration.millis(2000l),
                        ae->{curstage.close();}));
        tl.play();
    }

    void setlvl(int lvl){
        switch (lvl) {
            case 1:
                spanningImage.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\Spanning_animations\\LevelSpanning1.gif")));
                break;
            case 2:
                spanningImage.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\Spanning_animations\\LevelSpanning2.gif")));
                break;
            case 3:
                spanningImage.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\Spanning_animations\\LevelSpanning3.gif")));
                break;
            case 4:
                spanningImage.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\Spanning_animations\\LevelSpanning4.gif")));
                break;
            case 5:
                spanningImage.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\Spanning_animations\\LevelSpanning5.gif")));
                break;
            default:
                spanningImage.setImage(new Image(getClass().getResourceAsStream("assets\\sprites\\Spanning_animations\\LevelSpanning1.gif")));
        }
        System.gc();
    }
    public void setstage(Stage _stage, Scene _scene) {
        curstage = _stage;
    }
    public void setCurlevel(int curlvl){curlevel = curlvl;}
}
