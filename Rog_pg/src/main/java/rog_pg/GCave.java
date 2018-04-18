package rog_pg;

import java.util.ArrayList;
import java.util.Random;

public class GCave extends GMap {
    private int Y;
    private int X;
    private int[][] map;
    private Random rand;

    public GCave(int x, int y,int f){
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

        int f;
        ArrayList<Point> path = new ArrayList<>();

        map[x][y] = 2;
        path.add(new Point(x,y));

        for (int i = 0; i < rand.nextInt(max - min) + min; i++) {
            f = rand.nextInt(5 - 1) + 1;
            switch (f) {
                case 1:
                    if (x < X - 2 ) x++;
                    else i--;
                    break;
                case 2:
                    if (x > 2  ) x--;
                    else i--;
                    break;
                case 3:
                    if (y < Y - 2 ) y++;
                    else i--;
                    break;
                case 4:
                    if (y > 2 ) y--;
                    else i--;
                    break;
            }
            path.add(new Point(x,y));
            map[x][y] = 2;
        }

        Point start = path.get(rand.nextInt(path.size()));
        x=start.getX();
        y=start.getY();
        System.out.println("x y "+x+" "+y);
        if(count>0){
            count--;
            simpleCave(x,y,min,max,count);
        }

        /*for(int ix=1;ix<X-1;ix++){
            for(int jy=1;jy<Y-1;jy++)
                if(map[ix][jy]==0&&map[ix+1][jy]==2&&map[ix-1][jy]==2&&map[ix][jy+1]==2&&map[ix][jy-1]==2)
                    map[ix][jy]=2;
        }*/
    }
}
