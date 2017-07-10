
package newpackage;

import java.io.IOException;

public class Runner {
    
    public static void main(String args[]) throws IOException{
        ImgBreak b =new ImgBreak("img_0.png");
        
        b.inBlackWhite();
        b.imgToAscii();
    }
    
}
