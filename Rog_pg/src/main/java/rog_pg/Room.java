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
       return !(r.x >= (x + w) || x >= (r.x + r.w) || r.y >= (y + h) || y >= (r.y + r.h));
    }

    @Override
    public String toString() {
        return "Room{" + "roomX=" + x + ", roomY=" + y+ ", roomW=" + w + ", roomH=" + h+ '}';
    }

}
