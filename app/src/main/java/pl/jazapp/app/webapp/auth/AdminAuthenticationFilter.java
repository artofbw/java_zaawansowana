package pl.jazapp.app.webapp.auth;

import pl.jazapp.app.webapp.user.UserContext;

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

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if(userContext.getUser().get() != null && userContext.isAdmin()) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/");
        }
    }
}
