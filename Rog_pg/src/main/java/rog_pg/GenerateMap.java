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
    public void mapPrint(){
        char map[][]=new char[X][Y]; 
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++){
                switch (this.map[i][j]){
                    case 0:System.out.print("#");break;
                    case 1:System.out.print(".");break;
                    case 2:System.out.print("_");break;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    void roomGen(){
        boolean in=false;
        for(int i=0;i<rand.nextInt(30-10)+10;i++){
            Room r = new Room();
            r.x=rand.nextInt(X-11)+1;
            r.y=rand.nextInt(Y-11)+1;
            r.h=(rand.nextInt(6)+4);
            r.w=(rand.nextInt(6)+4);
            for(Room room:rooms){
                if(r.intersect(room)){in=true;break;}
                else{in=false; }
            }
            if(!in){rooms.add(r);}    
        }        
        corridorPrint();
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
    void corridorPrint(){
        int[][] mapp = new int[X][Y];
        for (int i = 0; i < X; i++) 
            for (int j = 0; j < Y; j++)
                mapp[i][j] = 1;   

        PathFinder pf = new PathFinder(mapp,this.X,this.Y);
        Point[] path;
        for (int i=0;i<rooms.size()-1;i++) {
            path = pf.find(new Point(rooms.get(i).y+rooms.get(i).h/2,rooms.get(i).x+rooms.get(i).w/2),
                           new Point(rooms.get(i+1).y+rooms.get(i+1).h/2,rooms.get(i+1).x+rooms.get(i+1).w/2));  
            for (Point p:path) {
                map[p.getX()][p.getY()]=999;
            }
        }
    }  
    public int[][] getMap() {
        return map;
    }
}
