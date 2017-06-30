package beans;

import beans.classes.User;
import beans.dao.UserDao;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
public class SettingsBean implements Serializable{
    // <editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    UserDao userDao;
    
    private User changeUser;
    private String oldPassword;
    private String newPassword;
    // </editor-fold>
    @PostConstruct
    public void init() {
       changeUser=userDao.getCurrentUser();
       
    }
    
    public void voided(){
        
    }
     
    public void save(){
        if(oldPassword.equals(userDao.getCurrentUser().getPassword())&&oldPassword.equals(userDao.getCurrentUser().getConfirmation())){
            addMessage("Со...", "Хранено");
            userDao.getCurrentUser().changeUser(changeUser);
            userDao.setCurrentUser(changeUser);
            init();
        }else addMessage("Ошибка!", "Старый пароль указан неверно либо подтверждение не совпадает, нельзя сохранить");
        
    }
    public void changePass(){
        if(oldPassword.equals(userDao.getCurrentUser().getPassword())&&oldPassword.equals(userDao.getCurrentUser().getConfirmation())){
            addMessage("Изменено", "Лучше запомнить свой новый пароль");
            userDao.getCurrentUser().changeUserPass(changeUser,newPassword);
            userDao.setCurrentUser(changeUser);
            init();
        }else addMessage("Ошибка!", "Старый пароль указан неверно либо подтверждение не совпадает, нельзя сменить пароль");
        
    }
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    // <editor-fold defaultstate="collapsed" desc="Get/Set methods">
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    public User getChangeUser() {
        return changeUser;
    }

    public void setChangeUser(User changeUser) {
        this.changeUser = changeUser;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    // </editor-fold>
    
}
