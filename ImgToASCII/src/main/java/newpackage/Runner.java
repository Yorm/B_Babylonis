
package newpackage;

import java.io.IOException;

public class Runner {
    
    public static void main(String args[]) throws IOException{
        ImgBreak b =new ImgBreak("256 2.jpg");
        
        b.inBlackWhite();
        b.imgToAscii();
    }
    
}
