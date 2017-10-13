package enemies;

import rpgtest.NPC;
/**
 *
 * @author walte
 */
public class Worm extends NPC{
    public Worm(){
        this.exp=1200;//количество опыта за убийство равно диапазону от hp до exp
        this.hp=500;
        this.lvl=1;
        this.attack=350;
    }
}
