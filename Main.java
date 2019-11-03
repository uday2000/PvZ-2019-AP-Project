//package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage TitleStage) throws Exception{
        FXMLLoader Titleloader = new FXMLLoader(getClass().getResource("FXMLS/TitleScreen.fxml"));
        Parent titleparent = Titleloader.load();
        Scene titlescene = new Scene(titleparent, 352, 224);
        TitleScene titlecontroller = (TitleScene)Titleloader.getController();

        FXMLLoader Slotloader = new FXMLLoader(getClass().getResource("FXMLS/GameSelect.fxml"));
        Parent gameselectparent = Slotloader.load();
        Scene gameselectscene = new Scene(gameselectparent, 352 ,224);
        SlotScene gameselectcontroller = (SlotScene)Slotloader.getController();

        FXMLLoader Playerloader = new FXMLLoader(getClass().getResource("FXMLS/MainScreen.fxml"));
        Parent playermainparent = Playerloader.load();
        Scene playermainscene = new Scene(playermainparent, 352 ,224);
        MainScene playermaincontroller = (MainScene)Playerloader.getController();

        FXMLLoader Levelloader = new FXMLLoader(getClass().getResource("FXMLS/LevelScreen.fxml"));
        Parent levelparent = Levelloader.load();
        Scene levelscene = new Scene(levelparent, 352 ,224);
        LevelScene levelcontroller = (LevelScene)Levelloader.getController();

        titlecontroller.setNextScene(gameselectscene);
        gameselectcontroller.setPrevScene(titlescene);
        gameselectcontroller.setPlayerScene(playermainscene);
        playermaincontroller.setPrevScene(gameselectscene);
        playermaincontroller.setPlayScene(levelscene);
        levelcontroller.setPrevScene(playermainscene);

        TitleStage.setTitle("Retro Plants V/S Zombies");
        TitleStage.setScene(titlescene);
        TitleStage.initStyle(StageStyle.UNDECORATED);

        TitleStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
