package rog_pg;

import java.util.Random;

public class GForest extends GMap {
    private int Y;
    private int X;
    private int[][] map;
    private Random rand;

    GForest(int x, int y,int f){
        rand = new Random(System.currentTimeMillis());
        this.Y=y;
        this.X=x;
        this.map = new int[X][Y];
        mapFill(f);
    }

    public void mapFill(int f){
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++){
                map[i][j] = f;
            }
        }
    }

    //TODO
    public void simpleForest(int min,int max){
        int x,y;
        for(int i=0;i<rand.nextInt(max-min)+min;i++){
            x=rand.nextInt(X-1)+1;
            y=rand.nextInt(Y-1)+1;
            if(map[x][y]!=3){ map[x][y]=3; }
            else i--;
        }
    }

    public int[][] getMap() {
        return map;
    }
}
