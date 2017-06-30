package beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.commons.lang3.StringUtils;

@FacesValidator(value="newEmailValidator")
public class EmailValidator implements Validator{

    public void validate(FacesContext context, UIComponent toValidate, Object value) 
            throws ValidatorException {
        
        String emailStr = (String) value;
        if (-1 == emailStr.indexOf("@")) {           
            throw new ValidatorException(new FacesMessage("Неккоректный email"));
        }
    }
}