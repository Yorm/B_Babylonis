package rog_pg;

import java.util.ArrayList;
import java.util.Random;

public abstract class GMap {
    private int Y;
    private int X;
    private int[][] map;
    private Random rand;

    public void mapFill(int f){}
    public int[][] getMap() {
        return map;
    }
    public void simpleCave(int x,int y, int min,int max,int count){}
    public void simpleForest(int min,int max){}
    public void simpleLabyrinth(int min,int max){}
}
