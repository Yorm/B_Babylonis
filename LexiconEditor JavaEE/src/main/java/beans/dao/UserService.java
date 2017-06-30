package beans.dao;

import beans.classes.User;
import beans.classes.Word;
import java.util.HashSet;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import javax.inject.Inject;

public class UserService {
    public EntityManager em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();
    @Inject
    UserDao userDao;
    @Inject
    WordsDao wordDao;
        
    
    public void add(User user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        System.out.println("add "+ user.toString());
    }
    
    public void addWord(User u, Word w){
        User user = em.find(User.class, u.getId());
 
        em.getTransaction().begin();
        user.getWords().add(w);
        em.merge(user);
        em.getTransaction().commit();
        
    }
    
    public void deleteUser(long id){
        em.getTransaction().begin();
        em.remove(get(id)); 
        em.getTransaction().commit(); 
    }
    
    public void deleteWord(User u,Word w){
        
        Word word = getWords(w.getID());
        System.out.println("до "+word.getUsers());
       // word.setUsers(new HashSet());
        System.out.println("после "+word.getUsers());
        em.getTransaction().begin();       
        em.remove(word); 
        //(remove)
        em.getTransaction().commit();   
        
    }
    public void setUserSettings(User u,User newU){
        User user = em.find(User.class, u.getId());
 
        em.getTransaction().begin();
        user.setName(newU.getName());
        user.setSurname(newU.getSurname());
        user.setPassword(newU.getPassword());
        user.setEmail(newU.getEmail());
        em.merge(user);
        em.getTransaction().commit();  
    }
    public void setUserPass(User u,User newU){
        User user = em.find(User.class, u.getId());
 
        em.getTransaction().begin();
        user.setPassword(newU.getPassword());
        em.merge(user);
        em.getTransaction().commit();  
    }
    public Integer countUsers(){
        List<Integer> a = em.createNativeQuery("SELECT COUNT(*) FROM users_table;").getResultList(); 
        return Integer.parseInt(a.get(0)+"");
    }
    public Integer countWords(){
        List<Integer> a = em.createNativeQuery("SELECT COUNT(*) FROM words_table;").getResultList(); 
        return Integer.parseInt(a.get(0)+"");
    }
    //ищет по айдишнику
    public User get(long id){
        return em.find(User.class, id);
    }
    public Word getWords(long id){
        return em.find(Word.class, id);
    }

}
