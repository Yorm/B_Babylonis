package beans.classes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "words_table")
public class Word implements Serializable {
    
// <editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long ID;
    
    @Column(name = "word")
    private String word;
    
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE},mappedBy = "words")
    private Set<User> users = new HashSet<>();
    
    public Word() {}
    public Word(String word) {this.word=word;}
// </editor-fold>
    @Override
    public String toString(){
        return "id="+ID+"  word="+word;
    }
// <editor-fold defaultstate="collapsed" desc="Get/Set methods">

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    // </editor-fold>
}
