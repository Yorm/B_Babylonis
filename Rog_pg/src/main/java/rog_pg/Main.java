package rog_pg;

import rog_pg.GenerateMap;
/**
 *
 * @author walte
 */
public class Main {
    public static void main(String args[]){
        GenerateMap map = new GenerateMap();
        //map.veldGen();
        map.roomGen();
        map.mapPrint();
    }
}
