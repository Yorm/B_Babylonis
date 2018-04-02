package rog_pg;

import rog_pg.GenerateMap;
/**
 *
 * @author walte
 */
/*
Bug report
-pathfinder.find dont work with map edges (0-39)
-pathfinder.find in end of path find process can make waste step
-gmap.cleanRooms dont paint the edges of the rooms at the edges of the map
-point.java need into room.java?
*/
public class Main {
    public static void main(String args[]){
        OutputMap out = new OutputMap();
        out.main();
    }
}
