package beans;

import beans.classes.Word;
import beans.dao.WordsDao;
import beans.dao.UserDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class Selection implements Serializable{
    // <editor-fold defaultstate="collapsed" desc="Fields">
        private ArrayList<String> unknownWordsList;
        private ArrayList<Integer> numberList;
        private List<String> known;
        
        private String actualWord;
        private Integer actualNumber;
        int i;
        boolean page;
        @Inject
        WordsDao wordsDao;
        @Inject
        UserDao userDao;

        public Selection() {}
    // </editor-fold>
    
    @PostConstruct
    public void init() {
        i=0;
        page=false;
        unknownWordsList = new ArrayList(wordsDao.KeySet());
        numberList = new ArrayList(wordsDao.CollectionNumber());
        known  = new ArrayList<>(); 
        actualWord=unknownWordsList.get(i);
        actualNumber=numberList.get(i);
        
    }
    public void reset(){
        i=0; 
        page=false;
        actualWord=unknownWordsList.get(i);
        actualNumber=numberList.get(i);
    }
    public void packing(Boolean b){
        System.out.println("packing "+b);
        if(b){
            if(i<unknownWordsList.size()){
                wordsDao.addKnow_word(unknownWordsList.get(i));
                wordsDao.delUnknown_word(unknownWordsList.get(i));
                System.out.println(unknownWordsList.get(i));
                packing(i);
                userDao.getCurrentUser().addWordInSet(new Word(unknownWordsList.get(i)));
                i++;   
            }
        }else{
            if(i<unknownWordsList.size()){
                packing(i);
                i++;
            } 
        }
        if(i==unknownWordsList.size())
            page = true;

    }
    
    private void packing(int i){
        if(i+1<unknownWordsList.size()){
            actualWord=unknownWordsList.get(i+1);//i+1?
            actualNumber=numberList.get(i+1);
        }
        else if (i+1<unknownWordsList.size()){
            actualWord=unknownWordsList.get(i);
            actualNumber=numberList.get(i);
        } 
    }
    
    
    public List<String> getKnow_words(){
        return wordsDao.getKnow_words();
    }
    public String pages(){
        if(!page) return "Следующее слово";
        else{return "Конец!";}
    }
    
    public void destroy(){

    }

    // <editor-fold defaultstate="collapsed" desc="Get/Set methods">
    
    public Integer getActualNumber() {
        return actualNumber;
    }
    
    public void setActualNumber(Integer actualNumber) {    
        this.actualNumber = actualNumber;
    }

    public List<String> getKnown() {
        return known;
    }

    public void setKnown(List<String> known) {
        this.known = known;
    }
    
    public int sizeArr(){
        return unknownWordsList.size();
    }
    
    public int getI() {
       if(i<unknownWordsList.size()) return i+1;
       else return i;       
    }

    public void setI(int i) {
        this.i = i;
    }
    
    public String getActualWord() {
        return actualWord;
    }

    public void setActualWord(String actualWord) {
        this.actualWord = actualWord;
    }  
    // </editor-fold>   
}
