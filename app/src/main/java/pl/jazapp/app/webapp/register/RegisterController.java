package pl.jazapp.app.webapp.register;

import pl.jazapp.app.User;
import pl.jazapp.app.UserCreatorService;
import pl.jazapp.app.Users;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterController {
    @Inject
    private UserCreatorService userCreator;
    @Inject
    private Users users;

    public String register(RegisterRequest registerRequest) {
        var username = registerRequest.getUsername();
        var userExists = users.userExists(username);
        var password = registerRequest.getPassword();
        var passwordCheck = registerRequest.getPasswordCheck();

        if (userExists) {
            var flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            flash.put("error-user-is-taken", "Username is already taken.");

            return "/register.xhtml";
        }

        if (!User.passwordCheck(password, passwordCheck)) {
            var flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            flash.put("error-passwords-do-not-match", "Passwords do not match.");

            return "/register.xhtml";
        }

        // add user to database
        userCreator.createUser(username, new User(registerRequest));
        return "login.xhtml?faces-redirect=true";
    }
}
