package pl.jazapp.app.webapp.extension.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.PropertyResourceBundle;

@FacesValidator("usernameAndPasswordValidator")
public class UsernameAndPassword implements Validator<String> {
    private static final String LENGTH_VALIDATOR_MESSAGE_ID = "pl.jazapp.app.webapp.extension.validator.UsernameAndPassword.LENGTH_VALIDATOR";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (value.length() > 25) {
            var msg = getMsg(context);
            var onlySmallLettersMsg = msg.getString(LENGTH_VALIDATOR_MESSAGE_ID);
            throw new ValidatorException(new FacesMessage(onlySmallLettersMsg));
        }
    }

    public PropertyResourceBundle getMsg (FacesContext context){
        return context.getApplication().evaluateExpressionGet(
                context, "#{msg}", PropertyResourceBundle.class);
    }
}
