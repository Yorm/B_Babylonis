
package newpackage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImgBreak {
    private BufferedImage image;
    private int width;
    private int height;
    public Color[][] pixels;
    private char[][] chars;
    
    public ImgBreak(String name) throws IOException  {
        this.image = ImageIO.read(new File("src/main/java/img/"+name));
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
        
        Color[][] p = new Color[width][height];
        for(int w=0;w<this.width;w++){
            for(int h=0;h<this.height;h++)
                p[h][w] = getPixelColor(h,w);
        }
        pixels=p;
        chars=new char[width][height];
    }
    
    public void imgToAscii(){  
        for(int w=0;w<this.width;w++){
            for(int h=0;h<this.height;h++)
                chars[h][w] = asciiSwitch(pixels[h][w]);
        }
        for(int w=0;w<this.width;w++){
            for(int h=0;h<this.height;h++)
                System.out.print(chars[h][w]+" ");
            System.out.println("\n");
        }
        asciiWrite();
    }
    
    private char asciiSwitch(Color color){
        switch(color.getRed()/10){
            case 0: return 'W';
            case 1:return '#';
            case 2:return 'H';
            case 3:return '&';
            case 4:return '%';
            case 5:return 'h';
            case 6:return 'k';
            case 7:return 'j';
            case 8:return '?';
            case 9:return '+';
            case 10:return '=';
            case 11:return 'c';
            case 12:return '/';
            case 13:return ';';
            case 14:return ':';
            case 15:return '^';
            case 16:return '^';
            case 17:return '°';
            case 18:return '\"';           
            case 19:return '²';
            case 20:return '\'';
            case 21:return '\'';
            case 22:return ',';
            case 23:return '_';
            case 24:return '-';
            case 25:return '.';
            default:return '.';
        }
    }
    
    public void asciiWrite(){
        try(FileWriter writer = new FileWriter("notes.txt", false))
        { 
            writer.write("hello"
                    + "\n");
            for(int w=0;w<this.width;w++){
                for(int h=0;h<this.height;h++){
                    writer.append(chars[h][w]);
                   // writer.append(' ');
                }
                writer.append('\n');
            }
            writer.flush();
        }
        catch(IOException ex){ 
            System.err.println(ex.getMessage());
        } 
    }
    
    public void inBlackWhite(){
        for(int w=0;w<this.width;w++){
            for(int h=0;h<this.height;h++){
                int c = Math.round((pixels[h][w].getRed()+pixels[h][w].getGreen()+pixels[h][w].getBlue())/3);
                pixels[h][w] = new Color(c,c,c);
                image.setRGB(h,w,pixels[h][w].getRGB()); 
            }
        }
        imgWrite();
    }
    
    public void imgWrite() {  
        try {
            ImageIO.write(image, "png", new File("image.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public Color getPixelColor(int x, int y) {
        Object colorData = image.getRaster().getDataElements(x, y, null);
        int argb = image.getColorModel().getRGB(colorData);
        return new Color(argb, true);
    }
}
