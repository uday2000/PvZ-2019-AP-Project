import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private int panewidth = 1408;
    private int paneheight = 896;
    
    @Override
    public void start(Stage TitleStage) throws Exception{
        FXMLLoader Titleloader = new FXMLLoader(getClass().getResource("fxmls/TitleScreen.fxml"));
        Parent titleparent = Titleloader.load();
        Scene titlescene = new Scene(titleparent, panewidth, paneheight);
        TitleScene titlecontroller = Titleloader.getController();

        FXMLLoader Slotloader = new FXMLLoader(getClass().getResource("fxmls/GameSelect.fxml"));
        Parent gameselectparent = Slotloader.load();
        Scene gameselectscene = new Scene(gameselectparent, panewidth ,paneheight);
        SlotScene gameselectcontroller = Slotloader.getController();

        FXMLLoader Playerloader = new FXMLLoader(getClass().getResource("fxmls/MainScreen.fxml"));
        Parent playermainparent = Playerloader.load();
        Scene playermainscene = new Scene(playermainparent, panewidth ,paneheight);
        MainScene playermaincontroller = Playerloader.getController();

        /*FXMLLoader LevelLoader = new FXMLLoader(getClass().getResource("fxmls/LevelScreen.fxml"));
        Parent levelParent = LevelLoader.load();
        Scene levelScene = new Scene(levelParent, 352, 224);
        LevelScene levelsceneController =  LevelLoader.getController();*/

        titlecontroller.setNextScene(gameselectscene);
        gameselectcontroller.setPrevScene(titlescene);
        gameselectcontroller.setNextScene(playermainscene);
        playermaincontroller.setPrevScene(gameselectscene);
        //playermaincontroller.setPlayScene(levelScene);
        //levelsceneController.setPrevScene(playermainscene);

        TitleStage.getIcons().add(new Image(getClass().getResourceAsStream("assets/sprites/PVZLogo.png")));
        TitleStage.setTitle("Plants V/S Zombies: RETRO");
        TitleStage.setScene(titlescene);
        TitleStage.initStyle(StageStyle.UNDECORATED);
        TitleStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
