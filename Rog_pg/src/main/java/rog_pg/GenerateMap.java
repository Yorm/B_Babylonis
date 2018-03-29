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
                }
                System.out.print(" ");
                //if(i==0||i==(X-1))System.out.print("-");
                //else if(j==0||j==(Y-1))System.out.print("|"); 
                //  map[0][Y-1]=map[X-1][0]=map[0][0]=map[X-1][Y-1]='+';
            }
            System.out.println();
        }
    }
    void roomGen(){
        boolean in=false;
        for(int i=0;i<rand.nextInt(40-10)+10;i++){
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
        roomPrint();
    }
    void roomPrint(){//TODO
        rooms.forEach((r) -> {
            for(int i=r.y;i<r.y+r.h;i++){
                for(int j=r.x;j<r.x+r.w;j++)
                    if(i==r.y||j==r.x||i==(r.y+r.h-1)||j==(r.x+r.w-1))
                        map[i][j]=0;
            }
        });
    }
    
    void corridorPrint(){
        PathFinder pf = new PathFinder(map,this.X,this.Y);
        // Point start = pathFinder.new Point(1,1);// Hачальная точка
        // Point end = pathFinder.new Point(3,3);//Конечная точка
        // Point[] path = pathFinder.find(start,end); // Hайдем путь
       // for (int i=0;i<rooms.size();i++) {
        Point[] path = pf.find(new Point(2,2),new Point(4,10));
        
            for(Point p: path)
                map[p.getX()][p.getY()]=0;
            
        //}
    }   
    
}
