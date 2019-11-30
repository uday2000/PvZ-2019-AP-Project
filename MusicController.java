import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public class MusicController {
    private boolean BGMMusic;
    private boolean SFXMusic;

    private ArrayList<MediaPlayer> allmedias;

    MusicController()
    {
        BGMMusic = true;
        SFXMusic = true;
        allmedias = new ArrayList<MediaPlayer>();
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
    public void playAudio(Media _media, boolean loop) {
        MediaPlayer mp = new MediaPlayer(_media);
        mp.play();
        if(loop) {
            mp.setCycleCount(MediaPlayer.INDEFINITE);
            allmedias.add(mp);
        }
    }
    public void stopAll() {
        for(MediaPlayer mp : allmedias){
            mp.stop();
            allmedias.remove(mp);
        }
    }
}
