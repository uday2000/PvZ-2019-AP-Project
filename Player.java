import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Player implements Serializable {
    private String p_name;
    private int curlvl;
    private int unlockedlvls;
    private ArrayList<LevelScene> lvldata;

    Player(String s) {
        this.p_name = s;
        this.curlvl = 1;
        this.unlockedlvls = 1;
        this.lvldata = new ArrayList<LevelScene>(Arrays.asList(null,null,null,null,null));
    }
    Player(String s, int cl, int ul) {
        this.p_name = s;
        this.curlvl = cl;
        this.unlockedlvls = ul;
        this.lvldata = new ArrayList<LevelScene>(Arrays.asList(null,null,null,null,null));
    }

    public String getP_name() {
        return p_name;
    }
    public int getCurlvl() {
        return curlvl;
    }
    public int getUnlockedlvls(){
        return unlockedlvls;
    }
    public void setlevel(int lvlnum, LevelScene LS) {
        if(lvldata.get(lvlnum - 1) == null) {
            lvldata.set(lvlnum - 1, LS);
        }
    }
}
