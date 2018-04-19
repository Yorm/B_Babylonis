
package rog_pg;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class OutputMap {
    private final int Y=64;
    private final int X=64;
    private int[][] map;

    
    void main(){
        Random rand = new Random();

        //GDungeon d = new GDungeon(X,Y,2);
        GForest f= new GForest(X,Y,5);
        //GCave c = new GCave(X,Y,0);

        //d.simpleLabyrinth(5,20);
        //d.proceduralGen(199,201);
        f.simpleForest(40,80);
        //c.simpleCave(127,127,130,150,100);

        map = f.getMap();
        mapWrite("map.txt");

    }
    private void mapWrite(String fileName){
        try(FileWriter writer = new FileWriter(fileName, false))
        { 
            writer.write("hello"
                    + "\n");
            for(int x=0;x<X;x++){
                for(int y=0;y<Y;y++){
                    switch (map[x][y]){
                        case 0:writer.append('#');break;
                        case 1:writer.append('_');break;
                        case 2:writer.append('.');break;
                        case 3:writer.append('T');break;
                        case 4:writer.append('O');break;
                        case 5:writer.append(',');break;
                        default: writer.append('@');break;
                    }
                   writer.append(' ');
                }
                writer.append('\n');
            }
            writer.flush();
        }
        catch(IOException ex){ 
            System.err.println(ex.getMessage());
        }

    }
}
