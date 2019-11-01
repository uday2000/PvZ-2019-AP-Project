package sample;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {

    private ImageView playButton = new ImageView();

    private Image playPressed = new Image("file:sprites\\buttons\\playbutton pressed.png");
    private Image playReleased = new Image("file:sprites\\buttons\\exitbutton.png");

    @FXML
    private void exitAction() {
        System.out.println(0);
        System.exit(0);
    }
    @FXML
    private void playAction()
    {
        System.out.println("hello");
        playButton.setImage(playPressed);
        System.out.println("helloagain");
    }

    @FXML
    private void playActionRelease()
    {
        playButton.setImage(playReleased);
    }



 }