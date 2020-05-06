package pl.jazapp.app.webapp.extension.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.PropertyResourceBundle;

@FacesValidator("lastNameValidator")
public class LastNameValidator implements Validator<String> {
    private static final String ONLY_LETTERS_MESSAGE_ID = "pl.jazapp.app.webapp.extension.validator.LastNameValidator.ONLY_LETTERS_LAST_NAME";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (!value.matches("[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+")) {
            var msg = getMsg(context);
            var onlySmallLettersMsg = msg.getString(ONLY_LETTERS_MESSAGE_ID);
            throw new ValidatorException(new FacesMessage(onlySmallLettersMsg));
        }
    }

    public PropertyResourceBundle getMsg (FacesContext context){
        return context.getApplication().evaluateExpressionGet(
                context, "#{msg}", PropertyResourceBundle.class);
    }
}
