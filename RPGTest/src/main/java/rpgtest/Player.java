package rpgtest;

import java.util.ArrayList;

/**
 *
 * @author walte
 */
public class Player extends NPC{
    
    public Player(){
        this.mind = new ArrayList<>();
        this.exp=0;
        this.hp=1000;
        this.lvl=1;
        this.attack=250;
    }  
    
    private void mindAdd(){
        this.mind.add("");//0
    }
    
}
