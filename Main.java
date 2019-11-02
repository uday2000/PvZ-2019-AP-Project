package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage1) throws Exception{
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Scene1.fxml"));
        Parent scene1Parent = loader1.load();
        Scene scene1 = new Scene(scene1Parent,704,448);
        Scene1 controller1 = (Scene1)loader1.getController();


        FXMLLoader loader4 = new FXMLLoader(getClass().getResource("Scene4.fxml"));
        Parent scene4Parent = loader4.load();
        Scene scene4 = new Scene(scene4Parent,704,448);
        Scene4 controller4 = (Scene4)loader4.getController();

        controller1.setNextScene(scene4);
        controller4.setPrevScene(scene1);


        stage1.setTitle("Plants vs Zombies DU Version");
        stage1.setScene(scene1);
        stage1.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
