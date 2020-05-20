package pl.jazapp.app;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

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
        var user = users.getFullName(username.toString());
        return user;
    }
}
