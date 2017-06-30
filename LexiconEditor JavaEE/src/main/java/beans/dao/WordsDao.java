package beans.dao;

import beans.classes.Word;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class WordsDao implements Serializable {
    // <editor-fold defaultstate="collapsed" desc="Fields">
    
    Map<String,Integer> unknown_words = new HashMap<>();
    List<String> know_words = new ArrayList<>();
    UserService usServ = new UserService();
    
    public WordsDao(){}
    
    // </editor-fold>
    
    public int countWords(){
        return usServ.countWords();
    }
    public void addKnow_word(String know_word) {
        System.out.println("add know word " + know_word);
        know_words.add(know_word);
    }
    public void delKnow_word(String know_word) {
        System.out.println("remove know word " + know_word);
        know_words.remove(know_word);
    }
    public void delUnknown_word(String unknown_word) {
        System.out.println("remove unknow word " + unknown_word);
        unknown_words.remove(unknown_word);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Get/Set methods">

    public List<String> getKnow_words() {
        return know_words;
    }

    public void setKnow_words(List<String> know_words) {
        this.know_words = know_words;
    }

    public Map<String,Integer> getUnknown_words() {
        return unknown_words;
    }
    public void setUnknown_words(Map<String,Integer> unknown_words) {
        this.unknown_words = unknown_words;
    }  
    public Set<String> KeySet() {
        return unknown_words.keySet();
    }
    public Collection<Integer> CollectionNumber() {
        return unknown_words.values();
    }
    // </editor-fold>
}
