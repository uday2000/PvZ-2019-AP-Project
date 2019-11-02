//package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

        titlecontroller.setNextScene(gameselectscene);
        gameselectcontroller.setPrevScene(titlescene);

        TitleStage.setTitle("Retro Plants V/S Zombies");
        TitleStage.setScene(titlescene);
        TitleStage.show();
    }


    public static void main(String[] args){
        launch(args);
    }
}
