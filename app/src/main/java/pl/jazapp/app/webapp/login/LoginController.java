package pl.jazapp.app.webapp.login;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginController {
    public String login(LoginRequest loginRequest) {
        System.out.println(String.format(
            "Tried to login with username %s and password %s", loginRequest.getUsername(), loginRequest.getPassword())
        );
        return "index.xhtml?faces-redirect =true";
    }
}
