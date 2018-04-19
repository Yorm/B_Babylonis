package rog_pg;

/**
 *
 * @author walte
 */
public class Room {
    int x;
    int y;
    int w;
    int h;
    int id;
    int type;
    
    public Room(){}
    public Room(int id){this.id = id;}
    public Room(int x, int y, int w, int h,int id) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.id = id;
    }
   
    public boolean intersect(Room r){
       return !(r.x >= (x+1 + w+1) || x >= (r.x+1 + r.w+1) || r.y >= (y+1 + h+1) || y >= (r.y+1 + r.h+1));
    }

    @Override
    public String toString() {
        return "Room{" + "x=" + x + ", y=" + y + ", w=" + w + ", h=" + h + ", id=" + id + '}';
    }

    public Point getCenter(){
        return new Point(y+h/2,x+w/2);
    }

    public int[][] defaultRoom(){
        int[][] labyrinth = {//h=5//w=5//t=1
                	{0,0,0,0,0},
                        {0,1,1,1,0},
                        {0,1,0,1,0},
                        {0,1,1,1,0},
                        {0,0,0,0,0}             
         	};
        return labyrinth;
    }
    public int[][] sphereRoom(){
        int[][] labyrinth = {//h=10//w=10//t=2
                	    {1,1,1,1,0,0,1,1,1,1},
                        {1,1,0,0,1,1,0,0,1,1},
                        {1,0,1,1,1,1,1,1,0,1},
                        {1,0,1,1,1,1,1,1,0,1},
                        {0,1,1,1,1,1,1,1,1,0},
                        {0,1,1,1,1,1,1,1,1,0},
                        {1,0,1,1,1,1,1,1,0,1},
                        {1,0,1,1,1,1,1,1,0,1},
                        {1,1,0,0,1,1,0,0,1,1},
                        {1,1,1,1,0,0,1,1,1,1},
         	};
        return labyrinth;
    }
    
    public void setType(int type) {
        this.type = type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

}
