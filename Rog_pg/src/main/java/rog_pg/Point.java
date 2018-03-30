package rog_pg;

public class Point{
    private int x;
    private int y;
    Point(int x, int y) {
        this.x=x;
        this.y=y;
    }
    public int getX() {
        return x;		
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Point)) return false;
            return (((Point)o).getX()==x) &&(((Point)o).getY()==y);
    }
    @Override
    public int hashCode(){
        return Integer.valueOf(x) ^ Integer.valueOf(y);
    }
    @Override
    public String toString(){
        return "x: "+Integer.valueOf(x).toString()+" y:"+Integer.valueOf(y).toString();
    }
}