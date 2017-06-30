package beans.classes;

import beans.dao.UserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users_table")
public class User implements Serializable
{
    // <editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
	
    @Column(name = "login")
    private String login;
	
    @Column(name = "name")
    private String name;
	
    @Column(name = "surname")
    private String surname;
	
    @Column(name = "password")
    private String password;
    
    @Transient
    private String confirmation;
	
    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = {CascadeType.MERGE})/*(cascade=CascadeType.ALL)*///@Cascade({CascadeType.SAVE_UPDATE})
    @JoinTable(name="user_word",
            joinColumns = @JoinColumn(name="users_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="words_id", referencedColumnName="ID")
    )
    private Set<Word> words = new HashSet<>();
    
    
    public User() {

    }
    
    public User(String login, String name, String surname, String password, String confirmation,String email) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.confirmation = confirmation;
        this.email=email;

    }
// </editor-fold>
    @Override
    public String toString(){
        return "id="+id+" login="+login+" name="+name+" surname="+surname
                +" password="+password+" comfirmation="+confirmation
                +" email="+email +" WORDS  = " +words;
    }
    
    public void addWordInSet(Word word){
        UserService usServ = new UserService();
        this.getWords().add(word); 
        usServ.addWord(this,word);
    }
    public void changeUser(User newU){
        UserService usServ = new UserService();
        usServ.setUserSettings(this, newU);
        this.setName(newU.getName());
        this.setSurname(newU.getSurname());
        this.setPassword(newU.getPassword());
        this.setEmail(newU.getEmail());
        
    }
    public void changeUserPass(User newU,String newP){
        UserService usServ = new UserService();
        newU.setPassword(newP);
        usServ.setUserPass(this, newU);
        this.setPassword(newU.getPassword());
        
    }
    public void delWordInSet(Word word){
        UserService usServ = new UserService();
        ArrayList<Word> cUserWords= new ArrayList<>();
        cUserWords.addAll(words);
        int i=0;
        for(i=0;i<cUserWords.size();i++)
            if(cUserWords.get(i).getWord().equals(word.getWord())){
                words.remove(cUserWords.get(i));
                usServ.deleteWord(this, cUserWords.get(i));
                break;
            }
        System.out.println("del "+i+" word"+ cUserWords.get(i));
        

    }
    public Set<String> getListForSet(){
        List<String> list = new ArrayList<>();
        List<Word> list2 = new ArrayList<>();
        list2.addAll(getWords());
        for(int i=0;i<list2.size();i++){
            list.add(list2.get(i).getWord());
        }
        Set<String> st = new HashSet<>();
        st.addAll(list);
        return st;
    }
    // <editor-fold defaultstate="collapsed" desc="Get/Set methods">

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id=id;
    }
// </editor-fold>
}