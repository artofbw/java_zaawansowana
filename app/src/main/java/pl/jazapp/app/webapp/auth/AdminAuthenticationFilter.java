package pl.jazapp.app.webapp.auth;

import pl.jazapp.app.webapp.user.UserContext;
import pl.jazapp.app.webapp.user.Users;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/departments/edit.xhtml", "/categories/exit.xhtml"})
public class AdminAuthenticationFilter extends HttpFilter {
    @Inject
    UserContext userContext;

    @Inject
    Users users;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if(users.getUserEntity(userContext.getUser()) != null && userContext.isAdmin()) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/");
        }
    }
}
