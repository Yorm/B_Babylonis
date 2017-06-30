package beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import beans.classes.User;
import beans.dao.UserDao;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class LoginUser implements Serializable{
// <editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    UserDao userDao;
    
    private String login;
    private String password;
    private Boolean b;
    public LoginUser() {}
// </editor-fold>
    
    @PostConstruct
    public void init() {
        System.out.println("init");
        //userDao.addUser(new User("1","1","1","1","1","1"));
        //userDao.addUser(new User("2","2","2","2","2","2"));
        //userDao.addUser(new User("3","3","3","3","3","3"));
    }

    public void loginOperation(){
        b=false;
        User u;
        for( int i=1;i<=userDao.countUsers();i++){
            u = userDao.findUser(i);
            if(login.equals(u.getLogin())){
                b=true;
                break;
            }else{ b=false; }
        }
        
        if(b) for( int i=1;i<=userDao.countUsers();i++){
            u = userDao.findUser(i);
            if(password.equals(u.getPassword())){
                b=true;
                userDao.setCurrentUser(u);
                addMessage("Вход", "(он выполнен)");
                break;
            }else{ b=false; }   
        }
        else {addMessage("Ошибка", "Такого пользователя нет");}
        if(!b) addMessage("Ошибка", "Пароль не верный");  
    }
    
    public void unLogin(){
        userDao.setCurrentUser(new User());
    }
    
    public String confirmation(){
        if(b)
            return "default";
        else 
            return "login";
    }
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
// <editor-fold defaultstate="collapsed" desc="Get/Set methods">

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
// </editor-fold>   
}
