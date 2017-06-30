package beans.classes;

public class WordAndTranslate {
    private String word;
    private String translate;
    public WordAndTranslate (String word,String translate ){
        this.word = word; 
        this.translate=translate;
    } 
    public String getWord() {return word;}
    public String getTranslate() {return translate;}  
}
