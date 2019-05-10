package bibliotheca.books;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BabylonianBook implements Book {
    final private int pageC = 410;
    final private int stringC = 39;
    final private int  charC = 80;


    private String alph;

    private Random r;
    private StringBuilder sb;

    public BabylonianBook(){
        this.alph = "abcdefghijklmnopqrstuvwxyz ,.";
    }

    public void makeBook(int count){

        sb = new StringBuilder();
        r = new Random();

        try {
            File oldNorthDir = new File("res/books/babylonian");
            if(!oldNorthDir.exists())
                oldNorthDir.mkdirs();
            FileUtils.cleanDirectory(oldNorthDir);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        for(int c=0;c<count;c++) {
            try (FileWriter writer = new FileWriter("res/books/babylonian/book"+(c+1)+".txt", false)) {
                for (int i = 0; i < pageC; i++) {
                    for (int j = 0; j < stringC; j++) {
                        for (int k = 0; k < charC; k++) {
                            sb.append(alph.charAt(r.nextInt(alph.length())));
                        }
                        writer.write(sb.toString());
                        writer.append('\n');
                        sb.setLength(0);
                    }
                    writer.write(i + 1 + "\n");
                }
                writer.flush();
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    public void setAlph(String alph){
        this.alph = alph;
    }
    public String getAlph(){
        return this.alph;
    }

}
