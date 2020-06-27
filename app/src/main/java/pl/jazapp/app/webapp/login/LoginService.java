package pl.jazapp.app.webapp.login;

import pl.jazapp.app.webapp.user.Users;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@ApplicationScoped
public class LoginService {
    @Inject
    private Users users;
    @Inject
    private HttpServletRequest request;

    public boolean logIn(String username, String password) {
        var user = users.getUser(username, password);
        if (user != null) {
            var session = request.getSession();
            session.setAttribute("username", user.getUsername());
            return true;
        }
        return false;
    }
}
