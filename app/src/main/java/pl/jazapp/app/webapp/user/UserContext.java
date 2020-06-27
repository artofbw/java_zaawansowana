package pl.jazapp.app.webapp.user;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Optional;

@Named
@SessionScoped
public class UserContext implements Serializable {
    @Inject
    private Users users;
    @Inject
    private HttpServletRequest request;

    private static final Long serialVersionUid = 1L;

    public String getFullName() {
        var username = request.getSession().getAttribute("username");;
        var fullName = users.getFullName(username.toString());
        return fullName;
    }

    public Optional<User> getUser() {
        var username = request.getSession().getAttribute("username");;
        var user = users.getUserEntity(username.toString());
        return Optional.ofNullable(user);
    }

    public boolean isAdmin() {
        return getUser().get().isAdmin();
    }
}
