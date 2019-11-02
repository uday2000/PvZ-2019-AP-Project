package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Scene4 {

    private Scene prevScene;

    @FXML
    private void testBackScreen(MouseEvent mouseEvent) {
        Stage stage4 = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage4.setScene(prevScene);
    }


    void setPrevScene(Scene scene1) {
        prevScene = scene1;
    }

}
