package pl.jazapp.app.webapp.register;

import pl.jazapp.app.webapp.login.RegisterRequest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterController {
    public String register(RegisterRequest registerRequest) {
        System.out.println(String.format(
                "Tried to log in with username %s and password %s", registerRequest.getUsername(), registerRequest.getPassword())
        );
        return "login.xhtml?faces-redirect =true";
    }
}
