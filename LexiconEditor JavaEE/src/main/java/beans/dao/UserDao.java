package beans.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import beans.classes.User;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class UserDao implements Serializable{
    List<User> allUsers = new ArrayList<>();
    UserService usServ = new UserService();
    User currentUser = new User("1","1","1","1","1","1");
    
    public UserDao(){}
    
    public int countUsers(){
        return usServ.countUsers();
    }
    
    public User findUser(long i){
        return usServ.get(i);
    }
    public void addUser(User user){  
        allUsers.add(user);
        usServ.add(user);
    }
   
    public void findWords(){
        for(int i=0;i<7;i++){
            System.out.println(usServ.getWords(4));
        }
    }

    
    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }
    public List<User> getAllUsers() {
        return allUsers;
    } 

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
}
