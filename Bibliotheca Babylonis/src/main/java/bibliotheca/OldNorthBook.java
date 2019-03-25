package bibliotheca;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class OldNorthBook implements Book{

    private final String url;
    private final String[] htmlAlf;
    private String wordsLib;
    private String oldFutark = "ᚠᚣᚦᚫᚱᚲᚷᚹᚺᛀᛁᛃᛇᛈᛉᛊᛏᛒᛖᛗᛚᛜᛞᛟ᛫᛬";

    private Random r;
    private StringBuilder sb;
    private FileWriter writer;

    public OldNorthBook(){
        this.wordsLib ="res/dict/";
        this.url = "https://norse.ulver.com/dct/new/";
        this.htmlAlf = new String[]{"a","b","d","e","f","g","h","i","j","k","l","m","n","o","p","r","s","t","u","v","y",
                "th","ae","oe","au"};
    }

    public void makeBook(int count){

        r = new Random();
        sb = new StringBuilder();

        List<Elements> dict = new File(wordsLib).list().length==0?loadFromNU():loadFromOnf();
        if(dict == null){
            System.out.println("BE DA");
            System.exit(1);
        }

        int sentLeng=0;
        int elNum = 0;
        int wordNum = 0;

        for(int c=0;c<count;c++) {
            try {
                writer = new FileWriter("res/books/oldNorth/book" + (c + 1) + ".txt", false);
                for (int j = 0; j < 100; j++) {
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
                    if (r.nextInt(10) == 1) sb.append('\n');
                    writer.write(sb.toString().replaceAll("[\\(\\)]", ""));
                    sb.setLength(0);
                }
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("End");
    }


    private List<Elements> loadFromOnf(){
        List<Elements> dict = new ArrayList<>();
        try {
            System.out.println("Upload from files...");
            List<File> onfList = Files.walk(Paths.get(wordsLib))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            for(File onf:onfList){
                Elements els = Jsoup.parse(Files.lines(Paths.get(onf.getPath()))
                        .reduce("", String::concat))
                        .getElementsByClass("ar");
                dict.add(els);
                System.out.print('-');
            }
            System.out.println("+");

            return dict;

        } catch (IOException e) { e.printStackTrace(); return null;}
    }

    private List<Elements> loadFromNU(){
        List<Elements> dict = new ArrayList<>();
        try {
            System.out.println("Download from site...");
            Elements els;
            for (int i = 0; i < htmlAlf.length; i++) {
                writer = new FileWriter(wordsLib + htmlAlf[i]+".onf", false);
                //stream?
                els = Jsoup.connect(url + htmlAlf[i] + ".html")
                        .userAgent("Mozilla")
                        .get()
                        .getElementsByClass("ar");

                dict.add(els);
                //stream?
                for(Element l:els){ writer.write(l+"\n"); }
                writer.flush();
                System.out.print('-');
            }
            System.out.println("+");

            return dict;

        } catch (IOException e) { e.printStackTrace(); return null;}

    }
}
