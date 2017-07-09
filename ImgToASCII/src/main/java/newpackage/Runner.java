
package newpackage;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Runner {
    
    public static void main(String args[]) throws IOException{
        ImgBreak b =new ImgBreak("img_0.png");
        b.imgToAscii();
    }
    
}
