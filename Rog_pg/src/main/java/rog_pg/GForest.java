package rog_pg;

import java.util.Random;
import java.util.ArrayList;

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
    //todo bad coda
    //
    public void griver(int height){
        ArrayList<Point> riverPath = new ArrayList<>();
        int x,y;
        boolean xORy = true;
        switch(rand.nextInt(2)){
            case 0: xORy=true; break;
            case 1: xORy=false; break;
        }

        if(xORy){
            x=rand.nextInt(X-1);
            y=0;
        }else{
            y=rand.nextInt(Y-1);
            x=0;
        }

        riverPath.add(new Point(x,y));

        if(xORy){
            for(int i=0; i<Y;i++){
                switch(rand.nextInt(2)){
                    case 0: if(X>x)x--;else i--;break;
                    case 1: if(x<X-1)x++;else i--; break;
                }
                for(int j=i-height;j<i+height;j++){
                    riverPath.add(new Point(x,j));
                }
            }
        }
        else{
            for(int i=0; i<X;i++){
                switch(rand.nextInt(2)){
                    case 0: if(Y>y)y--;else i--;break;
                    case 1: if(y<Y-1)y++;else i--; break;
                }
                for(int j=i-height;j<i+height;j++) {
                    riverPath.add(new Point(j, y));
                }
            }
        }

        for(Point p:riverPath) {
            map[p.getX()][p.getY()] = 0;
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
        griver(2);

    }

    public int[][] getMap() {
        return map;
    }
}
