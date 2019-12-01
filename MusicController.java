import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

public class MusicController {
    private boolean BGMMusic;
    private boolean SFXMusic;

    private ArrayList<MediaPlayer> allmusic;

    MusicController()
    {
        BGMMusic = true;
        SFXMusic = true;
        allmusic = new ArrayList<MediaPlayer>();
    }

    public boolean getbgm() {
        return BGMMusic;
    }
    public boolean getsfx() {
        return SFXMusic;
    }
    public void setbgm(boolean b) {
        BGMMusic = b;
    }
    public void setsfx(boolean b) {
        SFXMusic = b;
    }
    public void playMusic(String s) {
        if(BGMMusic) {
            if(s.equals("bgm")) {
                Media music = new Media(new File("assets/Audio/BGM.mp3").toURI().toString());
                MediaPlayer musicplay = new MediaPlayer(music);
                musicplay.setCycleCount(MediaPlayer.INDEFINITE);
                musicplay.setVolume(0.5);
                musicplay.play();
                allmusic.add(musicplay);
            }
        }
    }
    public void playsfx(String sfx) {
        if(SFXMusic){
            if(sfx.equals("button")) {
                Media buttonclick = new Media(new File("assets/Audio/ButtonClick.aiff").toURI().toString());
                MediaPlayer buttonplay = new MediaPlayer(buttonclick);
                buttonplay.play();
            }
        }
    }
    public void stopAll() {
        for(MediaPlayer mp : allmusic){
            mp.stop();
        }
        allmusic.clear();
    }
}
