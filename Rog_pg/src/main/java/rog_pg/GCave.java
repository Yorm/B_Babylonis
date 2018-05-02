package rog_pg;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.*;

public class GCave extends GMap {
    private int Y;
    private int X;
    private int[][] map;
    private Random rand;

    GCave(int x, int y,int f){
        rand = new Random(System.currentTimeMillis());
        this.Y=y;
        this.X=x;
        this.map = new int[X][Y];
        mapFill(f);
    }
    public int[][] getMap() {
        return map;
    }
    public void mapFill(int f){
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++){
                map[i][j] = f;
            }
        }
    }
    //TODO
    public void simpleCave(int x,int y, int min,int max,int count){

        ArrayList<Point> path = new ArrayList<>();

        map[x][y] = 2;
        path.add(new Point(x,y));

        for (int i = 0; i < rand.nextInt(max - min) + min; i++) {
            switch (rand.nextInt(5 - 1) + 1) {
                case 1:
                    if (x < X - 2 ) {x++;}
                    else {i--;}
                    break;
                case 2:
                    if (x > 2  ) {x--;}
                    else {i--;}
                    break;
                case 3:
                    if (y < Y - 2 ) {y++;}
                    else {i--;}
                    break;
                case 4:
                    if (y > 2 ) {y--;}
                    else {i--;}
                    break;
            }
            map[x][y] = 2;
            path.add(new Point(x,y));
        }

        int start=rand.nextInt(path.size());
        if(count>0){
            count--;
            simpleCave(path.get(start).getX() , path.get(start).getY() ,min,max,count);
        }
    }
}
