package pl.jazapp.app.webapp.login;

import pl.jazapp.app.Users;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@ApplicationScoped
public class LoginService {
    @Inject
    private Users users;
    @Inject
    private HttpServletRequest request;

    private static boolean isLogged;

    public boolean logIn(String username, String password) {
        try {
            var user = users.getUser(username, password);
            var session = request.getSession();
            session.setAttribute("username", user.getUsername());
            setLogged(true);
        } catch (NullPointerException e) {
            setLogged(false);
        }

        return isLogged();
    }

    public static boolean isLogged() {
        return isLogged;
    }

    public static void setLogged(boolean logged) {
        isLogged = logged;
    }
}
