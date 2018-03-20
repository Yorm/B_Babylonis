package rog_pg;

import java.util.ArrayList;
import java.util.Random;

public class GenerateMap {
    // <editor-fold defaultstate="collapsed" desc="Fields">
    private final int Y;
    private final int X;
    private char[][] map;
    Random rand = new Random();
    
    ArrayList<Room> rooms;
    
    public GenerateMap(){
        rooms=new ArrayList<>();
        this.Y=40;
        this.X=40;
        this.map = new char[X][Y];
        mapFill();
    }
    public GenerateMap(int x, int y){
        rooms=new ArrayList<>();
        this.Y=y;
        this.X=x;
        this.map = new char[X][Y];
        mapFill();
    }
    // </editor-fold>   
    public final void mapFill(){
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++)
                if(i==0||i==(X-1)) map[i][j] = '-';
                else if(j==0||j==(Y-1))map[i][j] = '|'; 
                else map[i][j] = '.';   
        }
        map[0][Y-1]=map[X-1][0]=map[0][0]=map[X-1][Y-1]='+';
    }
    public void mapPrint(){
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++)
                System.out.print(map[i][j]+" ");
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
                        map[i][j]='#';
            }
        });
    }
    public void veldGen(){
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++){
                randDotChar(rand.nextInt(X-2)+1,rand.nextInt(Y-2)+1,'^');
                randDotChar(rand.nextInt(X-2)+1,rand.nextInt(Y-2)+1,',');
                randDotChar(rand.nextInt(X-2)+1,rand.nextInt(Y-2)+1,'\"');
                randDotChar(rand.nextInt(X-2)+1,rand.nextInt(Y-2)+1,'\'');
            }
        }  
    }
    private void randDotChar(int x,int y,char c){
        x=rand.nextInt(X-2)+1;
        y=rand.nextInt(Y-2)+1;
        map[x][y]=c; 
    }
    
}
