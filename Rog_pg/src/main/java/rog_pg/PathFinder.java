/*
thanks for the basis of this class
developersonthe.net
 */
package rog_pg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PathFinder{
    
    int[][] fillmap;
    int[][] labyrinth;
    List buf = new ArrayList();

    PathFinder (int[][] labyrinth, int x, int y){
        this.fillmap = new int[x][y];
        this.labyrinth = labyrinth;
        this.buf = new ArrayList();
    }

    void push(Point p, int n){
        if(fillmap[p.getY()][p.getX()]<=n) return; 
        fillmap[p.getY()][p.getX()]=n; 
        buf.add(p); 
    }

    Point pop(){
        if(buf.isEmpty()) return null;
        return (Point)buf.remove(0);
    }
    //не работает с краями карты (0,39)
    //уводит в одну из сторон в последних вычислениях
    public Point[] find(Point start, Point end){
        int tx=0, ty=0, n = 0, t=0;
        Point p;

        for(int i=0; i<fillmap.length;i++)
            Arrays.fill(fillmap[i], Integer.MAX_VALUE);
        push(start, 0); 
        
        while((p = pop())!=null){ 
            if(p.equals(end)){
                System.out.println("Hайден путь длины "+n);
            }

            n=fillmap[p.getY()][p.getX()]+labyrinth[p.getY()][p.getX()];

            if((p.getY()+1<labyrinth.length)&&labyrinth[p.getY()+1][p.getX()]!=0) push(new Point(p.getX(), p.getY()+1), n);
            if((p.getY()-1>=0)&&(labyrinth[p.getY()-1][p.getX()]!=0)) push(new Point(p.getX(), p.getY()-1), n);
            if((p.getX()+1<labyrinth[p.getY()].length)&&(labyrinth[p.getY()][p.getX()+1]!=0)) push(new Point(p.getX()+1, p.getY()), n);
            if((p.getX()-1>=0)&&(labyrinth[p.getY()][p.getX()-1]!=0)) push(new Point(p.getX()-1, p.getY()), n);
        }
        
        if(fillmap[end.getY()][end.getX()]==Integer.MAX_VALUE){
            System.err.println("Пути не существует");
            return null;
        } else
            System.out.println("Поиск завершен");
        
        List path = new ArrayList();
        path.add(end);
        int x = end.getX();
        int y = end.getY();
        n = Integer.MAX_VALUE; 
        
        while((x!=start.getX())||(y!=start.getY())){ 
            //TODO
            if(fillmap[y+1][x]<n){tx=x; ty=y+1; t=fillmap[y+1][x];}
            if(fillmap[y-1][x]<n){tx=x; ty=y-1; t=fillmap[y-1][x];}
            if(fillmap[y][x-1]<n){tx=x-1; ty=y; t=fillmap[y][x-1];}
            if(fillmap[y][x+1]<n){tx=x+1; ty=y; t=fillmap[y][x+1];}
            
 
            x = tx;
            y = ty;
            n = t; 
            path.add(new Point(x,y));
        }
        /*
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++){
                System.out.print(labyrinth[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++){
                System.out.print(fillmap[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        */
        Point[] result = new Point[path.size()];
        t = path.size();
        for(Object point: path)
            result[--t] = (Point)point;
        return result;
    }

}