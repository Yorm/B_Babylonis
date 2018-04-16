
package rog_pg;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class OutputMap {
    private final int Y=128;
    private final int X=128;
    private int[][] map;

    
    void main(){
        GenerateMap gmap = new GenerateMap(X,Y);
        Scanner sc = new Scanner(System.in);
        char c;
        for(;;){ 
           // gmap.simpleLabyrinth(249,251);
            // gmap.proceduralGen(199,201);
            //gmap.bigSmoke();
            //gmap.simpleForest(40,80);
            gmap.simpleCave(15,30);
            map = gmap.getMap();
            //mapPrint();
            mapWrite();
            System.out.println("press [g|G] to GEN or [e|E] to END");
            //c = sc.next().charAt(0);
            c='e';
            if(c=='g'||c=='G'){
                gmap = new GenerateMap(X,Y);
                continue;
            }else{
            if(c=='e'||c=='E')
                return; 
            }
        }
    }
    private void mapWrite(){
        try(FileWriter writer = new FileWriter("map.txt", false))
        { 
            writer.write("hello"
                    + "\n");
            for(int x=0;x<X;x++){
                for(int y=0;y<Y;y++){
                    switch (this.map[x][y]){
                        case 0:writer.append('#');break;
                        case 1:writer.append('_');break;
                        case 2:writer.append('.');break;
                        case 3:writer.append('T');break;
                        case 4:writer.append('O');break;
                        case 5:writer.append(',');break;
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
    private void mapPrint(){
        char map[][]=new char[X][Y]; 
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++){
                switch (this.map[i][j]){
                    case 0:System.out.print("#");break;
                    case 1:System.out.print("_");break;
                    case 2:System.out.print(".");break;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
