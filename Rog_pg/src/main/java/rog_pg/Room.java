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
    
    public Room(){}
    
    public Room(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
   
    public boolean intersect(Room r){
       return !(r.x+1 >= (x+1 + w+1) || x+1 >= (r.x+1 + r.w+1) || r.y+1 >= (y+1 + h+1) || y+1 >= (r.y+1 + r.h+1));
    }

    @Override
    public String toString() {
        return "Room{" + "roomX=" + x + ", roomY=" + y+ ", roomW=" + w + ", roomH=" + h+ '}';
    }

}
