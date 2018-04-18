package rog_pg;

import java.util.ArrayList;
import java.util.Random;

public class GDungeon extends GMap {
    private int Y;
    private int X;
    private int[][] map;
    private Random rand;
    private ArrayList<Room> rooms;

    public GDungeon(int x, int y,int f){
        rooms=new ArrayList<>();
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

    public void proceduralGen(int min,int max){
        boolean in=false;

        int roomCount=rand.nextInt(max-min)+min;
        int prsnt=(int) Math.round((roomCount/100.0)*7);

        System.out.println("room count = "+roomCount);
        System.out.println("big room = " +prsnt);
        for(int i=0;i<roomCount;i++){//todo if prsnt - print corridors
            Room r;
            if(prsnt>0){
                r = roomGen(i,true);
                prsnt--;
            }else{r = roomGen(i,false);}

            for(Room room:rooms){
                if(r.intersect(room)){in=true;break;}
                else{in=false; }
            }
            if(!in){
                rooms.add(r);
                //if(rooms.size()>=2){
                //corridorPrint(rooms.get(i-1),rooms.get(i));
                //}
            }else {i--;}
        }
        for(int i=1;i<prsnt;i++){
            //у каждой комнаты будет массив из путей(массивов точек)
            //из которого я выберу минимальный и протяну туда коридор
            //тоесть
            /*речь о больших комнатах
            1) я провожу из первой комнаты коридоры во все комнаты
            2) я выбираю кратчайший(один из кратчайших) и записываю его в массив путей
            3) я провожу из второй комнаты пути во все комнаты кроме первой и выбираю кратчайший и записываю его в массив путей
            4)....
            5) я провожу пути
            */
            corridorPrint(rooms.get(0),rooms.get(i));

        }
        roomPrint();
    }

    //COMPLITE (5-20 rooms)
    public void simpleLabyrinth(int min,int max){
        boolean in=false;
        for(int i=0;i<rand.nextInt(max-min)+min;i++){
            Room r = roomGen(i,false);
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

    private Room roomGen(int i,boolean bigRoom){
        Room r = new Room();//todo global rand?
        if(bigRoom){
            r.setId(i);
            r.setType(0);
            r.setX(rand.nextInt(X-13)+2);
            r.setY(rand.nextInt(Y-13)+2);
            r.setH(rand.nextInt(12-8)+8);
            r.setW(rand.nextInt(12-8)+8);
        }else{
            r.setId(i);
            r.setType(0);
            r.setX(rand.nextInt(X-11)+2);
            r.setY(rand.nextInt(Y-11)+2);
            r.setH(rand.nextInt(9-3)+3);
            r.setW(rand.nextInt(9-3)+3);
        }
        return r;
    }
    private void roomPrint(){//TODO
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
                } break;
            }
        }
    }
    private void cleanRooms(){
        for(int i=1;i<X-1;i++)
            for(int j=1;j<Y-1;j++){
                if(map[i][j]==2 && map[i+1][j]==999) map[i][j]=0;
                if(map[i][j]==2 && map[i-1][j]==999) map[i][j]=0;
                if(map[i][j]==2 && map[i][j-1]==999) map[i][j]=0;
                if(map[i][j]==2 && map[i][j+1]==999) map[i][j]=0;
            }
        for(int i=0;i<X;i++)
            for(int j=0;j<Y;j++){
                if(map[i][j]==999) map[i][j]=2;
            }
    }

    //TODO TODO TODO TODO
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
    //TODO
    //    //TODO up and down
    //    //TODO
    private void corridorPrint(Point a, Point b){
        PathFinder pf = new PathFinder(map,this.X,this.Y);
        Point[] path;

        path = pf.find(a,b);
        for (Point p:path) {
            map[p.getX()][p.getY()]=2;
        }
    }
    public int[][] getMap() {
        return map;
    }
}
