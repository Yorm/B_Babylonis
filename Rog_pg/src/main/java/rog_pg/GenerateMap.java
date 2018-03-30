package rog_pg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import rog_pg.Point;
import rog_pg.PathFinder;

public class GenerateMap {
    // <editor-fold defaultstate="collapsed" desc="Fields">
    private final int Y;
    private final int X;
    private int[][] map;
    Random rand = new Random();
    
    ArrayList<Room> rooms;
    
    public GenerateMap(){
        rooms=new ArrayList<>();
        this.Y=40;
        this.X=40;
        this.map = new int[X][Y];
        mapFill();
    }
    public GenerateMap(int x, int y){
        rooms=new ArrayList<>();
        this.Y=y;
        this.X=x;
        this.map = new int[X][Y];
        mapFill();
    }
    // </editor-fold>   
    public final void mapFill(){
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++){
                map[i][j] = 1;   
            }
        }
      
    }
    void roomGen(){
        boolean in=false;
        for(int i=0;i<rand.nextInt(10-4)+4;i++){
            Room r = new Room();
            r.x=rand.nextInt(X-11)+2;
            r.y=rand.nextInt(Y-11)+2;
            r.h=(rand.nextInt(6)+3);
            r.w=(rand.nextInt(6)+3);
            for(Room room:rooms){
                if(r.intersect(room)){in=true;break;}
                else{in=false; }
            }
            if(!in){
                rooms.add(r);
                if(rooms.size()>=2){
                    corridorPrint(rooms.get(i-1),rooms.get(i));
                }    
            }else {i--;}
        }  
        
        roomPrint();
    }
    void roomPrint(){//TODO
        rooms.forEach((r) -> {
            for(int i=r.y;i<r.y+r.h;i++){
                for(int j=r.x;j<r.x+r.w;j++)
                   //if(i==r.y||j==r.x||i==(r.y+r.h-1)||j==(r.x+r.w-1)){
                        map[i][j]=999;
                    //}else{map[i][j]=1;}
            }
        });
        cleanRooms();
    }
    void cleanRooms(){
        for(int i=1;i<X-1;i++)
            for(int j=1;j<Y-1;j++){
                if(map[i][j]==1 && map[i+1][j]==999) map[i][j]=0;
                if(map[i][j]==1 && map[i-1][j]==999) map[i][j]=0; 
                if(map[i][j]==1 && map[i][j-1]==999) map[i][j]=0;  
                if(map[i][j]==1 && map[i][j+1]==999) map[i][j]=0;  
            }
        for(int i=0;i<X;i++)
            for(int j=0;j<Y;j++){
                if(map[i][j]==999) map[i][j]=2;  
            }
    }
    void corridorPrint(Room a, Room b){
        int[][] mapp = new int[X][Y];
        for (int i = 0; i < X; i++) 
            for (int j = 0; j < Y; j++)
                mapp[i][j] = 1;   

        PathFinder pf = new PathFinder(map,this.X,this.Y);
        Point[] path;

        path = pf.find(new Point(a.y+a.h/2,a.x+a.w/2),
                       new Point(b.y+b.h/2,b.x+b.w/2));  
        for (Point p:path) {
            map[p.getX()][p.getY()]=999;
        } 
    }
    public int[][] getMap() {
        return map;
    }
}
