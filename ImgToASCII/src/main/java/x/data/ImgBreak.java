
package x.data;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImgBreak {
    private final BufferedImage image;
    private int width;
    private int height;
    public Color[][] pixels;
    public String[][] hex;
    private char[][] chars;
    
    
    public ImgBreak(String name) throws IOException  {
        this.image = ImageIO.read(new File("src/main/java/res.img/"+name));
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();

        Color[][] p = new Color[height][width];
        hex = new String[height][width];
        for(int h=0;h<this.height;h++){
            for(int w=0;w<this.width;w++){
                System.out.println(h+" "+w);
                p[h][w] = getPixelColor(w,h);
                this.hex[h][w] = rgbToHex(p[h][w]);
            }
        }
        pixels=p;
        
        chars=new char[height][width];
    }
    
    public void imgToAscii(){  
        for(int h=0;h<this.height;h++){
            for(int w=0;w<this.width;w++)
                chars[h][w] = asciiSwitch(pixels[h][w]);
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
        try{
            FileReader r = new FileReader("src/main/java/x/data/number");
            int i;
            String numb=new String();
            while(( i=r.read())!=-1){numb+=(char)i;} 
            FileWriter wrArt = new FileWriter("src/main/java/x/data/art"+numb+".txt", false);
            {
                i=Integer.parseInt(numb);
                i++;
                FileWriter wrNum = new FileWriter("src/main/java/x/data/number", false);
                wrNum.write(i+"");
                wrNum.flush();
            }
            for(int h=0;h<this.height;h++){
                for(int w=0;w<this.width;w++){
                    wrArt.append(chars[h][w]);
                    wrArt.append(' ');
                }
                wrArt.append('\n');
            }
            wrArt.flush();
        }
        catch(IOException ex){ 
            System.err.println(ex.getMessage());
        } 
    }
    
    public void inBlackWhite(){
        for(int h=0;h<this.height;h++){
            for(int w=0;w<this.width;w++){
                int c = Math.round((pixels[h][w].getRed()+pixels[h][w].getGreen()+pixels[h][w].getBlue())/3);
                pixels[h][w] = new Color(c,c,c);
                image.setRGB(w,h,pixels[h][w].getRGB()); 
            }
        }
        imgWrite();
    }
    
    private void imgWrite() {  
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
    
    
    //?
    private String rgbToHex(Color c){
        String h = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue()); 
        return h;
    }
    //?
    private Color hexToRgb(String h){
         return Color.decode(h);
    }
}
