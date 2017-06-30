package beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.commons.lang3.StringUtils;

@FacesValidator(value="newPasswordValidator")
public class PasswordValidator implements Validator{

    public void validate(FacesContext context, UIComponent toValidate, Object value) 
            throws ValidatorException {
        
        String confirmedPassword = value.toString();
        final UIComponent userForm = context.getViewRoot().findComponent("userForm");
        final UIComponent passwordField = userForm.findComponent("passIn");
         
        final String password = ((UIInput)passwordField).getValue().toString();

        if(!StringUtils.equals(password,confirmedPassword)) {
            throw new ValidatorException(new FacesMessage("Пароль не прошел подтверждение"));
        }
    }
}