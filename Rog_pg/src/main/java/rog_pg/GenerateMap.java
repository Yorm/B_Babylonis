package rog_pg;

import java.util.ArrayList;
import java.util.Random;

public class GenerateMap {
    // <editor-fold defaultstate="collapsed" desc="Fields">
    private final int Y;
    private final int X;
    private int[][] map;
    Random rand = new Random(System.currentTimeMillis());
    ArrayList<Room> rooms;
    
    public GenerateMap(){
        rooms=new ArrayList<>();
        this.Y=40;
        this.X=40;
        this.map = new int[X][Y];
        mapFill(2);
    }
    public GenerateMap(int x, int y){
        rooms=new ArrayList<>();
        this.Y=y;
        this.X=x;
        this.map = new int[X][Y];
        mapFill(2);
    }
    // </editor-fold>   
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

       /* int x, y, f, up = 100;
        x = 40;
        y = 40;
        map[x][y] = 2;
        for (int j = 0; j < up; j++) {
            for (; ; ) {
                x = rand.nextInt(X - 2) + 2;
                y = rand.nextInt(Y - 2) + 2;
                if (map[x][y] == 2) break;
            }
            for (int i = 0; i < rand.nextInt(max - min) + min; i++) {
                f = rand.nextInt(4 - 1) + 1;
                switch (f) {
                    case 1:
                        if (x < X - 2) x++;
                        else i--;
                        break;
                    case 2:
                        if (x > 2) x--;
                        else i--;
                        break;
                    case 3:
                        if (y < Y - 2) y++;
                        else i--;
                        break;
                    case 4:
                        if (y > 2) y--;
                        else i--;
                        break;
                }
                map[x][y] = 2;

            //if(i==up-2){
            //    int px,py;
            //    for(;;) {
             //       px = rand.nextInt(X - 2) + 2;
             //       py = rand.nextInt(Y - 2) + 2;
             //       if(map[px][py]==2) break;
             //   }
            //    corridorPrint(new Point(x,y),new Point(px,py));
           // }
            }
        }*/


        /*for(int ix=1;ix<X-1;ix++){
            for(int jy=1;jy<Y-1;jy++)
                if(map[ix][jy]==0&&map[ix+1][jy]==2&&map[ix-1][jy]==2&&map[ix][jy+1]==2&&map[ix][jy-1]==2)
                    map[ix][jy]=2;
        }*/
    }
    //TODO
    public void simpleForest(int min,int max){
        mapFill(5);
        int x,y;
        for(int i=0;i<rand.nextInt(max-min)+min;i++){
            x=rand.nextInt(X-1)+1;
            y=rand.nextInt(Y-1)+1;
            if(map[x][y]!=3){ map[x][y]=3; }
            else i--;
        }
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
    //TODO
    //TODO up and down
    //TODO
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
