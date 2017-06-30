package beans;

import beans.classes.Word;
import beans.dao.UserDao;
import beans.dao.WordsDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CuttingBean implements Serializable{
    // <editor-fold defaultstate="collapsed" desc="Fields">
    private String defText;
    private String unkWords;
    
    @Inject
    WordsDao wordsDao;
    @Inject
    UserDao userDao;
    
    public CuttingBean() {}
    // </editor-fold>

    
    public void cutting(){  
        List<String> words_list;
        words_list=new ArrayList(Arrays.asList(defText.replaceAll("[^a-zA-Z 0-9]+", " ").toLowerCase().split("\\s+")));     
        Map<String,Integer> words_map = countWords(words_list);
        System.out.println(words_map);
        wordsDao.setUnknown_words(words_map);
    }
    
    public void clean(){ 
        defText=null;
        wordsDao.setUnknown_words(null);
        addMessage("", "Чисто");   
    }
    
    public Map<String,Integer> countWords(List<String> list) 
    {
        ArrayList<Word> cUserWords= new ArrayList<>();
        cUserWords.addAll(userDao.getCurrentUser().getWords());
        try{
            Iterator<String> it = list.iterator();
            int s = list.size();
            for (int i=0;i<s;i++) {
                String nxt = it.next();
                for (Word cWord : cUserWords) {
                    if(nxt.equals(cWord.getWord())){
                    it.remove();
                    }
                } 
            }
        }catch(Exception ex){
            System.out.println("cutting ERROR +\n"+ex);
        }
                
        HashMap<String,Integer> result = new HashMap<>(); 
        int count;
        String s1,s2;
        for (int i = 0; i < list.size() ; i++) 
        {
            s1 = list.get(i); 
            count = 0; 
            for (int j = 0; j <list.size(); j++) 
            {
                s2 = list.get(j);
                if (s1.equals(s2)) count++; 
            }
            result.put(s1,count); 
        }
        return result; 
    }
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Get/Set methods">
    public String getDefText() {
        return defText;
    }

    public void setDefText(String defText) {
        this.defText = defText;
    }

    public String getUnkWords() {
        return unkWords;
    }

    public void setUnkWords(String unkWords) {
        this.unkWords = unkWords;
    } 
    // </editor-fold>

}
