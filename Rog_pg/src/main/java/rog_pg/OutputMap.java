
package rog_pg;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import rog_pg.GenerateMap;

public class OutputMap {
    int Y=40;
    int X=40;
    int[][] map;
    
    void main(){
        GenerateMap gmap = new GenerateMap(40,40);
         Scanner sc = new Scanner(System.in);
        char c;
        for(;;){ 
            gmap.roomGen();
            map = gmap.getMap();
            mapPrint();
            
            System.out.println("press [g|G] to GEN or [e|E] to END");
            c = sc.next().charAt(0);
            if(c=='g'||c=='G'){
                gmap = new GenerateMap(40,40);
                continue;
            }else{
            if(c=='e'||c=='E')
                return; 
            }
        }
    }
    public void mapPrint(){
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
