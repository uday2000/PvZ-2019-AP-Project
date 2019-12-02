import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class SlotScene {

    @FXML
    private ImageView slot1, slot2, slot3, slot4, backButtonImage;
    private Scene prevScene;
    private Scene nextScene;
    private MainScene MScont;
    private ArrayList<Player> players;

    private MusicController MC;
    public void setmc(MusicController mc) { MC = mc;}

    private int panewidth = 1408;
    private int paneheight = 896;

    private Image backPressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\BackButtonPressed.png"));
    private Image backReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\BackButtonReleased.png"));
    private Image newGamePressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\NewGamePressed.png"));
    private Image newGameReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\NewGameReleased.png"));
    private Image loadGamePressed = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\LoadGamePressed.png"));
    private Image loadGameReleased = new Image(getClass().getResourceAsStream("assets\\sprites\\buttons\\LoadGameReleased.png"));

    @FXML
    private void buttonPress(MouseEvent _mouseEvent) throws InterruptedException {
        ImageView btn = slot1;
        TextFlow tf = null;
        try {
            btn = (ImageView) _mouseEvent.getSource();
        }catch(ClassCastException e) {
            tf = (TextFlow) _mouseEvent.getSource();
            ImageView[] imgs = {slot1, slot2, slot3, slot4};
            for(ImageView iv : imgs) {
                if(tf.getLayoutY() == iv.getLayoutY()) {
                    btn = iv;
                    break;
                }
            }
        }
        if(imgcomp(btn.getImage(), newGameReleased))
            btn.setImage(newGamePressed);
        else {
            btn.setImage(loadGamePressed);
            Text t = (Text)tf.getChildren().get(0);
            t.setFill(Color.GRAY);
        }
    }
    @FXML
    private void buttonRelease(MouseEvent _mouseEvent) throws InterruptedException {
        ImageView btn = slot1;
        TextFlow tf = null;
        try {
            btn = (ImageView) _mouseEvent.getSource();
        }catch(ClassCastException e) {
            tf = (TextFlow) _mouseEvent.getSource();
            ImageView[] imgs = {slot1, slot2, slot3, slot4};
            for(ImageView iv : imgs) {
                if(tf.getLayoutY() == iv.getLayoutY()) {
                    btn = iv;
                    break;
                }
            }
        }
        if(imgcomp(btn.getImage(), newGamePressed))
            btn.setImage(newGameReleased);
        else {
            btn.setImage(loadGameReleased);
            Text t = (Text)tf.getChildren().get(0);
            t.setFill(Color.WHITE);
        }
    }
    @FXML
    private void backPress() throws InterruptedException {
        backButtonImage.setImage(backPressed);
    }
    @FXML
    private void backRelease() {
        backButtonImage.setImage(backReleased);
    }

    @FXML
    private void backScreen(MouseEvent _mouseEvent) {
        MC.playsfx("button");
        Stage _stage = (Stage)((Node)_mouseEvent.getSource()).getScene().getWindow();
        _stage.setScene(prevScene);
    }

    void setPrevScene(Scene _scene) {
        prevScene = _scene;
    }
    void setNextScene(Scene _scene) {
        nextScene = _scene;
    }

    @FXML
    private void playScreen(MouseEvent _mouseEvent) throws Exception{
        ImageView btnimg = slot1;
        try {
            btnimg = (ImageView) _mouseEvent.getSource();
        }catch(ClassCastException e) {
            TextFlow tf = (TextFlow) _mouseEvent.getSource();
            ImageView[] imgs = {slot1, slot2, slot3, slot4};
            for(ImageView iv : imgs) {
                if(tf.getLayoutY() == iv.getLayoutY()) {
                    btnimg = iv;
                    break;
                }
            }
        }
        MC.playsfx("button");
        if(!imgcomp(btnimg.getImage(), newGamePressed)) {
            String s = btnimg.getId();
            MScont.setPlayer(players.get(Integer.parseInt(s.substring(s.length()-1)) - 1));
            Stage _stage = (Stage) ((Node) _mouseEvent.getSource()).getScene().getWindow();
            _stage.setScene(nextScene);
        }
        else{
            FXMLLoader ngloader = new FXMLLoader(getClass().getResource("fxmls/NameEnterPanel.fxml"));
            Parent ngparent = ngloader.load();
            Scene ngscene = new Scene(ngparent, panewidth ,paneheight);
            Stage ngstage = new Stage();
            PopupController ngcont = ngloader.getController();

            ngcont.setPrevStageScene((Stage)((Node)_mouseEvent.getSource()).getScene().getWindow());
            ngcont.setnextsceneandcont(nextScene, MScont);
            ngcont.setcurscene(this);
            ngcont.setmc(MC);

            ngstage.setScene(ngscene);
            ngscene.setFill(Color.TRANSPARENT);
            ngstage.initStyle(StageStyle.TRANSPARENT);
            ngstage.show();
        }
    }

    private boolean imgcomp(Image i1, Image i2) {
        int w = (int) i1.getWidth(), h = (int) i1.getHeight();
        PixelReader r1 = i1.getPixelReader(), r2 = i2.getPixelReader();
        double nsp = IntStream.range(0, w).parallel().mapToLong(i -> IntStream.range(0, h).parallel().filter(j -> r1.getArgb(i, j) != r2.getArgb(i, j)).count()).sum();
        if(nsp > 0)
            return false;
        return true;
    }
    public void setSlots(ArrayList<Player> p) {
        players = p;
        refresh();
    }
    private void refresh() {
        ImageView[] slotsarr = {slot1, slot2, slot3, slot4};
        for(int i=0; i<4; i++) {
            if(i < players.size()){
                slotsarr[i].setImage(loadGameReleased);
                Text name = new Text();
                name.setText(players.get(i).getP_name());
                name.setFont(new Font("SF Pixelate", 75));
                name.setFill(Color.WHITE);
                TextFlow TF = new TextFlow(name);
                TF.setPrefWidth(704);
                TF.setPrefHeight(128);
                TF.setLayoutX(352);
                TF.setLayoutY(192 + (i*160));
                TF.setTextAlignment(TextAlignment.CENTER);
                name.setTranslateY(32);
                TF.setOnMouseClicked(slotsarr[i].getOnMouseClicked());
                TF.setOnMouseEntered(slotsarr[i].getOnMouseEntered());
                TF.setOnMouseExited(slotsarr[i].getOnMouseExited());
                Pane curpane = (Pane)slot1.getParent();
                curpane.getChildren().add(TF);
            }
            else{
                slotsarr[i].setImage(newGameReleased);
            }
        }
    }
    public void setmaincont(MainScene ms){
        MScont = ms;
    }
    public void addslot(Player p) {
        players.add(p);
        refresh();
    }
}