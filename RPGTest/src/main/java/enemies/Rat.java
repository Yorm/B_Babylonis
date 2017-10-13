
package enemies;

import rpgtest.NPC;


public class Rat extends NPC{
    public Rat(){
        this.exp=900;//количество опыта за убийство равно диапазону от hp до exp
        this.hp=300;
        this.lvl=1;
        this.attack=200;
    }
}
