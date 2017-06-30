package beans;

import beans.classes.WordAndTranslate;
import beans.classes.YandexTranslateAPI;
import beans.dao.WordsDao;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ToStudy implements Serializable{
    // <editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    WordsDao wordsDao;
    
    private String word;
    // </editor-fold>
    private List<WordAndTranslate> wat_list;
    YandexTranslateAPI yanAPI = new YandexTranslateAPI();
    public List<WordAndTranslate> getWat_list() {
        Wat_list_create();
        return wat_list;
    }
    public void translate(){
        try{      
            addMessage("Переводится как...", yanAPI.translate(word));
        }catch(Exception e){}
    }
    public void Wat_list_create() {
        wat_list=new ArrayList<>();
        List<String> trans_words = new ArrayList<>();
        
        List<String> words= new ArrayList<>();
        words.addAll(wordsDao.KeySet()); 
        try{
            for(String a : words)
                trans_words.add(yanAPI.translate(a));
            System.out.println("перевод"+yanAPI.translate("human leg"));
        }catch(IOException ex){ 
            System.err.println("translate error");
            for(int i =0; i<words.size();i++)
                trans_words.add("Нет подключения к интернету");
        }
        
        System.out.println(words.size() + " "+trans_words.size());
        
        for(int i=0;i<words.size();i++)   
            wat_list.add(new WordAndTranslate(words.get(i),trans_words.get(i))); 
    }  
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
// <editor-fold defaultstate="collapsed" desc="Get/Set methods">
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
// </editor-fold>  
}
