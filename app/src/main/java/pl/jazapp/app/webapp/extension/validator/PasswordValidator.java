package pl.jazapp.app.webapp.extension.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.PropertyResourceBundle;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator<String> {
    private static final String INVALID_PASSWORD_MESSAGE_ID = "pl.jazapp.app.webapp.extension.validator.PasswordValidator.INVALID_PASSWORD";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (!value.matches("(?-i)(?=^.{3,}$)((?!.*\\s)(?=.*[A-Z])(?=.*[a-z]))((?=(.*\\d){1,})|(?=(.*\\W){1,}))^.*$")) {
            var msg = getMsg(context);
            var onlySmallLettersMsg = msg.getString(INVALID_PASSWORD_MESSAGE_ID);
            throw new ValidatorException(new FacesMessage(onlySmallLettersMsg));
        }
    }

    public PropertyResourceBundle getMsg (FacesContext context){
        return context.getApplication().evaluateExpressionGet(
                context, "#{msg}", PropertyResourceBundle.class);
    }
}