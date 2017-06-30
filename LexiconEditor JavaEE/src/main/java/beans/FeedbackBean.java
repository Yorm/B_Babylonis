package beans;

import beans.classes.Sender;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class FeedbackBean implements Serializable {
     // <editor-fold defaultstate="collapsed" desc="Fields">
    private String userEmail;
    private String password;
    private String UPmessage;
    private String message;
    final private String adminEmail="walterdive@gmail.com";
    // </editor-fold>
    public void send(){
        //если использовать ajax в этом бине - отправляются пустые параметры и выскакивает ошибка
        addMessage("Успешно!", "Голубь уже вылетер, милорд!");
        Sender s = new Sender(userEmail,password);
        s.send(UPmessage, message, userEmail, adminEmail);
        //userEmail=null;
        //password=null;
        //UPmessage=null;
        //message=null;  
    }
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    // <editor-fold defaultstate="collapsed" desc="Get/Set methods">
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUPmessage() {
        return UPmessage;
    }

    public void setUPmessage(String UPmessage) {
        this.UPmessage = UPmessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }    
    // </editor-fold>
}
