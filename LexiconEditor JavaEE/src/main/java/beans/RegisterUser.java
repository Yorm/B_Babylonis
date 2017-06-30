package beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import beans.classes.User;
import beans.dao.UserDao;
import javax.enterprise.context.RequestScoped;


@Named
@RequestScoped
public class RegisterUser implements Serializable{
// <editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    UserDao userDao;
    boolean b;
    private User currentUser;

    public RegisterUser() {}
// </editor-fold>

    @PostConstruct
    public void init() {
       currentUser=new User();
    }
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void addUser(){
        User u;
        System.out.println("currentUser "+currentUser.toString());
        for( int i=1;i<=userDao.countUsers();i++){
            u = userDao.findUser(i);
            if(currentUser.getLogin().equals(u.getLogin())){
                b = false;
                break;
            } else {
                b=true;
            }
        }
        if(!b) addMessage("Логин забит!", "Придумай получше");
        if(b){
            currentUser.setConfirmation("");
            userDao.addUser(currentUser);
            currentUser = new User();
        }
        
    }
    public String confirmation(){
        if(b)
            return "login";
        else 
            return "register";
    }
    
// <editor-fold defaultstate="collapsed" desc="Get/Set methods">
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
// </editor-fold>   
}