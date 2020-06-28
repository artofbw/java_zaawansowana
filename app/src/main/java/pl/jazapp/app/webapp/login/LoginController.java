package pl.jazapp.app.webapp.login;

import pl.jazapp.app.services.LoginService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginController {
    @Inject
    private LoginService loginService;

    public String login(LoginRequest loginRequest) {
        if (loginService.logIn(loginRequest.getUsername(), loginRequest.getPassword())) {
            return "index.xhtml?faces-redirect=true";
        } else {
            var flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            flash.put("error-wrong-username-or-password", "Wrong username or password.");

            return "/login.xhtml";
        }
    }
}
