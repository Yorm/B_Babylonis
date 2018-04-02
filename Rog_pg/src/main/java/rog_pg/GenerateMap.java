package rog_pg;

import java.util.ArrayList;
import java.util.Random;

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
    private void mapFill(){
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++){
                map[i][j] = 1;   
            }
        }
    }
    public void bigSmoke(int min,int max){
        boolean in=false;
        for(int i=0;i<rand.nextInt(max-min)+min;i++){
            Room r;
            if((rand.nextInt(10-1)+1)==1){
               r= createRoom(i);
            }else {r = roomGen(i);}
            
            for(Room room:rooms){
                if(r.intersect(room)){in=true;break;}
                else{in=false; }
            }
            if(!in){
                rooms.add(r);
                if(rooms.size()>=2){
                  //  corridorPrint(rooms.get(i-1),rooms.get(i));
                }    
            }else {i--;}
        }  
        roomPrint();
    }
    public Room createRoom(int id){
        Room r = new Room(id);
        r.setType(rand.nextInt(2-1)+1);
        switch(r.getType()){
            case 1: r.setW(5); r.setH(5); break;
            case 2: r.setW(10); r.setH(10); break;  
        }
        r.x=rand.nextInt(X-11)+2;
        r.y=rand.nextInt(Y-11)+2;
        return r;
    }
    /*public void slTest(){
        Room r = roomGen();
        
        rooms.add(r);
        int wall = rand.nextInt(4)+1;//1-up 2-down 3-left 4-right
        switch(wall){
            case 1:  break;
            case 2: break;
            case 3: break;
            case 4: break;
            default: System.err.println("rog_pg.GenerateMap.slTest() switch");
        }
        
        roomPrint();
    }*/
    public void simpleLabyrinth(int min,int max){
        boolean in=false;
        for(int i=0;i<rand.nextInt(max-min)+min;i++){
            Room r = roomGen(i);
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
    private Room roomGen(int i){
        Room r = new Room();
            r.setId(i);
            r.setType(0);
           // r.x=(int)rand.nextGaussian(X-11)+2;
            r.setX(rand.nextInt(X-11)+2);
            r.setY(rand.nextInt(Y-11)+2);
            r.setH(rand.nextInt(6)+3);
            r.setW(rand.nextInt(6)+3);
        return r;
    }
    private void roomPrint(){//TODO
        int[][] lab;
        for(Room r:rooms) {
            switch(r.type){
                case 0:{
                    for(int i=r.y;i<r.y+r.h;i++){
                        for(int j=r.x;j<r.x+r.w;j++)
                           //if(i==r.y||j==r.x||i==(r.y+r.h-1)||j==(r.x+r.w-1)){
                                map[i][j]=999;
                            //}else{map[i][j]=1;}
                    }
                    cleanRooms();
                }
                case 1: lab = r.defaultRoom(); insertRoom(r,lab); break;
                case 2: lab = r.sphereRoom(); insertRoom(r,lab); break;
            }  
        }  
    }
    public void insertRoom(Room r, int lab[][]){
        //TODO
        for(int i=r.y;i<r.y+r.h;i++){
            for(int j=r.x;j<r.x+r.w;j++)
//                map[i][j]=lab[i-r.y][j-r.x];
                System.out.print(lab[i-r.y][j-r.x]);
            System.out.println();
        }
    }
    private void cleanRooms(){
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
    private void corridorPrint(Room a, Room b){
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
