package bibliotheca;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

public class Book {
    final private int pageC = 410;
    final private int stringC = 39;
    final private int  charC = 80;

    private String oldFutark = "ᚠᚣᚦᚫᚱᚲᚷᚹᚺᛀᛁᛃᛇᛈᛉᛊᛏᛒᛖᛗᛚᛜᛞᛟ᛫᛬";
    private String alphEng = "abcdefghijklmnopqrstuvwxyz ,.";

    private String url = "https://norse.ulver.com/dct/new/";
    private String[] htmlAlf = {"a","b","d","e","f","g","h","i","j","k","l","m","n","o","p","r","s","t","u","v","y",
            "th","ae","oe","au"};

    Random r;
    StringBuilder sb;
    public Book(){}

    public void makeSimpleBook(){
        sb = new StringBuilder();
        r = new Random();
        try(FileWriter writer = new FileWriter("book.txt", false)){
            for( int i =0; i<pageC;i++){
                for( int j =0; j<stringC;j++){
                    for( int k =0; k<charC;k++){
                        sb.append(alphEng.charAt(r.nextInt(alphEng.length())));
                    }
                    writer.write(sb.toString());
                    writer.append('\n');
                    sb.setLength(0);
                }
                writer.write(i+1+"\n");
            }
            writer.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public void makeGoodBook(){

        r = new Random();
        sb = new StringBuilder();

        List<Elements> dict = new ArrayList<>();
        Document doc = null;

        int sentLeng=0;
        int elNum = 0;
        int wordNum = 0;

        try {
            for(int i=0;i<htmlAlf.length;i++) {
                dict.add(Jsoup.connect(url + htmlAlf[i] + ".html").get().getElementsByClass("ar"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileWriter writer = new FileWriter("bookG.txt", false)){
            for(int j=0; j<100;j++) {
                sentLeng = r.nextInt((10 - 2) + 1) + 2;
                sb.append(" ");
                for (int i = 0; i < sentLeng; i++) {
                    elNum = r.nextInt(dict.size());
                    wordNum = r.nextInt(dict.get(elNum).size());
                    sb.append(dict.get(elNum).get(wordNum).id());
                    sb.append(" ");
                }
                sb.setCharAt(sb.length() - 1, '.');
                sb.setCharAt(1, Character.toUpperCase(sb.charAt(1)));
                if(r.nextInt(10)==1) sb.append('\n');
                writer.write(sb.toString());
                sb.setLength(0);
            }
            writer.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

}
