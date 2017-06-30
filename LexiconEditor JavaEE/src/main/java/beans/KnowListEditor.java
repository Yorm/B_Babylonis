package beans;

import beans.classes.Word;
import beans.dao.UserDao;
import beans.dao.WordsDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named
@RequestScoped
public class KnowListEditor implements Serializable{
     // <editor-fold defaultstate="collapsed" desc="Fields">
    private String word;
    
    @Inject
    WordsDao wordsDao;
    @Inject
    UserDao userDao;
    // </editor-fold>
    
    public void addWord(){
        boolean b=true;
        ArrayList a = new ArrayList ();
        a.addAll(userDao.getCurrentUser().getListForSet());
        for(int i=0;i<a.size();i++){
            if(a.get(i).equals(word))
                b=false;
        }
        if(b){   
            wordsDao.addKnow_word(word);
            System.out.println("add " +word);
            userDao.getCurrentUser().addWordInSet(new Word(word));
            word="";
        }
        
    }
    public void delWord(){
        wordsDao.delKnow_word(word);
        userDao.getCurrentUser().delWordInSet(new Word(word));
    }
    
    // <editor-fold defaultstate="collapsed" desc="Get/Set methods">
    public Set<String> getKnow_words(){
        return userDao.getCurrentUser().getListForSet();
    }
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    } 
    // </editor-fold>
}
