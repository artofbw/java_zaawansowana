package pl.jazapp.app.webapp.extension.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.PropertyResourceBundle;

@FacesValidator("dateValidator")
public class DateValidator implements Validator<String> {
    private static final String INVALID_DATE_MESSAGE_ID = "pl.jazapp.app.webapp.extension.validator.DateValidator.INVALID_DATE";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (!value.matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$")) {
            var msg = getMsg(context);
            var onlySmallLettersMsg = msg.getString(INVALID_DATE_MESSAGE_ID);
            throw new ValidatorException(new FacesMessage(onlySmallLettersMsg));
        }
    }

    public PropertyResourceBundle getMsg (FacesContext context){
        return context.getApplication().evaluateExpressionGet(
                context, "#{msg}", PropertyResourceBundle.class);
    }
}
